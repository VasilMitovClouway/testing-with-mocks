import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class GatewayTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  @Mock
  private Validator validator;

  private Gateway gateway;

  @Test
  public void validSmsHappyPath() throws Exception {
    gateway = new Gateway(validator);
    Sms sms = new Sms("Title", "Some context of the sms", "0883323393");
    context.checking(new Expectations() {{
      oneOf(validator).check(sms);
      will(returnValue(true));
    }});
    assertThat(gateway.send(sms), is(true));
  }

  //Just to see if sms will not be send, if expectations are set to it not being valid.
  @Test
  public void tryingToSendSmsButItDoesntSend() throws Exception {
    gateway = new Gateway(validator);
    Sms sms = new Sms("Title", "Some context of the sms", "0883323393");
    context.checking(new Expectations() {{
      oneOf(validator).check(sms);
      will(returnValue(false));
    }});
    assertThat(gateway.send(sms), is(false));

  }
}
