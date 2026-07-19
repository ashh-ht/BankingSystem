import java.util.*;

public class Methods {
    Scanner sc = new Scanner(System.in);
    private Account account;

    // 3. transfer
    // -minimum of 50, maximum of 10,000 pesos
    // 4. view balance and account details
    // -acc name
    // -card number
    // -balance
    // 5. edit card details
    // 6. history

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

    public void showUser() {
        System.out.println("Name: " + account.getFirstName() + " " + account.getLastName());
        System.out.println("Card Number: " + account.getCardNum());
        System.out.println("Balance: " + account.getBalance());
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
                System.out.println("User not found. Please check your details and try again.");
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
                System.out.println("Incorrect pin. Please try again.");
                continue;
            }
            break;
        }
    }

    // create account
    public void createAccount() {
        System.out.println("\n\n~~~~~~~~~~Create Account~~~~~~~~~~");
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
                System.out.println("Invalid pin. Please enter a 4-digit pin.");
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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    // view balance and account details
                    break;
                case 5:
                    editCardDetails();
                    break;
                case 6:
                    // view transaction history
                    break;
                case 7:
                    System.out.println(Account.Color.BOLD + Account.Color.GREEN
                            + "Thank you for using the TFPH Banking System!" + Account.Color.RESET);
                    return;
                default:
                    break;
            }
        }

    }

    // money transfer
    public void transfer() {
        double balance = account.getBalance();
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
                System.out.println(
                        "Transferring to " + recipient.getFirstName() + " " + recipient.getLastName()
                                + "?[yes or no]");
                String dec = sc.nextLine();
                if (dec.equalsIgnoreCase("yes")) {
                    System.out.println(Account.Color.BOLD + Account.Color.YELLOW + "Money sent successfully!"
                            + Account.Color.RESET);
                    System.out.println("Amount: " + amount);
                    System.out.println("Message: " + message);
                    System.out.println("Reference Number: " + generateRefNum());
                    recipient.setBalance(recipient.getBalance() + amount);
                    account.setBalance(account.getBalance() - amount);

                    // showBalance func here...
                    // System.out.println("Your new balance is: " + account.getBalance());
                    // System.out.println("Recipient's new balance is: " + recipient.getBalance());
                } else {
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
                    printAllAccounts();
                    System.out.println("Name updated successfully!");
                    break;
                case 2:
                    System.out.print("Enter your new card pin: ");
                    String newCardPin = sc.nextLine();
                    account.setCardPin(newCardPin);
                    printAllAccounts();
                    System.out.println("Card pin updated successfully!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    // pang try lang
    public void printAllAccounts() {
        System.out.println("=== ALL REGISTERED ACCOUNTS ===");

        // key = cardNumber, value = account
        dummyData.forEach((cardNumber, account) -> {
            System.out.println("\n\nCard Number: " + cardNumber);
            System.out.println("Owner:       " + account.getFirstName() + " " + account.getLastName());
            System.out.println("Balance:     [Php. " + account.getBalance());
            System.out.println("---------------------------------\n\n");
        });
    }
}
