package com.clouway.smsender;

import com.clouway.smsender.Sms;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public interface Validator {
  boolean check(Sms sms);
}
