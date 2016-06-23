/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class SmsEnder implements Validator {
  @Override
  public boolean check(Sms sms) {
    if (mistakesInTitleOf(sms)|| mistakesInContentOf(sms)|| mistakesInNumberOf(sms)){
      return false;
    }
    return true;
  }

  private boolean mistakesInTitleOf(Sms sms) {
    if (sms.getTitle().isEmpty()) {
      return true;
    }
    return false;
  }

  private boolean mistakesInNumberOf(Sms sms) {
    if (sms.getNumberAsString().isEmpty())
      return true;
    if (!sms.getNumberAsString().startsWith("08")) {
      return true;
    }
    if (!sms.getNumberAsString().matches("[0-9]+")){
      return true;
    }
    if (sms.getNumberAsString().length() < 10 || sms.getNumberAsString().length() > 10) {
      return true;
    }
    return false;
  }

  private boolean mistakesInContentOf(Sms sms) {
    if (sms.getContext().isEmpty()) {
      return true;
    }
    if(sms.getContext().length()<10||sms.getContext().length()>100){
      return true;
    }
    return false;
  }

}
