package com.clouway.userrepository;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class RealValidator implements Validator {
  @Override
  public boolean validateAge(User user) {
    Integer userAge = Integer.valueOf(user.age());
    if (userAge < 10||userAge>100) {
      return false;
    }
    return true;
  }
}
