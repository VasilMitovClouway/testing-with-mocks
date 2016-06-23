

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class Gateway {
  private Validator validator;


  public Gateway(Validator validator) {
    this.validator = validator;
  }

  public boolean send(Sms sms) {
    if (validator.checkContentOf(sms)) {
      return true;
    }
    return false;
  }
}
