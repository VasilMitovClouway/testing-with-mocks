package com.clouway.smsender;

import com.clouway.smsender.Gateway;
import com.clouway.smsender.Sender;
import com.clouway.smsender.Sms;
import com.clouway.smsender.UnableToSendSmsAtTheMoment;
import com.clouway.smsender.Validator;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class GatewaySendsSmsTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  Validator validator=context.mock(Validator.class);

  Sender sender=context.mock(Sender.class);

  private Gateway gateway=new Gateway(validator,sender);



  @Test
  public void validSmsHappyPath() throws Exception, UnableToSendSmsAtTheMoment {
    Sms sms = new Sms("Title", "Some context of the sms", "0883323393");
    context.checking(new Expectations() {{
      oneOf(validator).check(sms);
      will(returnValue(true));
      oneOf(sender).send(sms);
      will(returnValue(true));
    }});
    assertThat(gateway.send(sms), is(true));
  }


  @Test
  public void smsIsNotValidAndItWillNotBeSend() throws Exception, UnableToSendSmsAtTheMoment {
    Sms sms = new Sms("Title", "Some context of the sms", "0883323393");
    context.checking(new Expectations() {{
      oneOf(validator).check(sms);
      will(returnValue(false));

    }});
    assertThat(gateway.send(sms), is(false));
  }

  @Test(expected = UnableToSendSmsAtTheMoment.class)
  public void smsIsValidButThereIsAProblemWithGateway() throws Exception, UnableToSendSmsAtTheMoment {
    Sms sms = new Sms("Title of com.clouway.smsender.Sms", "Some context of the sms that is here", "0883323393");
    context.checking(new Expectations() {{
      oneOf(validator).check(sms);
      will(returnValue(true));
      oneOf(sender).send(sms);
      will(returnValue(false));
    }});
    gateway.send(sms);
  }
}
