package com.clouway.sms;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class SmsValidator implements Validator{
  private final int minLength;
  private final int maxLength;

  public SmsValidator(int min,int max) {
    this.minLength = min;
    this.maxLength = max;
  }

  @Override
  public boolean validate(Sms sms) {
    if (!contentValidate(sms) || !numberValidate(sms.sender()) || !numberValidate(sms.receiver())) {
      return false;
    }
    return true;
  }

  private boolean contentValidate(Sms sms) {
    if(sms.content().length() > maxLength || sms.content().length() <= minLength) {
      return false;
    }
    if(sms.header().length() == 0 || sms.header().length() > 20){
      return false;
    }
    return true;
  }

  private boolean numberValidate(int[] number) {
    if(number.length != 10){
      return false;
    }
    for(int n : number){
      if(n > 10 || n < 0) {
        return false;
      }
    }
    return true;
  }
}
