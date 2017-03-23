import java.util.Scanner;

// Anthony Phillips
// Takes in a character and outputs its uppercase equivalent

public class ConvertToUpper {

  public static void main(String[] args) {
    Scanner stdIn = new Scanner(System.in);

    System.out.print("Enter a letter: ");
    char c = stdIn.next().charAt(0);

    if (!Character.isLetter(c)) {
      System.out.println("Not a letter.");
    } else {
      System.out.format("The uppercase equivalent of %c is\n", c);
      System.out.println(Character.toUpperCase(c));
    }
  }

}
