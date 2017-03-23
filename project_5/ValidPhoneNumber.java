import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

// Anthony Phillips
// Project 5
// Program 2: Valid Phone Number
// Takes in a string and tells whether it is valid or not.
// Valid phone numbers match a the pattern given in the assignment.

public class ValidPhoneNumber{

    public static void main(String[] args){
        System.out.print("Please enter a phone number: ");

        Scanner stdIn = new Scanner(System.in);
        String phone = stdIn.next();

        Pattern regex = Pattern.compile("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d");
        Matcher matcher = regex.matcher(phone);

        if (matcher.matches())
            System.out.println("Phone number is valid.");
        else
            System.out.println("Phone number is invalid.");
    }
}
