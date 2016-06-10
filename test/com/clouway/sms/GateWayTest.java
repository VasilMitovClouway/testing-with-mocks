package com.clouway.sms;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class GateWayTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator validator;

  @Mock
  Receiver receiver;

  GateWay gateWay;

  @Before
  public void setUp(){
    gateWay = new GateWay(validator,receiver);
  }

  @Test
  public void successfulSendSms() throws Exception {
    Sms sms = new Sms("Hello Word!", "hello", new int[]{}, new int[]{});
    context.checking(new Expectations(){{
      oneOf(validator).validate(sms);
      will(returnValue(true));

      oneOf(receiver).recieved(sms);
      will(returnValue(true));
    }});
    assertThat(gateWay.send(sms), is(true));
  }

  @Test
  public void failedSendSms() throws Exception {
    Sms sms = new Sms("Hello Word!", "hello", new int[]{}, new int[]{}  );
    context.checking(new Expectations(){{
      oneOf(validator).validate(sms);
      will(returnValue(true));

      oneOf(receiver).recieved(sms);
      will(returnValue(false));
    }});
    assertThat(gateWay.send(sms), is(false));
  }
}
