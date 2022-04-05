package edu.byu.cs329.typechecker;

import static org.junit.jupiter.api.Assertions.assertTrue;
import edu.byu.cs329.utils.AstNodePropertiesUtils;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import junit.framework.AssertionFailedError;
import org.eclipse.jdt.core.dom.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeCheckBuilder {
  static final Logger log = LoggerFactory.getLogger(TypeCheckBuilder.class);

  class Visitor extends ASTVisitor {
    ISymbolTable symbolTable = null;
    String className = null;
    Deque<List<DynamicNode>> typeCheckStack = null;
    Deque<String> typeStack = null;
    int blockCounter = 0;
    int statementCounter = 0;

    public Visitor(ISymbolTable symbolTable) {
      this.symbolTable = symbolTable;
      typeCheckStack = new ArrayDeque<>();
      pushTypeCheck(new ArrayList<>());
      typeStack = new ArrayDeque<>();
    }

    @Override
    public boolean visit(CompilationUnit node) {
      pushTypeCheck(new ArrayList<>());

      List<String> types = new ArrayList<String>();
      for (Object declaration : node.types()) {
        ((TypeDeclaration) declaration).accept(this);
        types.add(popType());
      }

      DynamicTest test = generateAllVoidTestAndPushResultingType(types);
      peekTypeCheck().add(test);
      return false;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
      pushTypeCheck(new ArrayList<>());
      className = AstNodePropertiesUtils.getName(node);

      List<String> types = new ArrayList<String>();
      for (MethodDeclaration method : Arrays.asList(node.getMethods())) {
        method.accept(Visitor.this);
        types.add(popType());
      }

      DynamicTest test = generateAllVoidTestAndPushResultingType(types);
      peekTypeCheck().add(test);
      return false;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
      resetCounters();
      pushTypeCheck(new ArrayList<>());

      String name = TypeCheckUtils.buildName(className, AstNodePropertiesUtils.getName(node));

      List<SimpleImmutableEntry<String, String>> typeList = symbolTable.getParameterTypeList(name);
      symbolTable.pushScope();
      for (SimpleImmutableEntry<String, String> entry : typeList) {
        symbolTable.addLocal(entry.getKey(), entry.getValue());
      }

//      symbolTable.addLocal("this", className);
      String type = symbolTable.getType(name);
      symbolTable.addLocal("return", type);

      node.getBody().accept(this);

      type = popType();
      DynamicTest test = generateAllVoidTestAndPushResultingType(Arrays.asList(type));
      peekTypeCheck().add(test);
      return false;
    }

    @Override
    public boolean visit(Block node) {
      pushTypeCheck(new ArrayList<>());
      symbolTable.pushScope();

      List<String> typeList = new ArrayList<String>();
      for (Object statement : node.statements()) {
        ((Statement) statement).accept(this);
        typeList.add(popType());
      }

      DynamicTest test = generateAllVoidTestAndPushResultingType(typeList);
      peekTypeCheck().add(test);
      return false;
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
      pushTypeCheck(new ArrayList<>());
      String name = AstNodePropertiesUtils.getName(node);
      String type = TypeCheckUtils.getType(node);
      symbolTable.addLocal(name, type);
      AstNodePropertiesUtils.getSimpleName(node).accept(this);
      type = popType();

      Expression initializer = AstNodePropertiesUtils.getInitializer(node);
      if (initializer != null) {
        initializer.accept(this);
        String rightType = popType();
        DynamicTest test = generateTypeCompatibleTestAndPushResultingType(type, rightType);
        peekTypeCheck().add(test);
        type = popType();
      }

      if (!TypeCheckTypes.isError(type)) {
        type = TypeCheckTypes.VOID;
      }

      pushType(type);
      return false;
    }

    @Override
    public boolean visit(Assignment node) {
      pushTypeCheck(new ArrayList<>());
      SimpleName name = (SimpleName) node.getLeftHandSide();
      name.accept(this);
      String leftType = popType();

      Expression rightHandSide = node.getRightHandSide();
      if (rightHandSide != null) {
        rightHandSide.accept(this);
        String rightType = popType();
        DynamicTest test = generateTypeCompatibleTestAndPushResultingType(leftType, rightType);
        peekTypeCheck().add(test);
        leftType = popType();
      }
      String type = TypeCheckTypes.ERROR;

      if (!TypeCheckTypes.isError(leftType)) {
        type = TypeCheckTypes.VOID;
      }

      pushType(type);
      return false;
    }

    @Override
    public boolean visit(IfStatement node) {
      pushTypeCheck(new ArrayList<>());
      //FIXME: do i need scope
//      symbolTable.pushScope();

      node.getExpression().accept(this);
      String ifType = popType();

      DynamicTest test = generateTypeCompatibleTestAndPushResultingType(ifType, TypeCheckTypes.BOOL);
      peekTypeCheck().add(test);

      node.getThenStatement().accept(this);
//      String thenType = ;
      //TODO: add a failure test
      String type = TypeCheckTypes.ERROR;
      String thenType = popType();
      String elseType = TypeCheckTypes.VOID;
//      if (popType() == TypeCheckTypes.VOID) type = TypeCheckTypes.VOID;
      if (node.getElseStatement() != null) {
        node.getElseStatement().accept(this);
        elseType = popType();
        //TODO: add a failure test

//        if (popType() != TypeCheckTypes.VOID) type = TypeCheckTypes.ERROR;
      }


      if (!TypeCheckTypes.isError(thenType) && !TypeCheckTypes.isError(elseType)) {
        type = TypeCheckTypes.VOID;
      }

      pushType(type);

      return false;
    }

    @Override
    public boolean visit(WhileStatement node) {
      pushTypeCheck(new ArrayList<>());
      //FIXME: do i need scope
//      symbolTable.pushScope();
      node.getExpression().accept(this);
      String whileType = popType();

      DynamicTest test = generateTypeCompatibleTestAndPushResultingType(whileType, TypeCheckTypes.BOOL);
      peekTypeCheck().add(test);
      whileType = popType();
      String type = TypeCheckTypes.ERROR;

      if (!TypeCheckTypes.isError(whileType)) {
        type = TypeCheckTypes.VOID;
      }

      pushType(type);
      return false;
    }

    @Override
    public boolean visit(ReturnStatement node) {
      pushTypeCheck(new ArrayList<>());
      node.getExpression().accept(this);
      String expressionType = popType();

      String returnType = symbolTable.getType("return");
      DynamicTest test = generateTypeCompatibleTestAndPushResultingType(returnType, expressionType);
      peekTypeCheck().add(test);
      returnType = popType();
      String type = TypeCheckTypes.ERROR;

      if (!TypeCheckTypes.isError(returnType)) {
        type = TypeCheckTypes.VOID;
      }

      pushType(type);
      return false;
    }

    @Override
    public boolean visit(SimpleName node) {
      pushTypeCheck(new ArrayList<>());
      String name = AstNodePropertiesUtils.getName(node);
      String type = symbolTable.getType(name);
      generateLookupTestAndAddToObligations(name, type);
      pushType(type);
      return false;
    }

    @Override
    public boolean visit(BooleanLiteral node) {
      pushTypeCheck(new ArrayList<>());
      String name = node.toString();
      String type = TypeCheckTypes.BOOL;
      generateLookupTestAndAddToObligations(name, type);
      pushType(type);
      return false;
    }

    @Override
    public boolean visit(NumberLiteral node) {
      pushTypeCheck(new ArrayList<>());
      String name = node.getToken();
      String type = TypeCheckTypes.INT;
      generateLookupTestAndAddToObligations(name, type);
      pushType(type);
      return false;
    }

    @Override
    public boolean visit(NullLiteral node) {
      pushTypeCheck(new ArrayList<>());
      String name = node.toString();
      String type = TypeCheckTypes.NULL;
      generateLookupTestAndAddToObligations(name, type);
      pushType(type);
      return false;
    }

    @Override
    public boolean visit(InfixExpression node) {
      pushTypeCheck(new ArrayList<>());
      String name = node.toString();
      String operator = node.getOperator().toString();
      String type;

      //TODO: add a failure test that enters else statement
      if (operator.equals("+") || operator.equals("*") || operator.equals("-")) {
        type = TypeCheckTypes.INT;
      }
      else if (operator.equals("&&") || operator.equals("||") || operator.equals("==") || operator.equals("<") || operator.equals(">")) {
        type = TypeCheckTypes.BOOL;
      }
      else {
        type = TypeCheckTypes.ERROR;
      }

      generateLookupTestAndAddToObligations(name, type);
      pushType(type);
      return false;
    }

    @Override
    public boolean visit(PrefixExpression node) {
      pushTypeCheck(new ArrayList<>());
      String name = node.toString();
      String operator = node.getOperator().toString();
      String prefixType;

      if (operator.equals("!")) {
        prefixType = TypeCheckTypes.BOOL;
      }
      else {
        prefixType = TypeCheckTypes.ERROR;
      }

      pushType(prefixType);

      generateLookupTestAndAddToObligations(name, prefixType);
      return false;
    }

    @Override
    public void endVisit(CompilationUnit node) {
      String name = "CompilationUnit ";
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(TypeDeclaration node) {
      String name = "class " + className;
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(MethodDeclaration node) {
      symbolTable.popScope();
      String name =
          TypeCheckUtils.buildName("method " + className, AstNodePropertiesUtils.getName(node));
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(Block node) {
      symbolTable.popScope();
      String name = generateBlockName();
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(IfStatement node) {
      //FIXME: do i need scope
//      symbolTable.popScope();
    }

    @Override
    public void endVisit(WhileStatement node) {
      //FIXME: do i need scope
//      symbolTable.popScope();
    }

    @Override
    public void endVisit(VariableDeclarationStatement node) {
      String name = generateStatementName();
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(SimpleName node) {
      String name = AstNodePropertiesUtils.getName(node);
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(BooleanLiteral node) {
      String name = node.toString();
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(NumberLiteral node) {
      String name = node.getToken();
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(NullLiteral node) {
      String name = node.toString();
      generateProofAndAddToObligations(name);
    }

    @Override
    public void endVisit(InfixExpression node) {
      String name = node.toString();
      generateProofAndAddToObligations(name);
    }

    private void resetCounters() {
      statementCounter = 0;
      blockCounter = 0;
    }

    private void generateLookupTestAndAddToObligations(String name, String type) {
      String displayName = generateLookupDisplayName(name, type);
      DynamicTest test = DynamicTest.dynamicTest(displayName,
          () -> Assertions.assertNotEquals(TypeCheckTypes.ERROR, type));
      peekTypeCheck().add(test);
    }

    private void generateProofAndAddToObligations(String name) {
      String type = peekType();
      String displayName = generateProvesDisplayName(name, type);
      List<DynamicNode> proofs = popTypeCheck();
      addNoObligationIfEmpty(proofs);
      DynamicContainer proof = DynamicContainer.dynamicContainer(displayName, proofs.stream());
      List<DynamicNode> obligations = peekTypeCheck();
      obligations.add(proof);
    }

    private DynamicTest generateTypeCompatibleTestAndPushResultingType(String leftType, String rightType) {
      String displayName = leftType + " := " + rightType;

      boolean isAssignmentCompatible = TypeCheckTypes.isAssignmentCompatible(leftType, rightType);

      DynamicTest test = DynamicTest.dynamicTest(displayName, () -> assertTrue(isAssignmentCompatible));

      String type = TypeCheckTypes.VOID;
      if (!isAssignmentCompatible) {
        type = TypeCheckTypes.ERROR;
      }
      pushType(type);
      return test;
    }

    private DynamicTest generateAllVoidTestAndPushResultingType(List<String> types) {
      DynamicTest test = null;
      String type = TypeCheckTypes.VOID;

      if (types.isEmpty()) {
        test = generateNoObligation();
        pushType(type);
        return test;
      }

      String displayName = String.join(",", types) + " = " + TypeCheckTypes.VOID;
      boolean testValue = types.stream().allMatch(t -> t.equals(TypeCheckTypes.VOID));
      test = DynamicTest.dynamicTest(displayName, () -> assertTrue(testValue));
      if (!testValue) {
        type = TypeCheckTypes.ERROR;
      }
      pushType(type);
      return test;
    }


    private void addNoObligationIfEmpty(List<DynamicNode> proofs) {
      if (proofs.size() > 0) {
        return;
      }
      proofs.add(generateNoObligation());
    }

    private DynamicTest generateNoObligation() {
      return DynamicTest.dynamicTest("true", () -> assertTrue(true));
    }

    private String generateProvesDisplayName(String name, String type) {
      return name + ":" + type;
    }

    private String generateLookupDisplayName(String name, String type) {
      return "E(" + name + ") = " + type;
    }

    private List<DynamicNode> popTypeCheck() {
      return typeCheckStack.pop();
    }

    private void pushTypeCheck(List<DynamicNode> proof) {
      typeCheckStack.push(proof);
    }

    private List<DynamicNode> peekTypeCheck() {
      return typeCheckStack.peek();
    }

    private String popType() {
      return typeStack.pop();
    }

    private void pushType(String type) {
      typeStack.push(type);
    }

    private String peekType() {
      return typeStack.peek();
    }

    private String generateBlockName() {
      return "B" + blockCounter++;
    }

    private String generateStatementName() {
      return "S" + statementCounter++;
    }

  }

  public TypeCheckBuilder() {

  }

  /**
   * Returns true if static type safe with the checks.
   *
   * @param symbolTable the environment for the type checks
   * @param node the ASTNode for the compilation unit
   * @param tests a container to hold the tests
   * @return true iff the compilation is static type safe
   */
  public boolean getTypeChecker(ISymbolTable symbolTable, ASTNode node, List<DynamicNode> tests) {
    Visitor visitor = new Visitor(symbolTable);
    node.accept(visitor);
    tests.addAll(visitor.popTypeCheck());
    return visitor.popType().equals(TypeCheckTypes.VOID);
  }
}
