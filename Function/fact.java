import java.util.Scanner;

public class fact{
    
    static int Factorial(int num)
    {
        int fact = 1 ;
        
        for(int i = 0 ; i < num ; i++){
            fact = fact * (num-i);
        }
        return fact;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the no. :");
        int num = sc.nextInt();
        
        int fact= Factorial(num);
        
        System.out.println("the factorial of num is :"+ fact);
    }
}
