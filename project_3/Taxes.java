import java.math.BigDecimal;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;

// Anthony Phillips
// Calculates tax using marginal tax rates

public class Taxes {

  public static void main(String[] args) {
    Scanner stdIn = new Scanner(System.in);

    BigDecimal netIncome;
    do {
      System.out.print("Please enter your net taxable income: ");
      if (stdIn.hasNextDouble()){
        netIncome = BigDecimal.valueOf(stdIn.nextDouble());
        break;
      }
    } while (true);

    TaxBracket[] taxBrackets = {
      (new TaxBracket(Double.MIN_VALUE, 0D, 0D)),
      (new TaxBracket(0D, 9_275D, 0.10D)),
      (new TaxBracket(9_275D, 37_650D, 0.15D)),
      (new TaxBracket(37_650D, 91_150D, 0.25D)),
      (new TaxBracket(91_150D, 190_150D, 0.28D)),
      (new TaxBracket(190_150D, 413_350D, 0.33D)),
      (new TaxBracket(413_350D, 415_050D, 0.35D)),
      (new TaxBracket(415_050D, Double.MAX_VALUE, 0.396D))
    };

    BigDecimal incomePool = netIncome;
    BigDecimal tax = BigDecimal.ZERO;

    for (int i=0; i<taxBrackets.length; i++){
      TaxBracket taxBracket = taxBrackets[i];
      BigDecimal deducted = incomePool.subtract(taxBracket.getCeiling());

      if (deducted.compareTo(BigDecimal.ZERO) > 0){
        incomePool = deducted;
        tax = tax.add(taxBracket.getCeiling().multiply(taxBracket.getTaxRate()));
        continue;
      } else {
        tax = tax.add(incomePool.multiply(taxBracket.getTaxRate()));
        break;
      }
    }

    NumberFormat usCurrencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    System.out.println("Your tax is: " + usCurrencyFormatter.format(tax));
  }

  private static class TaxBracket{
    private BigDecimal floor;
    public BigDecimal getFloor(){ return this.floor; }

    private BigDecimal ceiling;
    public BigDecimal getCeiling(){ return this.ceiling; }

    private BigDecimal taxRate;
    public BigDecimal getTaxRate(){ return this.taxRate; }

    public TaxBracket(double floor, double ceiling, double taxRate) {
      this.floor = BigDecimal.valueOf(floor);
      this.ceiling = BigDecimal.valueOf(ceiling);
      this.taxRate = BigDecimal.valueOf(taxRate);
    }
  }
}
