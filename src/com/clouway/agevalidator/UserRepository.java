package com.clouway.agevalidator;

/**
 * @Author Martin Milev
 * @Date 10.06.16.
 */
public class UserRepository {
  private final UserDB userDB;
  private final Validator validator;

  public UserRepository(UserDB db, Validator v) {

    this.userDB = db;
    this.validator = v;
  }

  public void registerUser(User u) {
    if (validator.ageValidate(u.age())) {
      userDB.addUser(u);
    }
  }

  public boolean isAdult(String age) {
    return validator.userIsAdult(age);
  }
}
