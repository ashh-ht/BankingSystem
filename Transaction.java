public class Transaction {
  String refNum;
  String type;
  double amount;

  public Transaction(String refNum, String type, double amount) {
    this.refNum = refNum;
    this.type = type;
    this.amount = amount;
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
}