import java.util.Scanner;

public class CalcAverage {
//  Written by: Anthony Phillips
//  Computes the average of a set of values entered by the user, e.g. with
//       10.0  5.0  6.0  9.0  0.0
//  the average is 7.5

  public static void main (String[] args){
    double runningTotal = 0;
    int count = 0;
    double number;
    Scanner stdIn = new Scanner(System.in);

    System.out.println("Type the numbers, the last being 0");
    number = stdIn.nextDouble();
    while (number != 0) {
      runningTotal += number;
      count++;
      number = stdIn.nextDouble();
    }

    double average = runningTotal/count;

    System.out.print("The average of the ");
    System.out.print(count);
    System.out.print(" numbers is ");
    System.out.println(average);
  }

}
