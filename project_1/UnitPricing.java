import java.math.BigDecimal;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;

public class UnitPricing {

  public static void main (String[] args){
    Scanner stdIn = new Scanner(System.in);

    int quantity;
    do {
      System.out.print("Please enter the Quantity desired as an integer: ");
      if (stdIn.hasNextInt()){
        quantity = stdIn.nextInt();
        break;
      }
      stdIn.nextLine();
    } while (true);

    BigDecimal unitPrice;
    do {
      System.out.print("Please enter the Unit price as a decimal: ");
      if (stdIn.hasNextBigDecimal()){
        unitPrice = stdIn.nextBigDecimal();
        break;
      }
      stdIn.nextLine();
    } while (true);

    BigDecimal totalAmount = unitPrice.multiply(new BigDecimal(quantity));

    NumberFormat usCurrencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    System.out.println("The Quantity desired is : " + quantity);
    System.out.println("The Unit Price is       : " + usCurrencyFormatter.format(unitPrice));
    System.out.println("The Total Amount is     : " + usCurrencyFormatter.format(totalAmount));
  }

}
