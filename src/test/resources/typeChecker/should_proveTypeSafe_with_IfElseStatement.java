package typeChecker;

public class C {
  void method() {
    int i = 1;

    if (true) {
      i = 2;
    }
    else {
      i = 0;
    }
  }
}
