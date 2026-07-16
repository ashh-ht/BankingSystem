import java.util.*;

public class Account {
    private String cardNum, firstName, lastName, cardPin;
    private double balance;
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

}
