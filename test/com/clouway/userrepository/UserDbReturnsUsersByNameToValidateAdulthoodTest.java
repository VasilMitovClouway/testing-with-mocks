package com.clouway.userrepository;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class UserDbReturnsUsersByNameToValidateAdulthoodTest {

  JUnitRuleMockery context = new JUnitRuleMockery();

  //Collaborator
  UserDB userDB = context.mock(UserDB.class);
  Validator validator = context.mock(Validator.class);
  //Object under test
  UserRepository userRepository = new UserRepository(userDB, validator);

  //User exists in DB and he is an Adult.
  @Test
  public void happyPathUserExistsAndHeIsAnAdult() throws Exception, UserNotRegistered {
    User vasko = new User("Vasko", "24");
    context.checking(new Expectations() {{
      oneOf(userDB).checkForUserByName("Vasko");
      will(returnValue(true));
      oneOf(userDB).requestUserInformation("Vasko");
      will(returnValue(vasko));
    }});
    assertThat(userRepository.isAdult("Vasko"), is(true));
  }

  //User exists in DB but he is not an adult.
  @Test
  public void userExistsButHeIsNotAdult() throws Exception, UserNotRegistered {
    User youngBoy = new User("Timmy", "9");
    context.checking(new Expectations() {{
      oneOf(userDB).checkForUserByName("Timmy");
      will(returnValue(true));
      oneOf(userDB).requestUserInformation("Timmy");
      will(returnValue(youngBoy));
    }});
    assertThat(userRepository.isAdult("Timmy"), is(false));
  }
//User doesn't exist in DB throws an exception.
  @Test(expected = UserNotRegistered.class)
  public void requestUserThatDoseNotExistInTheDB() throws Exception, UserNotRegistered {
    context.checking(new Expectations() {{
      oneOf(userDB).checkForUserByName("He is not here");
      will(returnValue(false));
    }});
    userRepository.isAdult("He is not here");

  }
}
