
import java.util.Scanner; // Importing Scanner Package

/**  
 * This is a simple Banking System program
 * This allows the user to input personal details
 * author allen-T
 */

public class BankingSystem
{
    public static void main(String[] args) 
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        //Input user personal details
        System.out.println("ENTER USERNAME:");   
        String userName = myObj.nextLine();  // Reads Username
        
        System.out.println("ENTER BANKING ID:");  // Reads UserID
        String userId = myObj.nextLine();
        
        Banking obj1 = new Banking(userName,userId); // Create a Banking Object
        obj1.showMenu();
    }
}
/** 
 * This class contains all the methods for the banking transactions
 * This also contains the major variables
 */
class Banking
{
    int balance;
    int prev_transactions;
    String customer_Name;
    String customer_ID;

    Banking(String cname, String cid)
    {
        //Turning the parameters into variables
        customer_Name = cname;
        customer_ID = cid;
    }
    /** 
     * This method is for the deposit transaction
     * The parameter will be the amount inputed by the user
     */
    void deposit(int amount)
    {
        if(amount != 0)
        {
            balance = balance + amount;
            prev_transactions = amount; //Saves the value of the parameter into the prev_transaction variable for future use
        }
    }
    /** 
     * This method is for the withdrawal transactions
     * The parameter me will be the amount inputed by the user
     */
    void withdraw(int amount)
    {
        if(amount != 0)
        {
            /** 
             * This conditional will check if the user withdraws more then his/her balance
             */
            if(amount > balance)
            {
                System.out.println("Your balance is insufficient to make this transaction."); 
            }
            else
            {
                balance = balance - amount;
                prev_transactions = -amount;
            }
        }
    }
    /** 
     * This method is for checking the previous transaction of the user
     */
    void get_prev_transactions() 
    {
        if(prev_transactions > 0)
        {
            System.out.println("Deposited: " + prev_transactions); //Shows how much was deposited
        }
        else if(prev_transactions < 0)//Shows how much was withdrawn 
        {
            System.out.println("Withdrawn: " + Math.abs(prev_transactions));
        }
        else
        {
            System.out.println("No Transactions occured");
        }
    }

    /**
     * This method is for the compounding intereset calculato.
     *  To show the clients how much they;ll earn in a particular timeline
     * @RATE is a fix value
     */
    void compounding_calc()
    {
        //Creating object for scanner class
        Scanner input = new Scanner(System.in);
        
    
        final double RATE = .05; //Default 

        System.out.println(" -------------------------------------------------------------------------------");
        System.out.println("|                         COMPOUNDING INTEREST CALCULATOR                       |");
        System.out.println(" -------------------------------------------------------------------------------");
        
        //Userinput for the principal amount and the number of years
        System.out.println("Enter the principal amount: ");
        double principal = input.nextDouble();

        System.out.println("How many times will you deposit in a year?");
        double frequency = input.nextDouble();

        System.out.println("Enter timeframe (Yearly): ");
        int time = input.nextInt();

        double amount = principal * Math.pow(1 + (RATE / frequency), frequency * time); //Algorithm for compounding interest
        double f_interest = amount - principal;//Solves for the interest

        System.out.println("\n\n\n************************************************************************");
        System.out.println("|Principal: " + principal);        
        System.out.println("|Interest Rate is at 5%");
        System.out.println("|Number of deposits(anually): " + frequency);
        System.out.println("|Compound Interest after " + time + " years: "+f_interest );  //Displays the interest
        System.out.println("|Amount after " + time + " years: "+amount);                  //Displays the grand total
        System.out.println("************************************************************************\n\n\n");
       
    }

    /**
     * This method is the interface or Main Menu for the client
     * This is where the client will choose his/her preferred transactions
     */

    void showMenu()
    {
        char option = '\0'; 
        Scanner scanner = new Scanner(System.in);

        System.out.println(" -------------------------------------------------------------------------------");
        System.out.println("|                         IMPERIAL BANK OF ASIA-PACIFIC                         |");
        System.out.println(" -------------------------------------------------------------------------------");
        System.out.println("Welcome " + customer_Name);
        System.out.println("Your ID is " + customer_ID);
        System.out.println("\n\n");

        
        do
        {
            System.out.println(" --------------------------------------------------------");
            System.out.println(" Your Current Balance is: " + balance); //Displays the current balance
            System.out.println(" --------------------------------------------------------\n\n");
            
            System.out.println("Press 1. DEPOSIT");
            System.out.println("Press 2. WITHDRAW");
            System.out.println("Press 3. PREVIOUS TRANSACTIONS");
            System.out.println("Press 4. COMPOUNDING INTEREST CALCULATOR");
            System.out.println("Press 5. Exit");
        
            option = scanner.next().charAt(0);
            System.out.println("\n");

            /** 
             * This loop will implement the clients preferred transaction
             */
            switch (option) 
            {
                
                case '1':
                    System.out.println("Enter an amount to deposit: ");
                    System.out.println("----------------------------");
                    int amount = scanner.nextInt(); //Userinput
                    deposit(amount); //Calls the deposit method
                    System.out.println("\n");
                    break;
                
                case '2':
                    System.out.println("Enter an amount to withdraw: ");
                    System.out.println("-----------------------------");
                    int amount2 = scanner.nextInt(); //Userinput
                    withdraw(amount2);//Calls the withdraw method
                    System.out.println("\n");
                    break;
                
                case '3':
                    System.out.println("---------------------------------------------------");
                    get_prev_transactions(); //Calls the previous transaction method
                    System.out.println("---------------------------------------------------");
                    System.out.println("\n");
                    break;
                
                case '4':
                    compounding_calc(); //Calls the compounding calculator
                    
                    break;
                
                case '5':
                System.out.println("Thank you for choosing Imperial Bank of Asia-Pacific");
                System.out.println("TRANSACTION TERMINATED...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("PLEASE CHOOSE ON THE OPTIONS");
                    break;
            }
        }
        while(option != '5');
        if(option == '5'); //Exit
            
            System.exit(0);
    }

}