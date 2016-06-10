package com.clouway.sms;

/**
 * @Author Martin Milev
 * @Date 08.06.16.
 */
public interface Receiver {
  public boolean recieved(Sms sms) throws Exception;
}
