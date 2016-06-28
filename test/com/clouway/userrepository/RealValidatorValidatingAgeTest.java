package com.clouway.userrepository;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class RealValidatorValidatingAgeTest {
  RealValidator realValidator = new RealValidator();

  @Test
  public void validUser() throws Exception {
    User validUser = new User("Valid", "10");
    assertThat(realValidator.validateAge(validUser), is(true));
  }

  @Test
  public void userWithLowerThan10YearsIsNotValid() throws Exception {
    User youngBoy=new User("Little Timmy","9");
    assertThat(realValidator.validateAge(youngBoy),is(false));
  }

  @Test
  public void userWithHighterThan100YearsIsNotValid() throws Exception {
    User myGrandGrandFather=new User("Alfred","101");
    assertThat(realValidator.validateAge(myGrandGrandFather),is(false));
  }
}
