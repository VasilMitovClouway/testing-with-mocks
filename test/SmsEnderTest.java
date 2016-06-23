import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class SmsEnderTest {
  SmsEnder smsEnder = new SmsEnder();

  @Test
  public void validSmsHappyPath() throws Exception {
    Sms validSms = new Sms("Title", "Some context of the sms", "0883323393");
    assertThat(smsEnder.check(validSms), is(true));
  }

  @Test
  public void missingTitleOfSmsIsNotValid() throws Exception {
    Sms notValidSms = new Sms(/*Missing title*/"", "Some context of the sms", "0883323393");
    assertThat(smsEnder.check(notValidSms), is(false));
  }

  @Test
  public void missingContextOfSmsIsNotValid() throws Exception {
    Sms notValidSms = new Sms("Some Title",/*Missing context*/"", "0883323393");
    assertThat(smsEnder.check(notValidSms), is(false));
  }

  @Test
  public void missingNumberOfSmsIsNotValid() throws Exception {
    Sms notValidSms = new Sms("Some Title", "The Context is here",/*Missing number*/"");
    assertThat(smsEnder.check(notValidSms), is(false));
  }

  //Just in case.
  @Test
  public void checkingIfTwoThingsAreMissingSmsWillNotBeValid() throws Exception {
    Sms notValidSms = new Sms(/*Missing title*/"", "The Context is here",/*Missing number*/ "");
    assertThat(smsEnder.check(notValidSms), is(false));
  }

  //Numbers of receivers should be 10 digits long and starting with 08.
  @Test
  public void smsWithNotValidNumberWillNotBeValid() throws Exception {
    Sms notValidSmsWithTooShortNumber = new Sms("Some Title", "The Context is here", "0883");
    Sms notValidSmsWithNumberNotStartingWith08 = new Sms("Some Title", "The Context is here", "08833233931");
    Sms notValidSmsWithNumberContainingChars=new Sms("Some Title","Context of sms","0883a23393");
    assertThat(smsEnder.check(notValidSmsWithNumberNotStartingWith08), is(false));
    assertThat(smsEnder.check(notValidSmsWithTooShortNumber), is(false));
    assertThat(smsEnder.check(notValidSmsWithNumberContainingChars),is(false));
  }

  @Test
  public void smsContextWithLowerThan10Symbols() throws Exception {
    Sms sms = new Sms("Short sms", "<10", "0883323393");
    assertThat(smsEnder.check(sms), is(false));
  }

  @Test
  public void smsContextWithMoreThan100Symbols() throws Exception {
    Sms sms = new Sms("Too long sms", "Ten SymbolTen SymbolTen SymbolTen SymbolTen SymbolTen SymbolTen SymbolTen SymbolTen SymbolTen Symbol1", "0883323393");
    assertThat(smsEnder.check(sms), is(false));
  }

}
