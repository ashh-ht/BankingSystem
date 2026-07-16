import java.util.*;

public class Account {
    private String cardNum, firstName, lastName, cardPin;
    //string ang cardnum and cardpin kasi para mas madali sa checking. and also hnd naman gagamitin for calculations kaya mas okay na string
    private double balance;
    public Account(String cardNum, String firstName, String lastName, String cardPin) {
        this.cardNum = cardNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardPin = cardPin;
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

    public void setBalance(float balance){
        this.balance = balance;
    }
}
