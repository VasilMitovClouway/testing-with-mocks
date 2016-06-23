

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class Gateway {
  private Validator validator;


  public Gateway(Validator validator) {
    this.validator = validator;
  }

  public boolean send(Sms sms) {
    return validator.check(sms);
  }
}
