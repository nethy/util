package io.nethy.util.object;

import org.junit.Assert;
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
    String toString =
      ObjectPresenter
        .of(new AClass())
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
    Assert
      .assertEquals(String.format(TEMPLATE, expected), presenter.toString());
  }

  public static class AClass {
    @Override
    public String toString() {
      return ObjectPresenter.of(this).toString();
    }
  }
}
