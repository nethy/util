package io.nethy.util.validation;

import java.util.List;

import io.nethy.util.F3;

public class ValidationBuilder3<A, B, C, E> extends ValidationBuilder {

  private final Validation<A, E> a;
  private final Validation<B, E> b;
  private final Validation<C, E> c;

  public ValidationBuilder3(
    Validation<A, E> a,
    Validation<B, E> b,
    Validation<C, E> c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public <D> ValidationBuilder4<A, B, C, D, E> and(Validation<D, E> other) {
    return new ValidationBuilder4<>(a, b, c, other);
  }

  public <D> Validation<D, List<E>> map(F3<A, B, C, D> f) {
    List<E> errors = collectErrors(a, b, c);

    if (errors.isEmpty()) {
      return Validation.valid(
        f.apply(
          a.get(),
          b.get(),
          c.get()));
    } else {
      return Validation.invalid(errors);
    }
  }
}
