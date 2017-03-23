import java.util.Scanner;

// Anthony Phillips
// Project 4
// Program 3: Counting Change
// Takes a monetary sum under $1 and a number of coins
// and tells if the given sum equals the sum of the coins

public class CountingChange{

    public static void main(String[] args){
        int givenSum;
        int calculatedSum = 0;
        Scanner stdIn = new Scanner(System.in);

        // Get the given sum
        while (true){
            System.out.print("Enter the sum of the change: ");

            if (stdIn.hasNextInt()){
                givenSum = stdIn.nextInt();

                // Make sure the given sum is from 1-99
                if (givenSum > 99 || givenSum < 0)
                    return;

                break;
            }

            System.out.println("Please enter an integer.");
            stdIn.nextLine();
        }

        // Add the values of the coins to the calculated sum
        calculatedSum += promptChange("dimes", 10);
        calculatedSum += promptChange("nickels", 5);
        calculatedSum += promptChange("pennies", 1);

        // Check if the given and calculated sums match up
        if (givenSum == calculatedSum){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // Static method to return the value of a given number of specified coins
    private static int promptChange(String changeType, int changeValue){
        Scanner stdIn = new Scanner(System.in);

        while (true){
            System.out.print("Enter the amount of " + changeType + ": ");

            if (stdIn.hasNextInt()){

                return stdIn.nextInt() * changeValue;
            }

            System.out.println("Please enter an integer.");
            stdIn.nextLine();
        }
    }
}
