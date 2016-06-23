/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class Sms {
  private String title;
  private String context;
  private String numberAsString;

  public Sms(String title, String context, String numberAsString) {
    this.title = title;
    this.context = context;
    this.numberAsString = numberAsString;
  }

  public String getTitle() {
    return title;
  }

  public String getContext() {
    return context;
  }

  public String getNumberAsString() {
    return numberAsString;
  }
}
