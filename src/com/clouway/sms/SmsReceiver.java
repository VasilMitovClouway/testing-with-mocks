package com.clouway.sms;

/**
 * @Author Martin Milev
 * @Date 08.06.16.
 */
public class SmsReceiver implements Receiver {
  @Override
  public boolean recieved(Sms sms) throws Exception {
    if(sms.sender() == sms.receiver()){
      return false;
    }
    return true;
  }
}
