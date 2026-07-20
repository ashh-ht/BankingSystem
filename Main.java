import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // final Account acc;
        BankServices bs = new BankServices();
        Scanner sc = new Scanner(System.in);

        while (true) {
            // switch for create or login
            System.out.println(Account.Color.VIOLET + Account.Color.BOLD
                    + "Select from the following to start the system." + Account.Color.RESET);
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. EXIT");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 1 || choice > 3) {
                    throw new IllegalArgumentException(Account.Color.BOLD + Account.Color.RED
                            + "Invalid choice. Please enter a number in the choices above.\n\n" + Account.Color.RESET);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException err) {
                System.out.println(Account.Color.BOLD + Account.Color.RED
                        + "Invalid input. Please enter a number.\n\n" + Account.Color.RESET);
                sc.nextLine();
            }

            switch (choice) {
                case 1:
                    bs.createAccount();
                    bs.menu();
                    break;
                case 2:
                    bs.login();
                    bs.menu();
                    break;
                case 3:
                    System.out.println(Account.Color.RED + "NOTE: " + Account.Color.RESET
                            + "If you exit the program, all data will be lost.");
                    System.out.print("Are you sure you want to exit? (Y/N): ");
                    String exitChoice = sc.nextLine();
                    if (exitChoice.equalsIgnoreCase("Y") || exitChoice.equalsIgnoreCase("yes")) {
                        System.out.println(Account.Color.VIOLET + Account.Color.BOLD + "Exiting the program. Goodbye!"
                                + Account.Color.RESET);
                        sc.close();
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
        }

    }
}