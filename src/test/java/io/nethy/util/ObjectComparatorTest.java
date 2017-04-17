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

import org.junit.Assert;
import org.junit.Test;

public class ObjectComparatorTest {
  @Test
  public void whenOtherIsTheSameThenTrue() throws Exception {
    Object o = new Object();

    boolean isEqual = ObjectComparator.isEqual(o, o, (l, r) -> false);

    Assert.assertTrue(isEqual);
  }

  @Test
  public void whenOtherIsNullThenFalse() throws Exception {
    Object o = new Object();

    boolean isEqual = ObjectComparator.isEqual(o, null, (l, r) -> true);

    Assert.assertFalse(isEqual);
  }

  @Test
  public void whenArgsAreEqualsThenTrue() throws Exception {
    A a = new A(1);
    A other = new A(1);

    Assert.assertTrue(a != other);
    Assert.assertTrue(a.equals(other));
  }

  @Test
  public void whenArgsAreNotEqualsThenFalse() throws Exception {
    A a = new A(1);
    A other = new A(2);

    Assert.assertTrue(a != other);
    Assert.assertFalse(a.equals(other));
  }

  @Test
  public void whenSubclassAreEqualsThenTrue() throws Exception {
    A a = new A(1);
    B b = new B(1);

    Assert.assertTrue(a.equals(b));
    Assert.assertTrue(b.equals(a));
  }

  @Test
  public void whenTypeIsDifferentThenFalse() throws Exception {
    A a = new A(1);
    C c = new C(1);

    Assert.assertFalse(a.equals(c));
    Assert.assertFalse(c.equals(a));
  }

  @Test(expected = NullPointerException.class)
  public void whenCurrentArgumentIsNullThrowException() throws Exception {
    A a = new A(1);

    ObjectComparator.isEqual(null, a, (l, r) -> true);
  }

  @Test(expected = NullPointerException.class)
  public void whenComparisionIsNullThrowException() throws Exception {
    A a = new A(1);
    B b = new B(1);

    ObjectComparator.isEqual(a, b, null);
  }

  public static class A {
    int a;

    public A(int a) {
      this.a = a;
    }

    @Override
    public int hashCode() {
      return Objects.hash(a);
    }

    @Override
    public boolean equals(Object obj) {
      return ObjectComparator.isEqual(this, obj, (l, r) -> l.a == r.a);
    }
  }

  public static class B extends A {
    public B(int a) {
      super(a);
    }

    @Override
    public boolean equals(Object obj) {
      return ObjectComparator.isEqual(this, obj, (l, r) -> l.a == r.a);
    }
  }

  public static class C {
    int c;

    public C(int c) {
      this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
      return ObjectComparator.isEqual(this, obj, (l, r) -> l.c == r.c);
    }
  }
}
