import java.util.Scanner;

public class Methods {
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



    //login
    public void login(){
        System.out.println("~~~~~~~~~~Welcome to the Banking System!~~~~~~~~~~");
        System.out.println("Enter your first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter your card number: ");
        int cardNum = sc.nextInt();
        System.out.println("Enter your card pin: ");
        int cardPin = sc.nextInt();
        //card pin checker here
    }

    //create account
}
