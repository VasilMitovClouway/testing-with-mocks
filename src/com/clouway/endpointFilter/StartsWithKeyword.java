package com.clouway.endpointFilter;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class StartsWithKeyword implements Endpoint {
  private String keyword;

  public StartsWithKeyword(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public boolean matches(String url) {
    if("".equals(url) || "".equals(keyword)){
      return false;
    }

    if(!url.startsWith(keyword)) {
      return false;
    }
    return true;
  }
}
