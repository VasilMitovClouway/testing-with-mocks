package com.clouway.endpointfilter;

import com.clouway.endpointfilter.Endpoint;
import com.clouway.endpointfilter.EndpointFilter;
import com.clouway.endpointfilter.StartsWithKeyword;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class StartsWithKeywordTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private Endpoint endpoint=new StartsWithKeyword("someKeyword");


  EndpointFilter endpointFilter=new EndpointFilter(endpoint);


  @Test
  public void urlStartingWithKeywordShouldReturnTrue() throws Exception {
    assertTrue(endpointFilter.shouldFilter("someKeywordAndSomeText"));
  }

  @Test
  public void urlNotStartingWithKeywordShouldReturnFalse() throws Exception {
    assertFalse(endpointFilter.shouldFilter("urlNotStartingwithsomeKeyword"));
  }
}
