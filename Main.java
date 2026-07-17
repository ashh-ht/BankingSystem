import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Methods m = new Methods();
        Scanner sc = new Scanner(System.in);

        //switch for create or login
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        
        switch(choice){
            case 1:
                m.createAccount();
                m.menu();
                break;
            case 2:
                m.login();
                m.menu();
                break;
            default:
                //input error handling here
                System.out.println("Invalid choice. Please try again.");
        } 

    }
}