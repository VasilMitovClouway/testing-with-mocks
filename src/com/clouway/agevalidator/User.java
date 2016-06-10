package com.clouway.agevalidator;

/**
 * @Author Martin Milev
 * @Date 08.06.16.
 */
public class User {
  private final String name;
  private final String age;

  public User(String name, String age) {
    this.name = name;
    this.age = age;
  }

  public String age() {
    return age;
  }

  public String name() {
    return name;
  }
}
