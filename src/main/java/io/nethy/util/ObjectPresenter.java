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

public final class ObjectPresenter {
  private static final char OPENING = '{';
  private static final char CLOSING = '}';
  private static final char VALUE_DELIMITER = '=';
  private static final char FIELD_DELIMITER = ',';

  private StringBuilder output = new StringBuilder(32);

  public static ObjectPresenter of(Object object) {
    Objects.requireNonNull(object);
    return new ObjectPresenter(object);
  }

  private ObjectPresenter(Object object) {
    Class<? extends Object> type = object.getClass();
    output.append(type.getSimpleName())
          .append(OPENING);
  }

  public ObjectPresenter append(String variableName, byte value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, short value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, int value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, long value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, float value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, double value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, boolean value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, char value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, Object value) {
    output.append(variableName)
          .append(VALUE_DELIMITER)
          .append(value)
          .append(FIELD_DELIMITER);
    return this;
  }

  @Override
  public String toString() {
    int lastIndex = output.length() - 1;
    if (output.charAt(lastIndex) == FIELD_DELIMITER) {
      output.deleteCharAt(lastIndex);
    }
    return output.append(CLOSING)
                 .toString();
  }
}
