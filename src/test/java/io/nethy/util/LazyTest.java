package io.nethy.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LazyTest {

  private MutableSupplier mutable = new MutableSupplier();
  private Lazy<Boolean> lazy = Lazy.of(mutable);

  @Test(expected = NullPointerException.class)
  public void constructor_supplier_is_null() throws Exception {
    Lazy.of(null);
  }

  @Test
  public void constructor_does_not_invoke_supplier() throws Exception {
    assertThat(mutable.isInvoked(), is(false));
  }

  @Test
  public void get_invokes_supplier() throws Exception {
    Boolean value = lazy.get();

    assertThat(value, equalTo(true));
    assertThat(mutable.isInvoked(), equalTo(value));
  }

  public static class MutableSupplier implements Supplier<Boolean> {

    private Boolean b = false;

    @Override
    public Boolean get() {
      b = true;
      return b;
    }

    public Boolean isInvoked() {
      return b;
    }
  }
}
