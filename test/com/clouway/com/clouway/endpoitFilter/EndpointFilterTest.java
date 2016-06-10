package com.clouway.com.clouway.endpoitFilter;

import com.clouway.endpointFilter.Endpoint;
import com.clouway.endpointFilter.EndpointFilter;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class EndpointFilterTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private Endpoint endpoint;

  @Test
  public void match() throws Exception {
    final String keyword = "index";
    context.checking(new Expectations() {{
      oneOf(endpoint).matches(keyword);
      will(returnValue(true));
    }});
    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertThat(endpointFilter.shouldFilter("index"),is(equalTo(true)));
  }

  @Test
  public void nomatch() throws Exception {
    final String keyword = "index";
    context.checking(new Expectations() {{
      oneOf(endpoint).matches(keyword);
      will(returnValue(false));
    }});
    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertThat(endpointFilter.shouldFilter("index"),is(equalTo(false)));
  }
}
