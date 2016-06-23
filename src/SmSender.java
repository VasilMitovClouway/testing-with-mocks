import java.util.regex.Pattern;

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class SmSender implements Validator {
  @Override
  public boolean checkContentOf(Sms sms) {
    if (sms.getTitle().isEmpty()) {
      return false;
    }
    if (sms.getContext().isEmpty()) {
      return false;
    }
    if(sms.getContext().length()<10||sms.getContext().length()>100){
      return false;
    }
    if (sms.getNumberAsString().isEmpty())
      return false;
    if (!sms.getNumberAsString().startsWith("08")) {
      return false;
    }
    if (!sms.getNumberAsString().matches("[0-9]+")){
      return false;
    }
    if (sms.getNumberAsString().length() < 10 || sms.getNumberAsString().length() > 10) {
      return false;
    }

    return true;
  }

}
