package io.nethy.util.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ValidationBuilder {
  @SafeVarargs
  protected static <A> List<A> collectErrors(Validation<?, A>... validations) {
    List<A> errors = null;
    for (Validation<?, A> v : validations) {
      if (!v.isValid()) {
        if (errors == null) {
          errors = new ArrayList<>();
        }
        errors.add(v.getError());
      }
    }
    if (errors == null) {
      errors = Collections.emptyList();
    }
    return errors;
  }
}
