package io.nethy.util.object;


public final class ObjectPresenter {
  private static final char OPENING = '(';
  private static final char CLOSING = ')';
  private static final char VALUE_DELIMITER = '=';
  private static final char FIELD_DELIMITER = ',';

  private StringBuilder output = new StringBuilder(32);

  public static ObjectPresenter of(Object object) {
    Objects.requireNonNull(object);
    return new ObjectPresenter(object);
  }

  private ObjectPresenter(Object object) {
    Class<? extends Object> type = object.getClass();
    output.append(type.getSimpleName()).append(OPENING);
  }

  public ObjectPresenter append(String variableName, byte value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, short value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, int value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, long value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, float value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, double value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, boolean value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, char value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  public ObjectPresenter append(String variableName, Object value) {
    output.append(variableName).append(VALUE_DELIMITER).append(value).append(
      FIELD_DELIMITER);
    return this;
  }

  @Override
  public String toString() {
    int lastIndex = output.length() - 1;
    if (output.charAt(lastIndex) == FIELD_DELIMITER) {
      output.deleteCharAt(lastIndex);
    }
    return output.append(CLOSING).toString();
  }
}
