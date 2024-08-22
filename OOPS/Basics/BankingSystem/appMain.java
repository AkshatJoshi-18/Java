import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class appMain {

//---------------------------------
    static class CreateAccount{
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        String name;
        long accountNumber;
        double balance = 0.0;
        private String PASSWORD;

//--------GENERATE USER ACCOUNT NUMBER
        private long generateAccountNumber()
        {
            accountNumber = rand.nextLong(1000000000000000L,9999999999999999L);
            Path path = Paths.get("UserData/"+accountNumber+".txt");
            if (Files.exists(path)) {
                generateAccountNumber();
            } 
            return accountNumber;
        }

//--------CREATE USER ACCOUNT
        void create(){
            accountNumber = generateAccountNumber();
            System.out.println("\n\n");
            System.out.println("your Account Number is : "+ accountNumber);
            System.out.print("Enter your Full Name : ");
            name = sc.nextLine();
            System.out.print("Set Your Account Password : ");
            PASSWORD = sc.nextLine();
            
            String data = "Account Number: " + accountNumber + "\n" +
            "Full Name: " + name + "\n" +
            "Password: " + PASSWORD + "\n"+
            "Balance: " + balance + "\n";

            try{  //creating the file 
            Path filePath = Paths.get("UserData/"+accountNumber+".txt");
            Files.createFile(filePath);
            Files.write(filePath, data.getBytes());
            System.out.println("------------------Account Created Successful-----------------");   
            }catch(IOException e){
                System.err.println("An error occurred : " + e.getMessage());
            }
        }
    }

//-------------------------------------------
    static class User{

        private String PASSWORD ;
        String extractedAccountNumber = "";
        String extractedFullName = "";
        String extractedBalance = "";
        
        Scanner sc = new Scanner(System.in);

//------------ SET THE VALUES FOR THE VARIABLE
        private  void setValueForVariables(long accountNumber){
            try{
                
                Path filePath = Paths.get("UserData/"+accountNumber+".txt");
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    if (line.startsWith("Account Number:")) {
                        extractedAccountNumber = line.split(": ")[1];
                    }
                    else if (line.startsWith("Full Name:")) {
                        extractedFullName = line.split(": ")[1];
                    }
                    else if (line.startsWith("Balance:")) {
                        extractedBalance = line.split(": ")[1];
                    }
                    else if (line.startsWith("Password:")) {
                        PASSWORD = line.split(": ")[1];
                    }
                    
                }
                
            }
            catch(Exception e){
                System.err.println("An error occurred : " + e.getMessage());
            }

        }
//-----------CHANGE THE VALUE IN USER DATA
        void ChangeUserDate(String password , String userFullName, double userBalance){
            Path filePath = Paths.get("UserData/"+extractedAccountNumber+".txt");

            String data = "Account Number: " + extractedAccountNumber + "\n" +
            "Full Name: " + userFullName + "\n" +
            "Password: " + password + "\n"+
            "Balance: " + userBalance+ "\n";
            try{
            Files.write(filePath, data.getBytes());
            }
            catch(IOException e){
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

//---------- USER Autentication
        private static boolean userAutentication(long accountNumber , String password){
            String extractedPassword = "";
            try {
                Path filePath = Paths.get("UserData/"+accountNumber+".txt");

                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    if (line.startsWith("Password:")) {
                        extractedPassword = line.split(": ")[1];
                    }
                }

                if(Files.exists(filePath) && password.equals(extractedPassword)){
                    System.out.println("-----------------------------------Logged In Successful-----------------------");
                    return true ;
                }
            } 
            catch (Exception e) {}
            System.out.println("------------------------------Invalid Details---------------------------");
            return false;
       }

//--------- DEPOSIT MONEY
        void deposit(){
            System.out.println("\n");
            System.out.print("Enter the Deposit Amount: ");
            double depositAmount = sc.nextDouble();
            
            if(depositAmount > 0){
                double balance = Double.parseDouble(extractedBalance);
                balance = depositAmount + balance ;
                ChangeUserDate(PASSWORD, extractedFullName, balance);
                setValueForVariables( Long.parseLong( extractedAccountNumber));
                showUserDetails();
                System.out.println("----------------------------Deposit Successful--------------------------------");
            }
            else{
                System.out.println("----------------------------Enter Valid Amount--------------------------------");
            }
        }

        
        void withdrawal(){
            System.out.println("\n");
            System.out.print("Enter the withdrawal Amount: ");
            double withdrawalAmount = sc.nextDouble();
            
            if(withdrawalAmount > 0){
                double balance = Double.parseDouble(extractedBalance);
                if(balance >= withdrawalAmount ){
                    balance =  balance - withdrawalAmount ;
                    ChangeUserDate(PASSWORD, extractedFullName, balance);
                    setValueForVariables( Long.parseLong( extractedAccountNumber));
                    showUserDetails();
                    System.out.println("-----------------------Withdrawal Successful------------------------------ ");
                }
                else{
                    System.out.println("------------------------------Insufficient Balance--------------------------");
                }
                
            }
            else{
                System.out.println("----------------------------Enter Valid Amount--------------------------------");
            }
        }
        
 //-----SHOW USER DETAILS
        void showUserDetails(){
            System.out.println("\n\n");
            System.out.println("Account Number: "+ extractedAccountNumber+"\n"+
            "Full Name: "+ extractedFullName +"\n"+
            "Balance: "+ extractedBalance);
            System.out.println("\n\n");
            
        }
        
//-------RESET USER PASSWORD
        boolean resetPassword(String oldPassword){
            if(PASSWORD.equals(oldPassword)){
                System.out.print("Enter new password :");
                PASSWORD = sc.nextLine();
                sc.nextLine();
                ChangeUserDate(PASSWORD, extractedFullName, Double.parseDouble(extractedBalance) );
                setValueForVariables( Long.parseLong( extractedAccountNumber));
                return true;
            }
            return false;
        }
        
     }
  
//----------------------------------------------
     class AccountLogin{
        private static boolean userAuth ;

//------------ LOGIN FUNCTION 
        static void login(long accountNumber , String password ){
            userAuth = User.userAutentication(accountNumber, password);
            if(userAuth) loggedInUserMenu(accountNumber);
        }
        
//------------- LOGGED IN USER MENU
        private static void loggedInUserMenu(long accountNumber){
            User user = new User();
            int Choice = 1 ;
            Scanner sc = new Scanner(System.in);
            String oldPassword ;
            boolean responce ;

            while(Choice != 0) {
                System.out.print(" Enter '1' for show user details \n Enter '2' for Deposit \n Enter '3' for Withdrawal \n Enter '4' for Reset Password \n Enter '0' for Logout \n Enter : ");
                Choice = sc.nextInt();
                sc.nextLine();

                switch (Choice) {
                    case 0 :
                        Choice = 0 ;
                        System.out.println("----------------------------LOGOUT-------------------------------");
                        break;
                    case 1 :
                        //User Details
                        user.setValueForVariables(accountNumber);
                        user.showUserDetails();
                        break;
                    case 2 :
                        // Deposit
                        user.setValueForVariables(accountNumber);
                        user.deposit();
                        break;
                    case 3 :
                        // Withdrawal
                        user.setValueForVariables(accountNumber);
                        user.withdrawal();
                        break;
                    case 4 :
                    //Rest Password
                        System.out.print("Enter Old Password : ");
                        oldPassword = sc.nextLine();
                        user.setValueForVariables(accountNumber);
                        responce = user.resetPassword(oldPassword);
                        if(responce) {System.out.println("--------------------------Password Change successfully !!----------------------------");}
                        else {System.out.println("--------------------------Enter Correct Password----------------------------");}
                        break;
                    default :
                        System.out.println("----------------Enter Correct Choice------------------------");
                }
            }
        }
        
    }

//--------- MAIN FUNCTION
    public static void main(String[] args) {
        int Choice = 1 ;
        Scanner sc = new Scanner(System.in);
        long accountNumber ;
        String password ;

        while(Choice != 0) {
            System.out.print(" Enter '1' for Account Login \n Enter '2' for Create a Account \n Enter '0' for Exit \n Enter : ");
            Choice = sc.nextInt();
            sc.nextLine();
            
            switch (Choice) {
                case 1 :
                    System.out.print("Enter the Account Number : ");
                    accountNumber = sc.nextLong();
                    sc.nextLine();//
                    System.out.print("Enter the Account Password : ");
                    password = sc.nextLine();
                    AccountLogin.login(accountNumber,password);
                    break;
                case 2 :
                    // Create Account
                    CreateAccount account= new CreateAccount();
                    account.create();
                    break;
                case 0 :
                    //exit
                    Choice = 0 ;
                    break;
                default :
                    System.out.println("----------------Enter Correct Choice------------------------");
            }
        }
           
    }
}