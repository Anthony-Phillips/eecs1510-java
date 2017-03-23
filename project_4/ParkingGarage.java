import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.stream.Collectors;

// Anthony Phillips
// Project 4
// Program 2: The Little Parking Garage
// Takes the parking hours of 5 cars and calculates the total
// hours and total charge using a defined pricing structure.

public class ParkingGarage{

    public static void main(String[] args){
        List<Double> hourSets = new ArrayList<Double>();
        BigDecimal cost = BigDecimal.valueOf(0);
        Scanner stdIn = new Scanner(System.in);

        // Prompt for hours
        for (int i = 1; i < 6; i++){
            double hours;

            // Get the time for car i
            while (true){
                System.out.print("Enter the hours parked for car " + i + ": ");

                if (stdIn.hasNextDouble()){
                    hours = stdIn.nextDouble();

                    if (hours >= 0)
                        break;
                }

                System.out.println("Invalid input; hours must be a positive decimal.");
                stdIn.nextLine();
            }

            // Add the time for car i to the array
            hourSets.add(hours);
        }

        // Calculate the cost
        for (double hourSet : hourSets){
            if (hourSet > 0){
                // Minimum fee is $5
                BigDecimal carCost = BigDecimal.valueOf(5);

                // After 2 hours, the charge is $1 per hour or part of an hour (Math.ceil)
                if (hourSet > 2){
                    carCost = carCost.add(BigDecimal.valueOf(Math.ceil(hourSet) - 2));
                }

                // The maximum charge is $12
                if (carCost.compareTo(BigDecimal.valueOf(12)) >= 0){
                    carCost = BigDecimal.valueOf(12);
                }

                // Add the charge to the total cost
                cost = cost.add(carCost);
            }
        }

        NumberFormat hoursFormatter = new DecimalFormat("#0.0");
        System.out.println("Total Hours " + hoursFormatter.format(hourSets.stream().collect(
            Collectors.summingDouble(hours->hours))));

        NumberFormat usCurrencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Total Charge " + usCurrencyFormatter.format(cost));
    }
}
