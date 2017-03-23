import java.util.Scanner;

// Anthony Phillips
// Sums each digit in a provided number, using modulo division

public class ModuloDivision {

  public static void main (String[] args) {
    Scanner stdIn = new Scanner(System.in);

    int integer = 0;
    do {
      System.out.print("Please enter a positive integer: ");
      if (stdIn.hasNextInt()){
        integer = stdIn.nextInt();
      }
      stdIn.nextLine();
    } while (integer <= 0);

    int sum = 0;
    while (integer > 0){
      sum += integer % 10;
      integer /= 10;
    }
    System.out.println("The sum of the digits is: " + sum);
  }

}
