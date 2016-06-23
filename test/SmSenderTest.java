import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class SmSenderTest {
  SmSender smSender = new SmSender();

  @Test
  public void validSmsHappyPath() throws Exception {
    Sms validSms = new Sms("Title", "Some context of the sms", "0883323393");
    assertThat(smSender.checkContentOf(validSms), is(true));
  }

  @Test
  public void missingTitleOfSmsIsNotValid() throws Exception {
    Sms notValidSms = new Sms(/*Missing title*/"", "Some context of the sms", "0883323393");
    assertThat(smSender.checkContentOf(notValidSms), is(false));
  }

  @Test
  public void missingContextOfSmsIsNotValid() throws Exception {
    Sms notValidSms = new Sms("Some Title",/*Missing context*/"", "0883323393");
    assertThat(smSender.checkContentOf(notValidSms), is(false));
  }

  @Test
  public void missingNumberOfSmsIsNotValid() throws Exception {
    Sms notValidSms = new Sms("Some Title", "The Context is here",/*Missing number*/"");
    assertThat(smSender.checkContentOf(notValidSms), is(false));
  }

  //Just in case.
  @Test
  public void checkingIfTwoThingsAreMissingSmsWillNotBeValid() throws Exception {
    Sms notValidSms = new Sms(/*Missing title*/"", "The Context is here",/*Missing number*/ "");
    assertThat(smSender.checkContentOf(notValidSms), is(false));
  }

  //Numbers of receivers should be 10 digits long and starting with 08.
  @Test
  public void smsWithNotValidNumberWillNotBeValid() throws Exception {
    Sms notValidSmsWithTooShortNumber = new Sms("Some Title", "The Context is here", "0883");
    Sms notValidSmsWithNumberNotStartingWith08 = new Sms("Some Title", "The Context is here","08833233931");
    assertThat(smSender.checkContentOf(notValidSmsWithNumberNotStartingWith08), is(false));
    assertThat(smSender.checkContentOf(notValidSmsWithTooShortNumber), is(false));
  }
}
