import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

// Anthony Phillips
// Project 5
// Program 2: Valid Phone Numbers
// Takes in a string and tells whether it is valid or not
// by comparing to the pattern "ddd-ddd-dddd"

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
