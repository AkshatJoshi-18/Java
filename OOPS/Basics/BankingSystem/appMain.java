import java.util.Scanner;
public class appMain {

//---------
    static class User{
        String name;
        long accountNumber;
        double Balance;
        private String PASSWORD = "hi";
        
        Scanner sc = new Scanner(System.in);
        
        static boolean userAutentication(long accountNumber , String password){
            
            return true;
       }
        
        void deposite(){}
        
        void withdrawal(){}
        
        void showUserDetails(){}
        

        boolean resetPassword(String oldPassword){
            if(PASSWORD.equals(oldPassword)){
                System.out.print("Enter new password :");
                PASSWORD = sc.nextLine();
                return true;
            }
            return false;
        }
        
     }
  
//---------
     class AccountLogin{
        private static boolean userAuth ;

        static void login(long accountNumber , String password ){
            userAuth = User.userAutentication(accountNumber, password);
            if(userAuth) loggedInUserMenu(accountNumber);
        }
        
        private static void loggedInUserMenu(long accountNumber){
            User user = new User();
            int Choice = 1 ;
            Scanner sc = new Scanner(System.in);
            String oldPassword ;
            boolean responce ;
            while(Choice != 0) {
                System.out.print(
                " Enter '1' for show user details \n Enter '2' for Deposit \n Enter '3' for Withdrawal \n Enter '4' for Reset Password \n Enter '0' for Logout \n Enter : ");
                Choice = sc.nextInt();
                sc.nextLine();
                switch (Choice) {
                    case 0 :
                        Choice = 0 ;
                        System.out.println("----------------------------LOGOUT-------------------------------");
                        break;
                    case 1 :
                        //User Details
                    case 2 :
                        // Deposit
                        break;
                    case 3 :
                        // Withdrawal
                        break;
                    case 4 :
                    //Rest Password
                        System.out.print("Enter Old Password : ");
                        oldPassword = sc.nextLine();
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

// MAIN-------------
    public static void main(String[] args) {
        int Choice = 1 ;
        Scanner sc = new Scanner(System.in);
        long accountNumber ;
        String password ;
        while(Choice != 0) {
            System.out.print(" Enter '1' for Account Login \n Enter '2' for Create a Account \n Enter '0' for Exit \n Enter : ");
            Choice = sc.nextInt();
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