package io.nethy.util.object;

public final class HashCodeCalculator {
  private static final int PRIME = 37;

  private int result = 1;

  public HashCodeCalculator of(boolean b) {
    addPartialValue(b ? 1 : 0);
    return this;
  }

  public HashCodeCalculator of(byte b) {
    addPartialValue(b);
    return this;
  }

  public HashCodeCalculator of(char c) {
    addPartialValue(c);
    return this;
  }

  public HashCodeCalculator of(short s) {
    addPartialValue(s);
    return this;
  }

  public HashCodeCalculator of(int a) {
    addPartialValue(a);
    return this;
  }

  public HashCodeCalculator of(long l) {
    addPartialValue(foldBits(l));
    return this;
  }

  public HashCodeCalculator of(float f) {
    addPartialValue(Float.floatToIntBits(f));
    return this;
  }

  public HashCodeCalculator of(double d) {
    addPartialValue(foldBits(Double.doubleToLongBits(d)));
    return this;
  }

  public HashCodeCalculator of(Object a) {
    addPartialValue(a != null ? a.hashCode() : 0);
    return this;
  }

  public int get() {
    return result;
  }

  private void addPartialValue(int partial) {
    result = PRIME * result + partial;
  }

  private int foldBits(long l) {
    return (int) (l ^ (l >>> 32));
  }
}
