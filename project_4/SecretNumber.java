import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

// Anthony Phillips
// Project 4
// Program 4: Secret Number
// Generates a secret number that the user must
// attempt to guess. Hints are provided along the way.

public class SecretNumber{

    public static void main(String[] args){
        int random;
        random = ThreadLocalRandom.current().nextInt(0, 101);

        // Guessing loop
        while (true){
            int guess;
            int difference;
            Boolean isHigh;
            Intensifier intensifier;
            String input;

            input = JOptionPane.showInputDialog("Please enter your guess." +
                "\n Enter '0' to quit.");

            if (input.equals("0")){
                return;
            }

            try {
                guess = Integer.parseInt(input);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Guess must be an integer.");
                continue;
            }

            if (guess == random){
                JOptionPane.showMessageDialog(null, "You have guessed correctly!");
                return;
            }

            // Get the difference
            difference = guess - random;

            // If the difference is positive, the guess was high
            isHigh = difference > 0;

            // Get the absolute value of the difference
            difference = Math.abs(difference);

            // Set the intensifier
            if (difference <= 10){
                intensifier = Intensifier.small;
            } else if (difference <= 30){
                intensifier = Intensifier.medium;
            } else {
                intensifier = Intensifier.large;
            }

            // Give the user their hint
            JOptionPane.showMessageDialog(null, String.format("Your guess was %1$s %2$s.",
                intensifier.toString(), isHigh? "high" : "low"));
        }
    }

    // Enum for intensifier (not really necessary)
    private enum Intensifier {
        small,
        medium,
        large;

        // Overridden toString for printing output
        @Override
        public String toString(){
            switch (this){
                case small:
                    return "a little";
                case medium:
                    return "moderately";
                case large:
                    return "way too";
                default:
                    return "";
            }
        }
    }
}
