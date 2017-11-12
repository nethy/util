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

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObjectComparatorTest {

  @Test
  public void isEqual_same_instances() throws Exception {
    A a = new A(1);

    assertThat(ObjectComparator.isEqual(a, a, alwaysFalse), is(true));
  }

  @Test
  public void isEqual_other_is_null() throws Exception {
    A a = new A(1);

    assertThat(ObjectComparator.isEqual(a, null, alwaysTrue), is(false));
  }

  @Test
  public void isEqual_different_instances() throws Exception {
    A a1 = new A(1);
    A a2 = new A(2);

    assertThat(ObjectComparator.isEqual(a1, a2, alwaysTrue), is(true));
    assertThat(ObjectComparator.isEqual(a1, a2, alwaysFalse), is(false));
  }

  @Test
  public void isEqual_other_is_a_subtype() throws Exception {
    A a = new A(1);
    B b = new B(1);


    assertThat(ObjectComparator.isEqual(a, b, aPredicate), is(true));
  }

  @Test
  public void isEqual_other_is_a_supertype() throws Exception {
    A a = new A(1);
    B b = new B(1);

    assertThat(ObjectComparator.isEqual(b, a, alwaysFalse), is(true));
  }

  @Test
  public void isEqual_other_is_different_type() throws Exception {
    A a = new A(1);
    C c = new C(1);

    assertThat(ObjectComparator.isEqual(a, c, aPredicate), is(false));
  }

  private BiPredicate<A, A> alwaysTrue = new BiPredicate<A, A>() {
    @Override
    public boolean test(A a, A a2) {
      return true;
    }
  };

  private BiPredicate<A, A> alwaysFalse = new BiPredicate<A, A>() {
    @Override
    public boolean test(A a, A a2) {
      return false;
    }
  };

  private BiPredicate<A, A> aPredicate = new BiPredicate<A, A>() {
    @Override
    public boolean test(A a, A a2) {
      return a.a == a2.a;
    }
  };

  public static class A {
    int a;

    public A(int a) {
      this.a = a;
    }

    @Override
    public int hashCode() {
      return new HashCodeCalculator().of(a).get();
    }

    @Override
    public boolean equals(Object obj) {
      return ObjectComparator.isEqual(this, obj, new BiPredicate<A, A>() {
        @Override
        public boolean test(A a, A a2) {
          return a.a == a2.a;
        }
      });
    }
  }

  public static class B extends A {
    public B(int a) {
      super(a);
    }

    @Override
    public boolean equals(Object obj) {
      return ObjectComparator.isEqual(this, obj, new BiPredicate<B, B>() {
        @Override
        public boolean test(B b, B b2) {
          return b.a == b2.a;
        }
      });
    }
  }

  public static class C {
    int c;

    public C(int c) {
      this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
      return ObjectComparator.isEqual(this, obj, new BiPredicate<C, C>() {
        @Override
        public boolean test(C c, C c2) {
          return c.c == c2.c;
        }
      });
    }
  }
}
