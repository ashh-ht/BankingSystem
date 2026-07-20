import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankServices {
    Scanner sc = new Scanner(System.in);
    private Account account;

    private final Map<String, Account> dummyData = new HashMap<>(Map.of(
            "1234567899876543", new Account("1234567899876543", "Sylvia Heart", "Sulla", "1234", 100000),
            "9876543211234567", new Account("9876543211234567", "John Francis", "Hernandez", "5678", 0),
            "4567891234567890", new Account("4567891234567890", "John Hendrick", "Fulgencio", "9012", 0)));

    public void addData(String cardNum, String firstName, String lastName, String cardPin, double balance) {
        Account newData = new Account(cardNum, firstName, lastName, cardPin, balance);
        dummyData.put(cardNum, newData);
        this.account = newData;
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

    public static String generateRefNum() {
        Random random = new Random();
        StringBuilder refNum = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int digit = random.nextInt(10);
            refNum.append(digit);
        }
        return refNum.toString();
    }

    public String dateFormatter() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        String formattedDateTime = dateTime.format(format);
        return formattedDateTime;

    }

    public void showUser() {
        System.out.println(Account.Color.GREEN + "\n\n--------------------------------" + Account.Color.RESET);
        System.out.println(Account.Color.YELLOW + "Name: " + Account.Color.RESET + account.getFirstName() + " " + account.getLastName());
        System.out.println(Account.Color.YELLOW + "Card Number: " + Account.Color.RESET + account.getCardNum());
        System.out.printf(Account.Color.YELLOW + "Balance: %.2f" + Account.Color.RESET,
                account.getBalance());
        System.out.println(Account.Color.GREEN + "\n--------------------------------" + Account.Color.RESET);
    }

    // login
    public void login() {
        String cardNum, cardPin;

        System.out.println(Account.Color.CYAN + Account.Color.BOLD
                + "\n\n~~~~~~~~~~Welcome to the TFPH Banking System!~~~~~~~~~~" + Account.Color.RESET);
        // while true for user input validation. uulit kapag mali/wala sa dummy data
        while (true) {
            System.out.print("Enter your card number: ");
            cardNum = sc.nextLine();
            int size = cardNum.strip().length();
            if (size != 16) {
                System.out.println(Account.Color.RED + Account.Color.BOLD
                        + "Invalid Card Number. Please enter a valid one." + Account.Color.RESET);
                continue;
            }
            boolean found = userChecker(cardNum);
            if (!found) {
                System.out.println(Account.Color.BOLD + Account.Color.RED + "User not found. Please check your details and try again." + Account.Color.RESET);
                continue;
            }
            this.account = this.dummyData.get(cardNum);
            break;
        }

        while (true) {
            System.out.print("Enter your card pin: ");
            cardPin = sc.nextLine();
            boolean correctPin = pinChecker(cardNum, cardPin);
            if (!correctPin) {
                System.out.println(Account.Color.BOLD + Account.Color.RED + "Incorrect pin. Please try again." + Account.Color.RESET);
                continue;
            }
            break;
        }
    }

    // create account
    public void createAccount() {
        System.out.println(Account.Color.BOLD + Account.Color.PURPLE + "\n\n~~~~~~~~~~Create Account~~~~~~~~~~" + Account.Color.RESET);
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
                System.out.println(Account.Color.BOLD + Account.Color.RED + "Invalid pin. Please enter a 4-digit pin." +Account.Color.RESET);
            }
        }

        addData(cardNum, firstName, lastName, cardPin, 0);

    }

    // user checker
    public boolean userChecker(String cardNum) {
        Account user = this.dummyData.get(cardNum);
        return user != null && user.getCardNum().equals(cardNum);
    }

    public boolean pinChecker(String cardNum, String cardPin) {
        Account user = this.dummyData.get(cardNum);
        return user != null && user.getCardPin().equals(cardPin);
    }

    public void menu() {
        showUser();
        while (true) {
            System.out.println(Account.Color.BOLD + Account.Color.BLUE + "\n\n~~~~~~~~~~Welcome to the Menu!~~~~~~~~~~"
                    + Account.Color.RESET);
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Send Money");
            System.out.println("4. View Balance and Account Details");
            System.out.println("5. Edit Card Details");
            System.out.println("6. View Transaction History");
            System.out.println("7. Done");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 1 || choice > 7) {
                    throw new IllegalArgumentException(Account.Color.BOLD + Account.Color.RED
                            + "Invalid choice. Please enter a number between 1 and 7.\n" + Account.Color.RESET);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (InputMismatchException err) {
                System.out.println(Account.Color.RED + Account.Color.BOLD + "Invalid input. Please enter a number.\n"
                        + Account.Color.RESET);
                sc.nextLine();
            }

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    viewbalance();
                    break;
                case 5:
                    editCardDetails();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    System.out.println(Account.Color.BOLD + Account.Color.GREEN
                            + "Thank you for using the TFPH Banking System!" + Account.Color.RESET);
                    return;
            }
        }

    }

    // money transfer
    public void transfer() {
        double balance = account.getBalance();
        // sender referral
        String sender = generateRefNum();
        System.out.println(
                Account.Color.BOLD + Account.Color.PURPLE + "\n\n~~~~~~~~~~Send Money~~~~~~~~~~" + Account.Color.RESET);
        while (true) {
            System.out.print("Enter the recipient's card number: ");
            String recipientCardNum = sc.nextLine();
            double amount = 0;
            while (true) {
                System.out.print("Enter the amount to transfer: ");
                try {
                    amount = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(Account.Color.BOLD + Account.Color.RED
                            + "Invalid amount. Please enter a valid number." + Account.Color.RESET);
                }
                if (amount > balance) {
                    System.out.println(balance);
                    System.out.println(Account.Color.BOLD + Account.Color.RED
                            + "Insufficient balance. Please enter a valid amount." + Account.Color.RESET);
                } else if (amount < 50 || amount > 10000) {
                    System.out.println(Account.Color.BOLD + Account.Color.RED
                            + "Invalid amount. Please enter an amount between 50 and 10,000 pesos."
                            + Account.Color.RESET);
                } else {
                    break;
                }
            }

            System.out.print("Message (Optional): ");
            String message = sc.nextLine();

            Account recipient = this.dummyData.get(recipientCardNum);
            boolean recipientExists = recipient != null;
            if (!recipientExists) {
                System.out.println(Account.Color.BOLD + Account.Color.RED
                        + "Recipient not found. Please check the card number and try again." + Account.Color.RESET);
                continue;
            } else {
                System.out.print(
                        "Transferring to " + recipient.getFirstName() + " " + recipient.getLastName()
                                + "?[yes or no]: ");
                String dec = sc.nextLine();
                if (dec.equalsIgnoreCase("yes")) {
                    System.out.println(Account.Color.BOLD + Account.Color.YELLOW + "\nMoney sent successfully!"
                            + Account.Color.RESET);
                    System.out.printf("%nAmount: %.2f", amount);
                    System.out.println("\nMessage: " + message);
                    System.out.println("Reference Number: " + sender);
                    recipient.setBalance(recipient.getBalance() + amount);
                    account.setBalance(account.getBalance() - amount);
                    System.out.printf("New balance: %.2f", account.getBalance());

                    account.addTransaction(sender, "Transferred to " + recipient.getFirstName() + " " + recipient.getLastName(), amount, dateFormatter());
                    recipient.addTransaction(sender, "Recieved from "+ account.getFirstName() + " " + account.getLastName(), amount, dateFormatter());
                } else if (dec.equalsIgnoreCase("no") || dec.equalsIgnoreCase("n")) {
                    break;
                }else {
                    System.out.println("Transfer cancelled.");
                    continue;
                }
            }
            break;
        }
    }

    public void editCardDetails() {
        System.out.println(Account.Color.BOLD + Account.Color.PURPLE + "\n\n~~~~~~~~~~Edit Card Details~~~~~~~~~~"
                + Account.Color.RESET);

        while (true) {
            System.out.println("1. Name");
            System.out.println("2. Card Pin");
            System.out.println("0. Back");
            System.out.print("Enter details you want to edit: ");
            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 || choice > 2) {
                    throw new IllegalArgumentException(Account.Color.BOLD + Account.Color.RED
                            + "Invalid choice. Please enter a number in the choices above.\n" + Account.Color.RESET);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException err) {
                System.out.println(Account.Color.BOLD + Account.Color.RED
                        + "Invalid input. Please enter a number.\n" + Account.Color.RESET);
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter your new first name: ");
                    String newFirstName = sc.nextLine();
                    System.out.print("Enter your new last name: ");
                    String newLastName = sc.nextLine();
                    account.setFirstName(newFirstName);
                    account.setLastName(newLastName);
                    System.out.println("Name updated successfully!");
                    break;
                case 2:
                    while (true) {
                        System.out.print("Enter your new card pin: ");
                        String newCardPin = sc.nextLine();
                        if (newCardPin.matches("\\d{4}")) {
                            System.out.println("Card pin updated successfully!");
                            account.setCardPin(newCardPin);
                            break;
                        }
                        System.out.println(
                                    Account.Color.BOLD + Account.Color.RED + "Please enter four-digit number."
                                            + Account.Color.RESET);
                    }
                    break;
                case 0:
                    return;
            }
        }

    }

    // WITHDRAWAL FUNCTION
    public void withdraw() {
        double Amount = 0;
        String Witdrawer = generateRefNum();
        // i created this to create a loop to avoid error on withdrawal and Deposit
        boolean Validity = false;
        System.out.println(Account.Color.BOLD + Account.Color.PURPLE + "\n\n~~~~~~~~~~Withdrawal~~~~~~~~~~" + Account.Color.RESET);

        // Error handling this loop makes that whenever a user tries to use anything but
        // numbers will be put in a loop and catch error to prevent crash
        while (!Validity) {
            System.out.print("Enter amount: ");
            try {
                Amount = sc.nextDouble();
                Validity = true;
            } catch (InputMismatchException ex) {
                System.out.println(Account.Color.BOLD + Account.Color.RED + "Invalid number input, try again." + Account.Color.RESET);
                sc.nextLine();
            }
        }

        // If else Deposit thingy basically I just change the operation on the
        // setbalance
        if (Amount < 100) {
            System.out.println("The minimum required to withdraw is Php 100.");
        } else if (Amount > 20000) {
            System.out.println("The maximum required to withdraw is Php 20,000.");
        } else if (Amount > account.getBalance()) {
            System.out.println("Insufficient credits. Your current Balance is " + account.getBalance());
        } else {
            // Subtract the balance of the user to the amount that was inputted then updates
            // it using the setBalance
            account.setBalance(account.getBalance() - (double) Amount);
            account.addTransaction(Witdrawer, "Withdraw", Amount, dateFormatter());
            System.out.printf(Account.Color.YELLOW + "~~~ Withdraw Successful ~~~" + Account.Color.RESET + "%nAmount Deposited: %.2f%nNew Balance: %.2f%n", Amount,
                    account.getBalance());
            System.out.println("Reference Number: " + Witdrawer);
        }
    }

    // DEPOSIT FUNCTION
    public void deposit() {
        double Amount = 0;
        boolean Validity = false;
        // refferal
        String Depositing = generateRefNum();
        System.out.println(
                Account.Color.BOLD + Account.Color.PURPLE + "\n\n~~~~~~~~~~Deposit~~~~~~~~~~" + Account.Color.RESET);

        // Error handling this loop makes that whenever a user tries to use anything but
        // numbers will be put in a loop and catch error to prevent crash
        while (!Validity) {
            System.out.print("Enter amount: ");
            try {
                Amount = sc.nextDouble();
                Validity = true;
            } catch (InputMismatchException ex) {
                System.out.println(Account.Color.BOLD + Account.Color.RED + "Invalid number input. Try again."
                        + Account.Color.RESET);
                sc.nextLine();
            }
        }

        // If else Deposit thingy
        if (Amount < 100) {
            System.out.println("The minimum required to deposit is Php 100.");
        } else if (Amount > 20000) {
            System.out.println("The maximum required deposit is Php 20,000.");
        } else {
            // add the balance of the user to the amount that was inputted then updates it
            // using the setBalance
            account.setBalance(account.getBalance() + (float) Amount);
            // formatted into two decimal place

            // add referral to the transaction
            account.addTransaction(Depositing, "Deposit", Amount,dateFormatter());

            System.out.printf(
                    Account.Color.YELLOW + "~~~ Deposit Successful ~~~" + Account.Color.RESET
                            + "%nAmount Deposited: %.2f%nNew Balance: %.2f%n",
                    Amount,
                    account.getBalance());
            System.out.println("Reference Number: " + Depositing);
        }

    }

    //view balance func
    public void viewbalance() {
        System.out.println(Account.Color.BOLD + Account.Color.PURPLE
                + "\n\n~~~~~~~~~~Balance and Account Details~~~~~~~~~~" + Account.Color.RESET);
        System.out.printf(
                Account.Color.BOLD + Account.Color.YELLOW + "%nTotal Balance: " + Account.Color.RESET + "Php. %.2f",
                account.getBalance());
        System.out.println(Account.Color.BOLD + Account.Color.YELLOW + "\nAccount name:  " + Account.Color.RESET
                + account.getFirstName() + " " + account.getLastName());
        System.out.println(Account.Color.BOLD + Account.Color.YELLOW + "Card number:   " + Account.Color.RESET
                + account.getCardNum());

    }

    //transaction history
    public void viewTransactionHistory() {
        System.out.println(Account.Color.BOLD + Account.Color.PURPLE + "\n~~~~~~~~~~Transaction History~~~~~~~~~~"
                + Account.Color.RESET);

        Map<String, Transaction> history = account.getTransactionHistory();

        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        }

        else {
            for (Map.Entry<String, Transaction> entry : history.entrySet()) {
                Transaction t = entry.getValue();
                System.out.printf("\nRef: " + entry.getKey() + " | "
                        + t.getType() + " | Php %.2f" + " | " + t.getDateTime() , t.getAmount());
            }
        }

        System.out.println("\n--------------------------------------------------------------");
    }
}
