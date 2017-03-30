import java.util.Scanner;

// Anthony Phillips
// Project 5
// Program 1: Binary Conversion
// Takes in binary numbers supplied by the user,
// and outputs their decimal equivalents.

public class BinaryConversion{

    public static void main(String[] args){
        Scanner stdIn = new Scanner(System.in);

        while (true){
            String binaryString;

            System.out.print("Enter a binary number: ");

            binaryString = stdIn.next();

            // Sentinel
            if (binaryString.equals("-1"))
                break;

            // Make sure string is actually binary
            if (isBinary(binaryString))
                System.out.println("Conversion to decimal: " + binaryToDecimal(binaryString));
            else
                System.out.println("Provided string not binary.");
        }

        System.out.println("All set !");
    }

    // Returns the decimal representation of a given binary string
    public static int binaryToDecimal(String binaryString){
        int decimal = 0;

        for (int  i = 0, j = binaryString.length() - 1; i < binaryString.length(); i++, j--){
            if (binaryString.charAt(j) == '1')
                decimal += Math.pow(2, i);
        }

        return decimal;
    }

    // Returns true if string only contains 1s and/or 0s
    public static boolean isBinary(String binaryString){

        for (char c : binaryString.toCharArray()){
            if (c != '0' && c != '1')
                return false;
        }

        return true;
    }
}
