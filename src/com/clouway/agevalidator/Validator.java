package com.clouway.agevalidator;

/**
 * @Author Martin Milev
 * @Date 08.06.16.
 */
public interface Validator {
  boolean ageValidate(String age);
  boolean userIsAdult(String age);
}
