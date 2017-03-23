import java.util.Scanner;

// Anthony Phillips
// Provides a secret code that the user must guess

public class ThreeGuesses {

  public static void main(String[] args) {
    Scanner stdIn = new Scanner(System.in);
    int secret = 45;
    int guess;

    System.out.println("Welcome to People's Bank.");

    System.out.print("Please enter your secret code: ");
    guess = stdIn.nextInt();

    if (guess == secret){
      System.out.println("Fine, go ahead.");
    } else {
      System.out.print("Sorry, that is not it. Try again: ");
      guess = stdIn.nextInt();

      if (guess == secret){
        System.out.println("Fine, go ahead.");
      } else {
        System.out.print("Sorry, last chance. Try again: ");
        guess = stdIn.nextInt();

        if (guess == secret){
          System.out.println("Fine, go ahead.");
        } else {
          System.out.println("Sorry, that was your last chance.");
        }
      }
    }
  }
}
