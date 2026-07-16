import java.util.*;

public class Methods extends Account {
    Scanner sc = new Scanner(System.in);

        public Methods(String firstname,String lastname,int cardNum,int cardPin,float balance ){
            setBalance(balance);
            setCardNum(cardNum);
            setCardPin(cardPin);
            setFirstName(firstname);
            setLastName(lastname);

        }
    public void Withdraw(double Amount){
        System.out.println("Enter the amount to be withdrawed: ");
        Amount = sc.nextDouble();
        
        try{
        if(Amount < 100){
            System.out.println("The minimum withdrawal is Php100");
        }
        // if the amount is greater than 20k then this will be printed
        else if(Amount > 20000){ 
            System.out.println("The Maximum withdrawal is Php20000");
        }
        //
        else if(Amount > getBlance()){
            System.out.println("Insufficient credits at the card, current balance: " + getBlance());
        }else{
            /* getbalance which is the current subtract the amount the user inputed and turns it into float which the setbalance updates the
            new balance */
            setBalance(getBlance()-(float)Amount);
            System.out.println("Withdrawal Succesful:" + Amount);
        }
            }
            // prevents the user from entering strings
        catch(InputMismatchException e){
            System.out.println("Error!! Please Enter valid number");
            sc.nextLine();
            }
            
        }
   
    //FEATURES
    //1. withdraw
        //-minimum of 100, maximum of 20,000 pesos

    
    //2. deposit
        //-minimum of 100, maximum of 20,000 pesos
    //3. transfer
        //-minimum of 50, maximum of 10,000 pesos
    //4. view balance and account details
        //-acc name
        //-card number
        //-balance
    //5. edit card details
    //6. history

    private final Map<String, Account> dummyData = Map.of(
            "1234567899876543", new Account("1234567899876543", "Sylvia Heart", "Sulla", "1234"),
            "9876543211234567", new Account("9876543211234567", "John Francis", "Hernandez", "5678"),
            "4567891234567890", new Account("4567891234567890", "Jane Marie", "Dela Cruz", "9012")
    );
        
    public void addData(String cardNum, String firstName, String lastName, String cardPin) {
        Account newData = new Account(cardNum, firstName, lastName, cardPin);
        dummyData.put(cardNum, newData); 
    }

    public static String generateCardNum() {
        Random random = new Random();
        StringBuilder cardNum = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNum.append(digit);
        }       
        return cardNum.toString();
    }

    //login
    public void login() {
        String firstName, lastName, cardNum, cardPin;

        System.out.println("~~~~~~~~~~Welcome to the Banking System!~~~~~~~~~~");
        //while true for user input validation. uulit kapag mali/wala sa dummy data
        while (true) {
            System.out.println("Enter your first name: ");
            firstName = sc.nextLine();
            System.out.println("Enter your last name: ");
            lastName = sc.nextLine();
            System.out.println("Enter your card number: ");
            cardNum = sc.nextLine();
            boolean found = userChecker(firstName, lastName, cardNum);
            if (!found) {
                System.out.println("User not found. Please check your details and try again.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Enter your card pin: ");
            cardPin = sc.nextLine();
            boolean correctPin = pinChecker(cardNum, cardPin);
            if (!correctPin) {
                System.out.println("Incorrect pin. Please try again.");
                continue;
            }
            break;
        }
    }

    //create account
    public void createAccount() {
        System.out.println("~~~~~~~~~~Create Account~~~~~~~~~~");
        System.out.println("Enter your first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();
        String cardNum = generateCardNum();
        System.out.println("Your card number is: " + cardNum);
        String cardPin = "";
        while (true) {
            System.out.println("Create a 4-digit card pin: ");
            cardPin = sc.nextLine();
            if (cardPin.matches("\\d{4}")) {
                System.out.println("Account created successfully!");
                break;
            } else {
                System.out.println("Invalid pin. Please enter a 4-digit pin.");
            }
        }
        
        addData(cardNum, firstName, lastName, cardPin);
        
    }
    
    //user checker
    public boolean userChecker(String firstName, String lastName, String cardNum) {
        Optional<Account> user = this.dummyData.values().stream()
                .filter(u -> u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)
                        && u.getCardNum().equals(cardNum))
                .findFirst();

        if (!user.isPresent()) {
            System.out.println("User not found. Please check your details and try again.");
            return false;
        }
        return true;
    }
    
    public boolean pinChecker(String cardNum, String cardPin) {
        Optional<Account> user = this.dummyData.values().stream()
                .filter(u -> u.getCardNum().equals(cardNum) && u.getCardPin().equals(cardPin))
                .findFirst();

        if (!user.isPresent()) {
            System.out.println("Incorrect pin. Please try again.");
            return false;
        }
        return true;
    }

    //money transfer
    public void transfer() {
        System.out.println("~~~~~~~~~~Transfer Money~~~~~~~~~~");
        while (true) {
            System.out.println("Enter the recipient's card number: ");
            String recipientCardNum = sc.nextLine();
            double amount = 0;
            while (true) {
                System.out.println("Enter the amount to transfer: ");
                try {
                    amount = Double.parseDouble(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Please enter a valid number.");
                }
                if(amount < 50 || amount > 10000) {
                    System.out.println("Invalid amount. Please enter an amount between 50 and 10,000 pesos.");
                } else {
                    break;
                }
            }
            
            System.out.println("Message (Optional): ");
            String message = sc.nextLine();

            Optional<Account> recipient = this.dummyData.values().stream()
                    .filter(u -> u.getCardNum().equals(recipientCardNum))
                    .findFirst();
            
            if (!recipient.isPresent()) {
                System.out.println("Recipient not found. Please check the card number and try again.");
                continue;
            } else {
                System.out.println(
                        "Transferring to " + recipient.get().getFirstName() + " " + recipient.get().getLastName() + "?[yes or no]");
                String dec = sc.nextLine();
                if(dec.equalsIgnoreCase("yes")) {
                    System.out.println("Transfer successful!");
                    System.out.println("Amount: " + amount);
                    System.out.println("Message: " + message);
                    
                } else {
                    System.out.println("Transfer cancelled.");
                    continue;
                }
            }
        }
        


    }
}
