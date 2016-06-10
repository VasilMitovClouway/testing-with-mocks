package com.clouway.sms;

/**
 * @Author Martin Milev
 * @Date 10.06.16.
 */
public class GateWay {
  private final Validator validator;
  private final Receiver receiver;

  public GateWay(Validator v, Receiver r) {
    this.validator = v;
    this.receiver = r;
  }

  public boolean send(Sms sms) throws Exception {
    if(validator.validate(sms)){
      return receiver.recieved(sms);
    }
    return false;
  }
}
