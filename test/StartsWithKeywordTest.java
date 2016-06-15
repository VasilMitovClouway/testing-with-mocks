import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class StartsWithKeywordTest {
  StartsWithKeyword keyword=new StartsWithKeyword("someKeyword");
  EndpointFilter endpointFilter=new EndpointFilter(keyword);

  @Test
  public void urlStartingWithKeywordShouldReturnTrue() throws Exception {
    assertTrue(endpointFilter.shouldFilter("someKeywordThatIsHere"));
  }

  @Test
  public void urlNotStartingWithKeywordShouldReturnFalse() throws Exception {
    assertFalse(endpointFilter.shouldFilter("urlNotStartingwithsomeKeyword"));
  }
}
