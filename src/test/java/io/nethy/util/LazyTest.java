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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
