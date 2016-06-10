package com.clouway.com.clouway.endpoitFilter;

import com.clouway.endpointFilter.EndpointFilter;
import com.clouway.endpointFilter.StartsWithKeyword;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class StartsWithKeywordTest {
  @Test
  public void happyPath() throws Exception {
    StartsWithKeyword keyword1 = new StartsWithKeyword("/login");
    StartsWithKeyword keyword2 = new StartsWithKeyword("/index");
    StartsWithKeyword keyword3 = new StartsWithKeyword("/logout");

    EndpointFilter filter = new EndpointFilter(keyword1, keyword2, keyword3);

    assertThat(filter.shouldFilter("/login"), is(true));
    assertThat(filter.shouldFilter("/logout"), is(true));
    assertThat(filter.shouldFilter("/index/page"), is(true));
    assertThat(filter.shouldFilter("/mysite.net"), is(false));
  }

  @Test
  public void notFiltered() throws Exception {
    StartsWithKeyword keyword = new StartsWithKeyword("/login");

    EndpointFilter filter = new EndpointFilter(keyword);

    assertThat(filter.shouldFilter("/my.jpg"), is(false));
    assertThat(filter.shouldFilter("/logout"), is(false));
  }

  @Test
  public void emptyKeyword() throws Exception {
    StartsWithKeyword keyword = new StartsWithKeyword("");

    EndpointFilter filter = new EndpointFilter(keyword);

    assertThat(filter.shouldFilter("/index"), is(false));
  }

  @Test
  public void emptyAdress() throws Exception {
    StartsWithKeyword keyword = new StartsWithKeyword("/index");

    EndpointFilter filter = new EndpointFilter(keyword);

    assertThat(filter.shouldFilter(""), is(false));
  }

  @Test
  public void emptyKeywordAndEmptyAdress() throws Exception {
    StartsWithKeyword keyword = new StartsWithKeyword("");

    EndpointFilter filter = new EndpointFilter(keyword);

    assertThat(filter.shouldFilter(""), is(false));
  }
}