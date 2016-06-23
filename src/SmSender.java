import java.util.regex.Pattern;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class SmSender implements Validator {
  @Override
  public boolean checkContentOf(Sms sms) {
    if (titleChecker(sms)||contextChecker(sms)||numberChecker(sms)){
      return false;
    }
    return true;
  }

  private boolean titleChecker(Sms sms) {
    if (sms.getTitle().isEmpty()) {
      return true;
    }
    return false;
  }

  private boolean numberChecker(Sms sms) {
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

  private boolean contextChecker(Sms sms) {
    if (sms.getContext().isEmpty()) {
      return true;
    }
    if(sms.getContext().length()<10||sms.getContext().length()>100){
      return true;
    }
    return false;
  }

}
