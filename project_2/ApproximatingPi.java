// Anthony Phillips
// Prints a rough approximation of pi

public class ApproximatingPi {

  public static void main (String[] args) {
    double divisor = 1.0;
    double multiplicand = 0;
    boolean isNegative = false;

    // There's probably a cleaner way to do this (via loop)
    for (int i = 0; i < 7; i++){
      multiplicand += (1.0 / (isNegative ? divisor * -1.0 : divisor));
      divisor += 2;
      isNegative = !isNegative;
    }

    System.out.println(4.0 * multiplicand);
  }

}
