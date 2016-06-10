package com.clouway.agevalidator;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author Martin Milev
 * @Date 09.06.16.
 */
public class UserDBTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator validator;

  @Mock
  UserDB db;

  User user;
  UserRepository userRepo;

  @Before
  public void setUp() {
    user = new User("Alex", "100");
    userRepo = new UserRepository(db, validator);
  }

  @Test
  public void addUserSuccessful() throws Exception {
    context.checking(new Expectations() {{
      oneOf(validator).ageValidate(user.age());
      will(returnValue(true));

      oneOf(db).addUser(user);
    }});
    userRepo.registerUser(user);
  }

  @Test
  public void addUserFailed() throws Exception {
    context.checking(new Expectations() {{
      oneOf(validator).ageValidate(user.age());
      will(returnValue(false));

      never(db).addUser(user);
    }});
    userRepo.registerUser(user);
  }

  @Test
  public void userIsAdult() throws Exception {
    context.checking(new Expectations() {{
      oneOf(validator).userIsAdult(user.age());
      will(returnValue(true));
    }});
    assertThat(userRepo.isAdult(user.age()), is(true));
  }

  @Test
  public void userNotIsAdult() throws Exception {
    context.checking(new Expectations() {{
      oneOf(validator).userIsAdult(user.age());
      will(returnValue(false));
    }});
    assertThat(userRepo.isAdult(user.age()), is(false));
  }
}

