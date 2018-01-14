package io.nethy.util;

import io.nethy.util.object.ObjectPresenter;
import io.nethy.util.object.Objects;

public class Lazy<T> implements Supplier<T> {

  private volatile Supplier<? extends T> supplier;
  private T value;

  public static <A> Lazy<A> of(Supplier<? extends A> supplier) {
    return new Lazy<>(supplier);
  }

  private Lazy(Supplier<? extends T> supplier) {
    this.supplier = Objects.requireNonNull(supplier);;
  }

  @Override
  public T get() {
    return isEvaluated() ? value : getValue();
  }

  private synchronized T getValue() {
    Supplier<? extends T> s = supplier;
    if (s != null) {
      value = s.get();
      supplier = null;
    }
    return value;
  }

  private boolean isEvaluated() {
    return supplier == null;
  }

  @Override
  public int hashCode() {
    return Objects
      .hash()
      .of(get())
      .get();
  }

  @Override
  public boolean equals(Object obj) {
    return (obj == this)
      || (obj instanceof Lazy
        && ((Lazy<?>) obj).get().equals(get()));
  }

  @Override
  public String toString() {
    return ObjectPresenter
      .of(this)
      .append("isEvaluated", isEvaluated())
      .append("value", value)
      .toString();
  }
}
