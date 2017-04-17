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

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {
  private volatile transient Supplier<? extends T> supplier;
  private T value;

  public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
    Objects.requireNonNull(supplier);
    return new Lazy<>(supplier);
  }

  private Lazy(Supplier<? extends T> supplier) {
    this.supplier = supplier;
  }

  @Override
  public T get() {
    return supplier == null ? value : getValue();
  }

  private synchronized T getValue() {
    Supplier<? extends T> s = supplier;
    if (s != null) {
      value = s.get();
      supplier = null;
    }
    return value;
  }

  private boolean isEvaulated() {
    return supplier == null;
  }

  @Override
  public int hashCode() {
    return Objects.hash(get());
  }

  @Override
  public boolean equals(Object obj) {
    return (obj == this)
        || (obj instanceof Lazy
            && Objects.equals(((Lazy<?>) obj).get(), get()));
  }

  @Override
  public String toString() {
    return ObjectPresenter.of(this)
                          .append("isEvaulated", isEvaulated())
                          .append("value", value)
                          .toString();
  }

  private void writeObject(ObjectOutputStream s) throws IOException {
    get();
    s.defaultWriteObject();
  }
}
