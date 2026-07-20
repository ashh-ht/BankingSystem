public class Transaction {
  private String refNum;
  private String type;
  private double amount;
  private String date;

  public Transaction(String refNum, String type, double amount, String date) {
    this.refNum = refNum;
    this.type = type;
    this.amount = amount;
    this.date = date;
  }

  public String getRefNum() {
    return refNum;
  }

  public String getType() {
    return type;
  }

  public double getAmount() {
    return amount;
  }

  public String getDateTime() {
    return date;
  }
}