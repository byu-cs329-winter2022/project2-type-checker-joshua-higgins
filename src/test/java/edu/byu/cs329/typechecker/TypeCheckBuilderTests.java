package edu.byu.cs329.typechecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import edu.byu.cs329.utils.JavaSourceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import junit.framework.AssertionFailedError;
import org.eclipse.jdt.core.dom.ASTNode;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("Tests for TypeCheckerBuilder")
public class TypeCheckBuilderTests {
  static final Logger log = LoggerFactory.getLogger(TypeCheckBuilderTests.class);

  private boolean getTypeChecker(final String fileName, List<DynamicNode> tests) {
    ASTNode compilationUnit = JavaSourceUtils.getAstNodeFor(this, fileName);
    SymbolTableBuilder symbolTableBuilder = new SymbolTableBuilder();
    ISymbolTable symbolTable = symbolTableBuilder.getSymbolTable(compilationUnit);
    TypeCheckBuilder typeCheckerBuilder = new TypeCheckBuilder();
    return typeCheckerBuilder.getTypeChecker(symbolTable, compilationUnit, tests);
  }

  @TestFactory
  @DisplayName("Should prove type safe when given empty class")
  Stream<DynamicNode> should_proveTypeSafe_when_givenEmptyClass() {
    String fileName = "typeChecker/should_proveTypeSafe_when_givenEmptyClass.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given empty method")
  Stream<DynamicNode> should_proveTypeSafe_when_givenEmptyMethod() {
    String fileName = "typeChecker/should_proveTypeSafe_when_givenEmptyMethod.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given empty block")
  Stream<DynamicNode> should_proveTypeSafe_when_givenEmptyBlock() {
    String fileName = "typeChecker/should_proveTypeSafe_when_givenEmptyBlock.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given variable declarations no inits")
  Stream<DynamicNode> should_proveTypeSafe_when_givenVariableDeclrationsNoInits() {
    String fileName = "typeChecker/should_proveTypeSafe_when_givenVariableDeclrationsNoInits.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given variable declarations with compatible inits")
  Stream<DynamicNode> should_proveTypeSafe_when_givenVariableDeclrationsWithCompatibleInits() {
    String fileName = "typeChecker/should_proveTypeSafe_when_givenVariableDeclrationsWithCompatibleInits.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given assignment with compatible inits")
  Stream<DynamicNode> should_proveTypeSafe_when_givenAssignment() {
    String fileName = "typeChecker/should_proveTypeSafe_when_givenAssignment.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given if statement with boolean")
  Stream<DynamicNode> should_proveTypeSafe_with_IfStatement() {
    String fileName = "typeChecker/should_proveTypeSafe_with_IfStatement.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given if else statement with boolean")
  Stream<DynamicNode> should_proveTypeSafe_with_IfElseStatement() {
    String fileName = "typeChecker/should_proveTypeSafe_with_IfElseStatement.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given if statement with comparable ints")
  Stream<DynamicNode> should_proveTypeSafe_with_IfStatementWithComparableInts() {
    String fileName = "typeChecker/should_proveTypeSafe_with_IfStatementWithCompableInts.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given if statement with prefix expression")
  Stream<DynamicNode> should_proveTypeSafe_with_IfStatementWithPrefixExpression() {
    String fileName = "typeChecker/should_proveTypeSafe_with_IfStatementWithPrefixExpression.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given while statement with boolean")
  Stream<DynamicNode> should_proveTypeSafe_with_WhileStatement() {
    String fileName = "typeChecker/should_proveTypeSafe_with_WhileStatement.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given while statement with boolean")
  Stream<DynamicNode> should_proveTypeSafe_with_WhileStatementWithComparableInts() {
    String fileName = "typeChecker/should_proveTypeSafe_with_WhileStatementWithComparableInts.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given return statement with comparable ints")
  Stream<DynamicNode> should_proveTypeSafe_with_ReturnStatement() {
    String fileName = "typeChecker/should_proveTypeSafe_with_ReturnStatement.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    
    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should not prove type safe when given bad inits")
  Stream<DynamicNode> should_NotProveTypeSafe_when_givenBadInits() {
    String fileName = "typeChecker/should_NotProveTypeSafe_when_givenBadInits.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    // Toggle as desired
    //
    // Option 1: mvn exec:java shows the details of the typeproof for visual inspection
    // return tests.stream();
    //
    // Option 2: test only isNotTypeSafe and show no details
    if (isTypeSafe) throw new AssertionFailedError();
    DynamicTest test = DynamicTest.dynamicTest("isNotTypeSafe", () -> assertFalse(isTypeSafe));
    return Arrays.asList((DynamicNode)test).stream();
  }

  @TestFactory
  @DisplayName("Should not prove type safe when given assignment")
  Stream<DynamicNode> shouldNot_proveTypeSafe_when_givenAssignment() {
    String fileName = "typeChecker/shouldNot_proveTypeSafe_when_givenAssignment.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);
    if (isTypeSafe) throw new AssertionFailedError();
    DynamicTest test = DynamicTest.dynamicTest("isNotTypeSafe", () -> assertFalse(isTypeSafe));
    return Arrays.asList((DynamicNode)test).stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given two methods")
  Stream<DynamicNode> should_prove_typeSafe_when_given_multiMethods() {
    String fileName = "typeChecker/should_prove_typeSafe_when_given_twoMethods.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should not prove type safe when string is out of scope")
  Stream<DynamicNode> should_not_prove_typeSafe_when_given_outOfScope() {
    String fileName = "typeChecker/should_not_prove_typeSafe_when_given_outOfScope.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    // Option 2: test only isNotTypeSafe and show no details
    if (isTypeSafe) throw new AssertionFailedError();
    DynamicTest test = DynamicTest.dynamicTest("isNotTypeSafe", () -> assertFalse(isTypeSafe));
    return Arrays.asList((DynamicNode)test).stream();
  }

  @TestFactory
  @DisplayName("Should not prove type safe with incomparable else")
  Stream<DynamicNode> should_not_prove_typeSafe_when_given_BadElse() {
    String fileName = "typeChecker/should_not_prove_typeSafe_when_given_BadElse.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    // Option 2: test only isNotTypeSafe and show no details
    if (isTypeSafe) throw new AssertionFailedError();
    DynamicTest test = DynamicTest.dynamicTest("isNotTypeSafe", () -> assertFalse(isTypeSafe));
    return Arrays.asList((DynamicNode)test).stream();
  }

  @TestFactory
  @DisplayName("Should not prove type safe with incomparable if in if else")
  Stream<DynamicNode> should_not_prove_typeSafe_when_given_BadIf_forIfElse() {
    String fileName = "typeChecker/should_not_prove_typeSafe_when_given_BadIf_forIfElse.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    // Option 2: test only isNotTypeSafe and show no details
    if (isTypeSafe) throw new AssertionFailedError();
    DynamicTest test = DynamicTest.dynamicTest("isNotTypeSafe", () -> assertFalse(isTypeSafe));
    return Arrays.asList((DynamicNode)test).stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given infix expression of type int")
  Stream<DynamicNode> should_prove_typeSafe_when_given_InfixExpressionInt() {
    String fileName = "typeChecker/should_prove_typeSafe_when_given_InfixExpressionInt.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should prove type safe when given infix expression of type bool")
  Stream<DynamicNode> should_prove_typeSafe_when_given_InfixExpressionBool() {
    String fileName = "typeChecker/should_prove_typeSafe_when_given_InfixExpressionBool.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    DynamicTest test = DynamicTest.dynamicTest("isTypeSafe", () -> assertTrue(isTypeSafe));
    tests.add((DynamicNode)test);
    return tests.stream();
  }

  @TestFactory
  @DisplayName("Should not prove type safe when given incomparable infix")
  Stream<DynamicNode> should_not_prove_typeSafe_when_given_badInfixExpression() {
    String fileName = "typeChecker/should_not_prove_typeSafe_when_given_badInfixExpression.java";
    List<DynamicNode> tests = new ArrayList<>();
    boolean isTypeSafe = getTypeChecker(fileName, tests);

    // Option 2: test only isNotTypeSafe and show no details
    if (isTypeSafe) throw new AssertionFailedError();
    DynamicTest test = DynamicTest.dynamicTest("isNotTypeSafe", () -> assertFalse(isTypeSafe));
    return Arrays.asList((DynamicNode)test).stream();
  }

  /*
    1. Test display names
    2. Tests if generates the right number of tests
    3. Test assert does not throw
    4. Have examples of non type safe code that fails that do throw something
   */
  @Nested
  @DisplayName("Regression tests on tests")
  class RegressionTests {
    private void testExpectedSuccessfulTests(Stream<DynamicNode> tests, int numTests, List<String> displayNames) {
      List<DynamicNode> testList = buildListOfDynamicNodes(tests.collect(Collectors.toList()));
      Iterator<DynamicNode> iter = testList.iterator();
      int i = 0;

      Assertions.assertEquals(numTests, testList.size());
      while (iter.hasNext()) {
        DynamicNode n = iter.next();
        if (n instanceof DynamicTest) {
          Assertions.assertEquals(displayNames.get(i), n.getDisplayName());
          i++;
          Assertions.assertDoesNotThrow(((DynamicTest) n).getExecutable());
          //after mutation on code
//        Assertions.assertThrows(AssertionFailedError.class, ((DynamicTest) n).getExecutable());
        }
        else if (n instanceof DynamicContainer) {
          Assertions.assertEquals(displayNames.get(i), n.getDisplayName());
          i++;
        }
      }
    }

    private void testExpectedUnsuccessfulTests(Stream<DynamicNode> tests) {
      List<DynamicNode> testList = buildListOfDynamicNodes(tests.collect(Collectors.toList()));
      Iterator<DynamicNode> iter = testList.iterator();

      Assertions.assertEquals(1, testList.size());
      while (iter.hasNext()) {
        DynamicNode n = iter.next();
        if (n instanceof DynamicTest) {
          Assertions.assertEquals(n.getDisplayName(), "isNotTypeSafe");

//          Assertions.assertThrows(AssertionFailedError.class, ((DynamicTest) n).getExecutable());
        }
//        else if (n instanceof DynamicContainer) {
//          Assertions.assertEquals(n.getDisplayName(), "CompilationUnit :void");
//          Stream<? extends DynamicNode> children = ((DynamicContainer) n).getChildren();
//          Assertions.assertEquals(numTests, getTestCount(children));
//        }
      }
    }

    void addNodeAndChildren(DynamicNode node, List<DynamicNode> list) {
      list.add(node);
      if (node instanceof DynamicContainer) {
        DynamicContainer container = (DynamicContainer)node;
        Iterator<? extends DynamicNode> children = container.getChildren().iterator();
        while (children.hasNext()) {
          addNodeAndChildren(children.next(), list);
        }
      }
    }

    List<DynamicNode> buildListOfDynamicNodes(List<DynamicNode> proof) {
      List<DynamicNode> allNodes = new ArrayList<>();
      addNodeAndChildren(proof.get(0), allNodes);
      return allNodes;
    }

    @Test
    @DisplayName("Tests the givenEmptyClass test")
    void test_should_proveTypeSafe_when_givenEmptyClass() {
      Stream<DynamicNode> tests = should_proveTypeSafe_when_givenEmptyClass();
      int expectedNumTests = 4;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "true", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the givenEmptyMethod test")
    void test_should_proveTypeSafe_when_givenEmptyMethod() {
      Stream<DynamicNode> tests = should_proveTypeSafe_when_givenEmptyMethod();
      int expectedNumTests = 8;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.m:void", "B0:void", "true", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the givenEmptyBlock test")
    void test_should_proveTypeSafe_when_givenEmptyBlock() {
      Stream<DynamicNode> tests = should_proveTypeSafe_when_givenEmptyBlock();
      int expectedNumTests = 8;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.m:void", "B0:void", "true", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the givenVariableDeclrationsNoInits test")
    void test_should_proveTypeSafe_when_givenVariableDeclrationsNoInits() {
      Stream<DynamicNode> tests = should_proveTypeSafe_when_givenVariableDeclrationsNoInits();
      int expectedNumTests = 17;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.m:void", "B0:void", "S0:void", "j:int", "E(j) = int", "S1:void", "k:Integer", "E(k) = Integer", "S2:void", "c:C", "E(c) = C",
              "void,void,void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the VariableDeclrationsWithCompatibleInits test")
    void test_VariableDeclrationsWithCompatibleInits() {
      Stream<DynamicNode> tests = should_proveTypeSafe_when_givenVariableDeclrationsWithCompatibleInits();
      int expectedNumTests = 38;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void", "class C:void",
              "method C.m:void", "B0:void", "S0:void", "j:int", "E(j) = int", "10:int", "E(10) = int", "int := int", "S1:void", "k:Integer", "E(k) = Integer", "null:nullType",
              "E(null) = nullType", "Integer := nullType", "S2:void", "c:C", "E(c) = C", "null:nullType", "E(null) = nullType", "C := nullType", "S3:void", "d:C", "E(d) = C",
              "c:C", "E(c) = C", "C := C", "S4:void", "e:boolean", "E(e) = boolean", "false:boolean", "E(false) = boolean", "boolean := boolean",
              "void,void,void,void,void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the givenAssignment test")
    void test_should_proveTypeSafe_when_givenAssignment() {
      Stream<DynamicNode> tests = should_proveTypeSafe_when_givenAssignment();
      int expectedNumTests = 16;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
      "class C:void", "method C.method:void", "S0:void", "j:int", "E(j) = int", "B0:void", "j:int", "E(j) = int",
      "5:int", "E(5) = int", "int := int", "void,void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the IfStatement test")
    void test_should_proveTypeSafe_with_IfStatement() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_IfStatement();
      int expectedNumTests = 29;
      List<String> displayNames = new ArrayList<>(Arrays.asList(
              "CompilationUnit :void", "S0:void", "isFive:boolean", "E(isFive) = boolean", "false:boolean",
              "E(false) = boolean", "boolean := boolean", "class C:void", "true:boolean", "E(true) = boolean",
              "boolean := boolean", "method C.method:void", "B0:void", "isFive:boolean", "E(isFive) = boolean",
              "true:boolean", "E(true) = boolean", "boolean := boolean", "void = void", "B1:void", "isFive:boolean",
              "E(isFive) = boolean", "false:boolean", "E(false) = boolean", "boolean := boolean",
              "void,void,void = void", "void = void", "void = void", "void = void"
      ));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the IfElseStatement test")
    void test_should_proveTypeSafe_with_IfElseStatement() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_IfElseStatement();
      int expectedNumTests = 31;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "S0:void", "i:int", "E(i) = int", "1:int", "E(1) = int", "int := int", "class C:void", "true:boolean", "E(true) = boolean",
              "boolean := boolean", "method C.method:void", "B0:void", "i:int", "E(i) = int", "2:int", "E(2) = int", "int := int", "void = void",
              "B2:void", "B1:void", "i:int", "E(i) = int", "0:int", "E(0) = int", "int := int", "void = void", "void,void = void", "void = void",
              "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the IfStatementWithComparableInts test")
    void test_should_proveTypeSafe_with_IfStatementWithComparableInts() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_IfStatementWithComparableInts();
      int expectedNumTests = 30;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "S0:void", "j:int", "E(j) = int", "5:int", "E(5) = int", "int := int", "S1:void", "isFive:boolean", "E(isFive) = boolean",
              "false:boolean", "E(false) = boolean", "boolean := boolean", "method C.method:void", "j == 5:boolean", "E(j == 5) = boolean", "boolean := boolean",
              "B1:void", "B0:void", "isFive:boolean", "E(isFive) = boolean", "true:boolean", "E(true) = boolean", "boolean := boolean", "void = void",
              "void,void,void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the IfStatementWithPrefixExpression test")
    void test_should_proveTypeSafe_with_IfStatementWithPrefixExpression() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_IfStatementWithPrefixExpression();
      int expectedNumTests = 23;
      List<String> displayNames = new ArrayList<>(Arrays.asList( "CompilationUnit :void",
              "S0:void", "isTrue:boolean", "E(isTrue) = boolean", "false:boolean", "E(false) = boolean", "boolean := boolean",
              "class C:void", "method C.method:void", "E(!false) = boolean", "boolean := boolean", "B1:void", "B0:void",
              "isTrue:boolean", "E(isTrue) = boolean", "true:boolean", "E(true) = boolean", "boolean := boolean",
              "void = void", "void,void = void", "void = void", "void = void", "void = void"
      ));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the WhileStatement test")
    void test_should_proveTypeSafe_with_WhileStatement() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_WhileStatement();
      int expectedNumTests = 11;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.method:void", "B0:void", "true:boolean", "E(true) = boolean", "boolean := boolean",
              "void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the WhileStatementWithComparableInts test")
    void test_should_proveTypeSafe_with_WhileStatementWithComparableInts() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_WhileStatementWithComparableInts();
      int expectedNumTests = 17;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.method:void", "S0:void", "i:int", "E(i) = int", "5:int", "E(5) = int", "int := int", "B0:void",
              "i > 0:boolean", "E(i > 0) = boolean", "boolean := boolean", "void,void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests the ReturnStatement test")
    void test_should_proveTypeSafe_with_ReturnStatement() {
      Stream<DynamicNode> tests = should_proveTypeSafe_with_ReturnStatement();
      int expectedNumTests = 17;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.method:void", "S0:void", "j:int", "E(j) = int", "5:int", "E(5) = int", "int := int", "B0:void", "j + 1:int", "E(j + 1) = int", "int := int",
              "void,void = void", "void = void", "void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Tests given two methods")
    void test_should_prove_typeSafe_when_given_multiMethods() {
      Stream<DynamicNode> tests = should_prove_typeSafe_when_given_multiMethods();
      int expectedNumTests = 30;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "method C.method1:void", "S0:void", "a:int", "E(a) = int", "0:int", "E(0) = int", "int := int", "B0:void", "a:int", "E(a) = int", "int := int",
              "void,void = void", "void = void", "class C:void", "method C.method2:void", "S0:void", "a:int", "E(a) = int", "1:int", "E(1) = int", "int := int", "B0:void", "a:int", "E(a) = int", "int := int",
              "void,void = void", "void = void", "void,void = void", "void = void"));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

    @Test
    @DisplayName("Test fail when givenAssignment")
    void test_shouldNot_proveTypeSafe_when_givenAssignment() {
      Stream<DynamicNode> tests = shouldNot_proveTypeSafe_when_givenAssignment();
      testExpectedUnsuccessfulTests(tests);
    }

    @Test
    @DisplayName("Test bool infix expression")
    void test_should_prove_typeSafe_when_given_BoolInfixExpression() {
      Stream<DynamicNode> tests = should_prove_typeSafe_when_given_InfixExpressionBool();
      int expectedNumTests = 38;
      List<String> displayNames = new ArrayList<>(Arrays.asList("CompilationUnit :void",
              "class C:void", "method C.method:void", "B0:void", "S0:void", "a:boolean", "E(a) = boolean",
              "true && true:boolean", "E(true && true) = boolean", "boolean := boolean", "S1:void", "b:boolean",
              "E(b) = boolean", "true || true:boolean", "E(true || true) = boolean", "boolean := boolean",
              "S2:void", "c:boolean", "E(c) = boolean", "1 == 1:boolean", "E(1 == 1) = boolean", "boolean := boolean",
              "S3:void", "d:boolean", "E(d) = boolean", "1 < 2:boolean", "E(1 < 2) = boolean", "boolean := boolean",
              "S4:void", "e:boolean", "E(e) = boolean", "2 > 1:boolean", "E(2 > 1) = boolean", "boolean := boolean",
              "void,void,void,void,void = void", "void = void", "void = void", "void = void"
      ));
      testExpectedSuccessfulTests(tests, expectedNumTests, displayNames);
    }

  }
}
