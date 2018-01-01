package io.nethy.util.validation;

import java.util.List;

import io.nethy.util.F2;

public class ValidationBuilder2<A, B, E> extends ValidationBuilder {

  private Validation<A, E> a;
  private Validation<B, E> b;

  public ValidationBuilder2(Validation<A, E> a, Validation<B, E> b) {
    this.a = a;
    this.b = b;
  }

  public <C> ValidationBuilder3<A, B, C, E> and(Validation<C, E> other) {
    return new ValidationBuilder3<>(a, b, other);
  }

  @SuppressWarnings("unchecked")
  public <C> Validation<C, List<E>> map(F2<A, B, C> f) {
    List<E> errors = collectErrors(a, b);

    if (errors.isEmpty()) {
      return Validation.valid(f.apply(a.get(),
                                      b.get()));
    } else {
      return Validation.invalid(errors);
    }
  }
}