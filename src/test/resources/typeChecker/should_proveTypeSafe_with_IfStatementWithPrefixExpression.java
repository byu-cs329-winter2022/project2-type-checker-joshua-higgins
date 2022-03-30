package typeChecker;

public class C {
  void method() {
    boolean isTrue = false;

    if (!false) {
      isTrue = true;
    }
  }
}
