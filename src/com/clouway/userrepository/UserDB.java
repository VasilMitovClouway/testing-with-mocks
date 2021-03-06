package com.clouway.userrepository;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
interface UserDB {
  boolean add(User user);

  boolean checkForUserByName(String name);
  User requestUserInformation(String name);
}
