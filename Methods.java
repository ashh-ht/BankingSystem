import java.util.*;

public class Methods {
    Account account;
    Scanner sc = new Scanner(System.in);
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
            "1234567899876543", new Account("1234567899876543", "Sylvia Heart", "Sulla", "1234", 0),
            "9876543211234567", new Account("9876543211234567", "John Francis", "Hernandez", "5678", 0),
            "4567891234567890", new Account("4567891234567890", "Jane Marie", "Dela Cruz", "9012",0)
    );
        
    public void addData(String cardNum, String firstName, String lastName, String cardPin, double balance) {
        Account newData = new Account(cardNum, firstName, lastName, cardPin, balance);
        dummyData.put(cardNum, newData);
        setAccount(cardNum, firstName, lastName, cardPin, balance);
        
    }

    public void setAccount(String cardNum, String firstName, String lastName, String cardPin, double balance) {
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setCardNum(cardNum);
        account.setCardPin(cardPin);
        account.setBalance(balance);
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
            System.out.print("Enter your first name: ");
            firstName = sc.nextLine();
            System.out.print("Enter your last name: ");
            lastName = sc.nextLine();
            System.out.print("Enter your card number: ");
            cardNum = sc.nextLine();
            boolean found = userChecker(cardNum);
            if (!found) {
                System.out.println("User not found. Please check your details and try again.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Enter your card pin: ");
            cardPin = sc.nextLine();
            boolean correctPin = pinChecker(cardNum, cardPin);
            if (!correctPin) {
                System.out.println("Incorrect pin. Please try again.");
                continue;
            }
            break;
        }
        setAccount(cardNum, firstName, lastName, cardPin, dummyData.get(cardNum).getBalance());
    }

    //create account
    public void createAccount() {
        System.out.println("~~~~~~~~~~Create Account~~~~~~~~~~");
        System.out.print("Enter your first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = sc.nextLine();
        String cardNum = generateCardNum();
        System.out.println("Your card number is: " + cardNum);
        String cardPin = "";
        while (true) {
            System.out.print("Create a 4-digit card pin: ");
            cardPin = sc.nextLine();
            if (cardPin.matches("\\d{4}")) {
                System.out.print("Account created successfully!");
                break;
            } else {
                System.out.print("Invalid pin. Please enter a 4-digit pin.");
            }
        }
        
        addData(cardNum, firstName, lastName, cardPin, 0);
        
    }
    
    //user checker
    public boolean userChecker(String cardNum) {
        Optional<Account> user = this.dummyData.values().stream()
                .filter(u -> u.getCardNum().equals(cardNum))
                .findFirst();

        if (!user.isPresent()) {
            return false;
        }
        return true;
    }
    
    public boolean pinChecker(String cardNum, String cardPin) {
        Optional<Account> user = this.dummyData.values().stream()
                .filter(u -> u.getCardNum().equals(cardNum) && u.getCardPin().equals(cardPin))
                .findFirst();

        if (!user.isPresent()) {
            return false;
        }
        return true;
    }

    //money transfer
    public void transfer() {
        String cardNum = account.getCardNum();
        System.out.println("~~~~~~~~~~Transfer Money~~~~~~~~~~");
        while (true) {
            System.out.print("Enter the recipient's card number: ");
            String recipientCardNum = sc.nextLine();
            double amount = 0;
            while (true) {
                System.out.print("Enter the amount to transfer: ");
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
            
            System.out.print("Message (Optional): ");
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
                    recipient.get().setBalance(recipient.get().getBalance() + amount);
                    this.dummyData.get(cardNum).setBalance(account.getBalance() - amount);
                } else {
                    System.out.println("Transfer cancelled.");
                    continue;
                }
            }
        }
        


    }
}
