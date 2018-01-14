package io.nethy.util.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.NoSuchElementException;

import io.nethy.util.F1;
import io.nethy.util.F2;
import io.nethy.util.F3;
import io.nethy.util.F4;
import org.junit.Test;

public class ValidationTest {
  private static final String INVALID = "invalid";
  private static final F1<Integer, Integer> INC1 = new F1<Integer, Integer>() {
    @Override
    public Integer apply(Integer a) {
      return a + 1;
    }
  };
  private static final F2<Integer, Integer, Integer> INC2 =
    new F2<Integer, Integer, Integer>() {
      @Override
      public Integer apply(Integer a, Integer b) {
        return a + b + 1;
      }
    };
  private static final F3<
    Integer,
    Integer,
    Integer,
    Integer> INC3 = new F3<Integer, Integer, Integer, Integer>() {
      @Override
      public Integer apply(Integer a, Integer b, Integer c) {
        return a + b + c + 1;
      }
    };
  private static final F4<
    Integer,
    Integer,
    Integer,
    Integer,
    Integer> INC4 = new F4<Integer, Integer, Integer, Integer, Integer>() {
      @Override
      public Integer apply(Integer a, Integer b, Integer c, Integer d) {
        return a + b + c + d + 1;
      }
    };
  private static final F1<String, Integer> LENGTH = new F1<String, Integer>() {
    @Override
    public Integer apply(String a) {
      return a.length() + 1;
    }
  };


  @Test
  public void getValue_valid() {
    assertThat(validate(2).get()).isEqualTo(2);
  }

  @Test(expected = NoSuchElementException.class)
  public void getValue_invalid() throws Exception {
    validate(1).get();
  }

  @Test
  public void getError_invalid() throws Exception {
    assertThat(validate(1).getError()).isEqualTo(INVALID);
  }

  @Test(expected = NoSuchElementException.class)
  public void getError_valid() throws Exception {
    validate(2).getError();
  }

  @Test
  public void isValid() throws Exception {
    assertThat(validate(2).isValid()).isTrue();
    assertThat(validate(1).isValid()).isFalse();
  }

  @Test
  public void reduce_valid() throws Exception {
    assertThat(validate(2).reduce(INC1, LENGTH)).isEqualTo(3);
  }

  @Test
  public void reduce_invalid() throws Exception {
    assertThat(validate(1).reduce(INC1, LENGTH))
      .isEqualTo(INVALID.length() + 1);
  }

  @Test
  public void map_single_valid() throws Exception {
    Validation<Integer, String> validation = validate(2).map(INC1);

    assertThat(validation.get()).isEqualTo(3);
  }

  @Test
  public void map_single_invalid() throws Exception {
    Validation<Integer, String> validation = validate(1).map(INC1);

    assertThat(validation.getError()).isEqualTo(INVALID);
  }

  @Test
  public void map_double_valid() throws Exception {
    Validation<Integer, List<String>> validation =
      validate(2).and(validate(2)).map(INC2);

    assertThat(validation.get()).isEqualTo(5);
  }

  @Test
  public void map_double_invalid() throws Exception {
    Validation<Integer, List<String>> validation =
      validate(1).and(validate(1)).map(INC2);

    assertThat(validation.getError()).containsExactly(INVALID, INVALID);
  }

  @Test
  public void map_triple_valid() throws Exception {
    Validation<
      Integer,
      List<String>> validation =
        validate(2).and(validate(2)).and(validate(2)).map(INC3);

    assertThat(validation.get()).isEqualTo(7);
  }

  @Test
  public void map_triple_invalid() throws Exception {
    Validation<
      Integer,
      List<String>> validation =
        validate(1).and(validate(1)).and(validate(1)).map(INC3);

    assertThat(validation.getError())
      .containsExactly(INVALID, INVALID, INVALID);
  }

  @Test
  public void map_quadruple_valid() throws Exception {
    Validation<
      Integer,
      List<String>> validation =
        validate(2)
          .and(validate(2))
          .and(validate(2))
          .and(validate(2))
          .map(INC4);

    assertThat(validation.get()).isEqualTo(9);
  }

  @Test
  public void map_quadruple_invalid() throws Exception {
    Validation<
      Integer,
      List<String>> validation =
        validate(1)
          .and(validate(1))
          .and(validate(1))
          .and(validate(1))
          .map(INC4);

    assertThat(validation.getError())
      .containsExactly(INVALID, INVALID, INVALID, INVALID);
  }

  private static Validation<Integer, String> validate(int i) {
    if (i == 2)
      return Validation.valid(i);
    else
      return Validation.invalid(INVALID);
  }
}
