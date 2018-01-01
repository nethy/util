package io.nethy.util.object;

import io.nethy.util.BiPredicate;

public final class ObjectComparator {

  @SuppressWarnings("unchecked")
  public static <A> boolean isEqual(A current, Object other, BiPredicate<A, A> predicate) {
    Objects.requireNonNull(current);
    Objects.requireNonNull(predicate);
    boolean isEqual = false;
    if (current == other) {
      isEqual = true;
    } else if (other == null) {
      isEqual = false;
    } else if (isSubtype(current, other)) {
      isEqual = predicate.test(current, (A) other);
    } else if (isSubtype(other, current)) {
      isEqual = other.equals(current);
    }
    return isEqual;
  }

  private static boolean isSubtype(Object base, Object sub) {
    Class<? extends Object> baseType = base.getClass();
    Class<? extends Object> subType = sub.getClass();
    return baseType.isAssignableFrom(subType);
  }

  private ObjectComparator() {
    throw new Error("");
  }
}
