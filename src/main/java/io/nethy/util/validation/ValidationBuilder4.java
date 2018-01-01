package io.nethy.util.validation;

import java.util.List;

import io.nethy.util.F4;

public class ValidationBuilder4<A, B, C, D, E> extends ValidationBuilder {

  private final Validation<A, E> a;
  private final Validation<B, E> b;
  private final Validation<C, E> c;
  private final Validation<D, E> d;

  public ValidationBuilder4(Validation<A, E> a,
                            Validation<B, E> b,
                            Validation<C, E> c,
                            Validation<D, E> d) {
                              this.a = a;
                              this.b = b;
                              this.c = c;
                              this.d = d;
  }

  @SuppressWarnings("unchecked")
  public <F> Validation<F, List<E>> map(F4<A, B, C, D, F> f) {
    List<E> errors = collectErrors(a, b, c, d);

    if (errors.isEmpty()) {
      return Validation.valid(f.apply(a.get(),
                                      b.get(),
                                      c.get(),
                                      d.get()));
    } else {
      return Validation.invalid(errors);
    }
  }
}
