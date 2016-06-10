package com.clouway.sms;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class ValidationTest {
  SmsValidator validator;

  @Before
  public void setUp(){
    validator = new SmsValidator(0, 120);
  }

  @Test
  public void happyPath() throws Exception {
    Sms sms = new Sms("Hello Word!", "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6,5,4,3,2,1});
    assertThat(validator.validate(sms), is(true));
  }

  @Test
  public void missingContent() throws Exception {
    Sms sms = new Sms("", "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6,5,4,3,2,1});
    assertThat(validator.validate(sms), is(false));
  }

  @Test
  public void messageTooLong() throws Exception {
    Sms sms = new Sms("Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!" +
            "Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!Hello Word!",
            "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6,5,4,3,2,1});
    assertThat(validator.validate(sms), is(false));
  }

  @Test
  public void missingTitle() throws Exception {
    Sms sms = new Sms("Hello Word!", "", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6,5,4,3,2,1});
    assertThat(validator.validate(sms), is(false));
  }

  @Test
  public void titleTooLong() throws Exception {
    Sms sms = new Sms("Hello Word!", "hello hello hello hello hello hello hello hello hello hello hello hello ",
            new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6,5,4,3,2,1});
    assertThat(validator.validate(sms), is(false));
  }

  @Test
  public void invalidNumber() throws Exception {
    Sms sms1 = new Sms("Hello Word!", "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{});
    Sms sms2 = new Sms("Hello Word!", "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,89,888});
    Sms sms3 = new Sms("Hello Word!", "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6});
    Sms sms4 = new Sms("Hello Word!", "hello", new int[]{0,8,9,7,6,5,4,3,2,1}, new int[]{0,8,9,7,6,5,4,3,2,1,9,8,7,6,5,4});
    assertThat(validator.validate(sms1), is(false));
    assertThat(validator.validate(sms2), is(false));
    assertThat(validator.validate(sms3), is(false));
    assertThat(validator.validate(sms4), is(false));
  }
}
