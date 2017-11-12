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
import org.junit.Before;
import org.junit.Test;

public class ObjectPresenterTest {
  private static final String CLASS_NAME = "AClass";
  private static final String TEMPLATE = CLASS_NAME + "(%s)";

  public static ObjectPresenter newPresenter() {
    return ObjectPresenter.of(new AClass());
  }

  @Test
  public void whenClassHasNoFieldsThenOmitOpeningClosingBraces() {
    String toString = newPresenter().toString();

    Assert.assertEquals("AClass()", toString);
  }

  @Test
  public void whenClassContainsFiledThenPrint() throws Exception {
    testField(newPresenter().append("byte", (byte) 1), "byte=1");
    testField(newPresenter().append("short", (short) 1), "short=1");
    testField(newPresenter().append("long", 1L), "long=1");
    testField(newPresenter().append("int", 1), "int=1");
    testField(newPresenter().append("float", 1.1F), "float=1.1");
    testField(newPresenter().append("double", 1.1), "double=1.1");
    testField(newPresenter().append("boolean", true), "boolean=true");
    testField(newPresenter().append("char", 'b'), "char=b");
    testField(newPresenter().append("object", new AClass()), "object=AClass()");
  }

  @Test
  public void whenValueIsNullThenPrintNull() throws Exception {
    String toString = ObjectPresenter.of(new AClass())
                                     .append("nullField", null)
                                     .toString();

    Assert.assertEquals("AClass(nullField=null)", toString);
  }

  @Test
  public void whenClassContainsMultipleFields() throws Exception {
    testField(newPresenter().append("a", 1).append("b", "test"), "a=1,b=test");
  }

  @Test(expected = NullPointerException.class)
  public void whenInstanceIsNullThenThrowException() throws Exception {
    ObjectPresenter.of(null);
  }

  private void testField(ObjectPresenter presenter, String expected) {
    ObjectPresenter op = ObjectPresenter.of(new AClass());
    String toString = presenter.toString();

    Assert.assertEquals(String.format(TEMPLATE, expected), toString);
  }

  public static class AClass {
    @Override
    public String toString() {
      return ObjectPresenter.of(this).toString();
    }
  }
}
