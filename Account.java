import java.util.HashMap;

public class Account {
    private String cardNum, firstName, lastName, cardPin;
    private double balance;
    HashMap<String, Transaction> transactionHistory = new HashMap<String, Transaction>();
    //string ang cardnum and cardpin kasi para mas madali sa checking. and also hnd naman gagamitin for calculations kaya mas okay na string
    
    public Account(String cardNum, String firstName, String lastName, String cardPin, double balance) {
        this.cardNum = cardNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardPin = cardPin;
        this.balance = balance;
    }
    
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }               
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public HashMap<String, Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(String refNum, String type, double amount) {
        Transaction t = new Transaction(refNum, type, amount);
        transactionHistory.put(refNum, t);
    }

    


    class Color {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String BOLD = "\u001B[1m";
        public static final String VIOLET = "\u001B[38;5;129m";
    }

}
