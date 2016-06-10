package com.clouway.sms;

/**
 * @Author Martin Milev
 * @Date 07.06.16.
 */
public class Sms {
  private final String content;
  private final String header;
  private final int[] sender;
  private final int[] receiver;

  public Sms(String content, String header, int[] sender, int[] receiver) {
    this.content = content;
    this.header = header;
    this.sender = sender;
    this.receiver = receiver;
  }

  public String content() {
    return content;
  }

  public String header() {
    return header;
  }

  public int[] sender() {
    return sender;
  }

  public int[] receiver() {
    return receiver;
  }
}
