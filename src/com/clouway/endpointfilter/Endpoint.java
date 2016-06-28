package com.clouway.endpointfilter;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public interface Endpoint {
  boolean matches(String url);
}
