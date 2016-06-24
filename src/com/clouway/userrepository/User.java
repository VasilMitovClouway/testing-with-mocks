package com.clouway.userrepository;

import java.io.PrintStream;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
class User {
  private final String name;
  private final String age;

  public User(String name, String age) {
    this.name=name;
    this.age=age;
  }
  public String age(){
    return age;
  }

  public String name() {
    return name;
  }
}
