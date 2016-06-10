package com.clouway.endpointFilter;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public interface Endpoint {
  boolean matches(String url);
}