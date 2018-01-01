package io.nethy.util.validation;

import java.util.NoSuchElementException;

import io.nethy.util.F1;

public abstract class Validation<A, E> {

  @SuppressWarnings("unchecked")
  public static <A, E> Validation<A, E> valid(A value) {
    return (Validation<A, E>) new Valid<>(value);
  }

  @SuppressWarnings("unchecked")
  public static <A, E> Validation<A, E> invalid(E error) {
    return (Validation<A, E>) new Invalid<>(error);
  }

  public <B> ValidationBuilder2<A, B, E> and(Validation<B, E> other) {
    return new ValidationBuilder2<>(this, other);
  }

  public abstract boolean isValid();

  public abstract A get();

  public abstract E getError();

  public abstract <B> Validation<B, E> map(F1<A, B> f);

  public abstract <B> B reduce(F1<A, B> fValue, F1<E, B> fError);

  private static class Valid<A> extends Validation<A, Object> {

    public final A value;

    public Valid(A value) {
      this.value = value;
    }

    @Override
    public boolean isValid() {
      return true;
    }

    @Override
    public A get() {
      return value;
    }

    @Override
    public Object getError() {
      throw new NoSuchElementException();
    }

    @Override
    public <B> Validation<B, Object> map(F1<A, B> f) {
      return Validation.valid(f.apply(value));
    }

    @Override
    public <B> B reduce(F1<A, B> fValue, F1<Object, B> fError) {
      return fValue.apply(value);
    }
  }

  private static class Invalid<E> extends Validation<Object, E> {

    private E error;

    public Invalid(E error) {
      this.error = error;
    }

    @Override
    public boolean isValid() {
      return false;
    }

    @Override
    public Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public E getError() {
      return error;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <B> Validation<B, E> map(F1<Object, B> f) {
      return (Validation<B, E>) this;
    }

    @Override
    public <B> B reduce(F1<Object, B> fValue, F1<E, B> fError) {
      return fError.apply(error);
    }
  }
}
