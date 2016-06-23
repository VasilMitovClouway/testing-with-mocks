

/**
 * @author Vasil Mitov (v.mitov.clouway@gmail.com)
 */
public class Gateway {
  private Validator validator;
  private Sender sender;


  public Gateway(Validator validator, Sender sender) {
    this.validator = validator;
    this.sender = sender;
  }

  public boolean send(Sms sms) throws UnableToSendSmsAtTheMoment {
    if(validator.check(sms)) {
      if (!sender.send(sms)){
        throw new UnableToSendSmsAtTheMoment();
      }
    }
    return false;
  }
}
