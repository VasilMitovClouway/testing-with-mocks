/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class StartsWithKeyword implements Endpoint {
  private String keyword;

  public StartsWithKeyword(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public boolean matches(String url) {
    if (url.startsWith(keyword)) {
      return true;
    } else return false;
  }
}

