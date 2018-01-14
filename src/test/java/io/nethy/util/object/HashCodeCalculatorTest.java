package io.nethy.util.object;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HashCodeCalculatorTest {
  private static final int PRIME = 37;

  @Test
  public void of_boolean() throws Exception {
    assertThat(Objects.hash().of(false).get(), equalTo(PRIME));
    assertThat(Objects.hash().of(true).get(), equalTo(PRIME + 1));
  }

  @Test
  public void of_byte() throws Exception {
    assertThat(Objects.hash().of((byte) 5).get(), equalTo(PRIME + 5));
  }

  @Test
  public void of_char() throws Exception {
    assertThat(Objects.hash().of((char) 4).get(), equalTo(PRIME + 4));
  }

  @Test
  public void of_short() throws Exception {
    assertThat(Objects.hash().of((short) 3).get(), equalTo(PRIME + 3));
  }

  @Test
  public void of_int() throws Exception {
    assertThat(Objects.hash().of(2).get(), equalTo(PRIME + 2));
  }

  @Test
  public void of_long() throws Exception {
    assertThat(
      Objects.hash().of((long) Math.pow(2, 33)).get(),
      equalTo(PRIME + 2));
  }

  @Test
  public void of_float() throws Exception {
    assertThat(Objects.hash().of(2.3F).get(), equalTo(PRIME + 1075000115));
  }

  @Test
  public void of_double() throws Exception {
    assertThat(Objects.hash().of(2.3D).get(), equalTo(PRIME + 644087808));
  }

  @Test
  public void of_not_null() throws Exception {
    assertThat(
      Objects.hash().of("test").get(),
      equalTo(PRIME + "test".hashCode()));
  }

  @Test
  public void of_null() throws Exception {
    assertThat(Objects.hash().of(null).get(), equalTo(PRIME));
  }
}
