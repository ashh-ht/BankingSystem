import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //switch for create or login
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                //code here
                break;
            case 2:
                //code here
                break;
            default:
                //input error handling here
                System.out.println("Invalid choice. Please try again.");
        }

    }
}