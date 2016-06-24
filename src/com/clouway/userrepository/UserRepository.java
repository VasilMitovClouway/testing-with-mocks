package com.clouway.userrepository;

import java.util.LinkedHashMap;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
class UserRepository {
  private final UserDB userDB;
  private final Validator validator;
  private LinkedHashMap<String, User> validUsers = new LinkedHashMap<>();

  public UserRepository(UserDB userDB, Validator validator) {
    this.userDB = userDB;
    this.validator = validator;
  }

  public void registerUser(User user) throws CannotRegisterAtThisTime {
    if (validator.validateUserAge(user)) {
      if (userDB.add(user)) ;
      else
        throw new CannotRegisterAtThisTime();


    }
  }


  public boolean isAdult(String name) {
    return false;
  }

}
