/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2017 Gabor Nemeth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package io.nethy.util;

import java.util.Objects;
import java.util.function.BiPredicate;

public final class ObjectComparator<T> {

  @SuppressWarnings("unchecked")
  public static <T> boolean isEqual(T current,
                                    Object other,
                                    BiPredicate<T, T> comparision) {
    Objects.requireNonNull(current);
    Objects.requireNonNull(comparision);
    boolean isEqual = false;
    if (current == other) {
      isEqual = true;
    } else if (other == null) {
      isEqual = false;
    } else if (isSubtype(current, other)) {
      isEqual = comparision.test(current, (T) other);
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
