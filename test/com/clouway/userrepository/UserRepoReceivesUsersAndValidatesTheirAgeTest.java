package com.clouway.userrepository;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;


import java.util.LinkedHashMap;


/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class UserRepoReceivesUsersAndValidatesTheirAgeTest {
  JUnitRuleMockery context = new JUnitRuleMockery();

  //Collaborators
  UserDB userDB = context.mock(UserDB.class);
  Validator validator = context.mock(Validator.class);

  //Object under test
  UserRepository userRepository = new UserRepository(userDB, validator);

  //We receive a valid user and it is registered to the userDB.
  @Test
  public void happyPathValidUser() throws Exception, CannotRegisterAtThisTime, UserAlreadyExists {
    User vasko = new User("Vasko","24");
    context.checking(new Expectations() {{
      oneOf(userDB).checkForUserByName("Vasko");
      will(returnValue(false));
      oneOf(validator).validateUserAge(vasko);
      will(returnValue(true));
      oneOf(userDB).add(vasko);
      will(returnValue(true));
    }});
    userRepository.registerUser(vasko);
  }

  //We receive a not valid user and it is not registered to the DB.
  @Test
  public void notValidUserIsNotRegistered() throws Exception, CannotRegisterAtThisTime, UserAlreadyExists {
    User youngBoy = new User("Timmy","9");
    context.checking(new Expectations() {{
      oneOf(userDB).checkForUserByName("Timmy");
      will(returnValue(false));
      oneOf(validator).validateUserAge(youngBoy);
      will(returnValue(false));
    }});
    userRepository.registerUser(youngBoy);
  }
//The user we are trying to register already exists in the DB.
  @Test(expected = UserAlreadyExists.class)
  public void userAlreadyExistsInDB() throws Exception, CannotRegisterAtThisTime, UserAlreadyExists {
    User alredyRegistered=new User("Timmy Two Times","22");
    context.checking(new Expectations(){{
      oneOf(userDB).checkForUserByName("Timmy Two Times");
      will(returnValue(true));
    }});
    userRepository.registerUser(alredyRegistered);

  }

  //The user is valid but there is a problem with ut userDB at this time.
  @Test(expected = CannotRegisterAtThisTime.class)
  public void validUserButCannotRegisterAtThisTime() throws Exception, CannotRegisterAtThisTime, UserAlreadyExists {
    User unluckyUser = new User("Jinx","22");
    context.checking(new Expectations() {{
      oneOf(userDB).checkForUserByName("Jinx");
      will(returnValue(false));
      oneOf(validator).validateUserAge(unluckyUser);
      will(returnValue(true));
      oneOf(userDB).add(unluckyUser);
      will(returnValue(false));
    }});
    userRepository.registerUser(unluckyUser);
  }
}

