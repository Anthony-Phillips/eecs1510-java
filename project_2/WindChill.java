import java.text.DecimalFormat;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;

// Anthony Phillips
// Calculates wind chill using temperature and wind speed

public class WindChill {

  public static void main (String[] args){
    Scanner stdIn = new Scanner(System.in);

    double T = 42.0;
    do {
      System.out.print("Enter temperature (Fahrenheit) between -58 and 41: ");
      if (stdIn.hasNextDouble()){
        T = stdIn.nextDouble();
      }
      stdIn.nextLine();
    } while (T < -58.0 || T > 41.0);

    double V = 0.0;
    do {
      System.out.print("Enter wind speed (mph) greater than 2: ");
      if (stdIn.hasNextDouble()){
        V = stdIn.nextDouble();
      }
      stdIn.nextLine();
    } while (V <= 2.0);

    double windChill = (35.74)+(0.6215*T)-(35.75*Math.pow(V, 0.16))+(0.4275*T*Math.pow(V, 0.16));

    DecimalFormat usFormat = (DecimalFormat)(NumberFormat.getNumberInstance(Locale.US));
    usFormat.applyPattern("#0.00");
    System.out.println("The wind chill index is " + usFormat.format(windChill));
  }

}
