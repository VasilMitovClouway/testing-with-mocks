package com.clouway.endpointfilter; /**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */

import com.clouway.endpointfilter.Endpoint;
import com.clouway.endpointfilter.EndpointFilter;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;


public class EndpointFilterTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  @Mock
  private Endpoint endpoint;
  @Mock
  private Endpoint endpoint1;

  @Test
  public void matchingAndFilteringUrl() throws Exception {
    String url = "weNeedToFilterThisUrl";
    context.checking(new Expectations() {{
      oneOf(endpoint).matches(url);
      will(returnValue(true));
    }});
    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertTrue(endpointFilter.shouldFilter("weNeedToFilterThisUrl"));
  }

  @Test
  public void matchingUrlThatDoesntNeedToBeFiltered() throws Exception {
    String url = "noNeedToFilterThisUrl";
    context.checking(new Expectations() {{
      oneOf(endpoint).matches(url);
      will(returnValue(false));
    }});
    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertThat(endpointFilter.shouldFilter("noNeedToFilterThisUrl"), is(false));
  }

  @Test
  public void matchingMoreThanOneUrl() throws Exception {
    String url1 = "shouldFilter";
    context.checking(new Expectations() {{
      oneOf(endpoint).matches(url1);
      will(returnValue(false));
      oneOf(endpoint1).matches(url1);
      will(returnValue(true));

    }});
    EndpointFilter endpointFilter = new EndpointFilter(endpoint, endpoint1);
    assertThat(endpointFilter.shouldFilter(url1), is(true));
  }

  @Test(expected = IllegalArgumentException.class)
  public void matchingEmptyUrlThrowsException() throws Exception {
    String emptyUrl = "";
    context.checking(new Expectations() {{
      allowing(endpoint).matches(emptyUrl);
      will(throwException(new IllegalArgumentException("You cannot pass an empty Url")));
    }});
    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    endpointFilter.shouldFilter("");
  }
}
