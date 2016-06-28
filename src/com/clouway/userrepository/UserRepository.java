package com.clouway.userrepository;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
class UserRepository {
  private final UserDB userDB;
  private final Validator validator;

  public UserRepository(UserDB userDB, Validator validator) {
    this.userDB = userDB;
    this.validator = validator;
  }

  public void registerUser(User user) throws CannotRegisterAtThisTime, UserAlreadyExists {
    if(!userDB.checkForUserByName(user.name())) {
      if (validator.validateAge(user)) {
        if (userDB.add(user)) ;
        else
          throw new CannotRegisterAtThisTime();
      }
    }
    else
       throw new UserAlreadyExists();
  }


  public boolean isAdult(String name) throws UserNotRegistered {
    if(!userDB.checkForUserByName(name)){
      throw new UserNotRegistered();
    }
    Integer age = Integer.valueOf(userDB.requestUserInformation(name).age());
    if (age > 18) {
      return true;
    }
    return false;
  }

}
