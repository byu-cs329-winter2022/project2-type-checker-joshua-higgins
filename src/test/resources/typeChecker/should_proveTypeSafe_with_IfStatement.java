package typeChecker;

public class C {
  void method() {
    boolean isFive = false;

    if (true) {
      isFive = true;
    }
    isFive = false;
  }
}
