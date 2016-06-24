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
  public void happyPathValidUser() throws Exception, CannotRegisterAtThisTime {
    User vasko = new User();
    context.checking(new Expectations() {{
      oneOf(validator).validateUserAge(vasko);
      will(returnValue(true));
      oneOf(userDB).add(vasko);
      will(returnValue(true));
    }});
    userRepository.registerUser(vasko);
  }

  //We receive a not valid user and it is not registered to the userDB
  @Test
  public void notValidUserIsNotRegistered() throws Exception, CannotRegisterAtThisTime {
    User youngBoy = new User();
    context.checking(new Expectations() {{
      oneOf(validator).validateUserAge(youngBoy);
      will(returnValue(false));
    }});
    userRepository.registerUser(youngBoy);
  }

  //The user is valid but there is a problem with ut userDB at this time.
  @Test(expected = CannotRegisterAtThisTime.class)
  public void validUserButCannotRegisterAtThisTime() throws Exception, CannotRegisterAtThisTime {
    User unluckyUser = new User();
    context.checking(new Expectations() {{
      oneOf(validator).validateUserAge(unluckyUser);
      will(returnValue(true));
      oneOf(userDB).add(unluckyUser);
      will(returnValue(false));
    }});
    userRepository.registerUser(unluckyUser);
  }
}

