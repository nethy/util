package io.nethy.util;

public final class Objects {

  private Objects() {
    throw new Error("Must not be instantiated!");
  }

  public static <A> A requireNonNull(A a) {
    if (a == null) {
      throw new NullPointerException();
    }
    return a;
  }

  public static HashCodeCalculator hash() {
    return new HashCodeCalculator();
  }
}
