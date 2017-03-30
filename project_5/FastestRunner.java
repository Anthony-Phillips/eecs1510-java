// Uses StringUtils.rightPad from the Apache Commons Lang library v3.5
// https://commons.apache.org/proper/commons-lang/download_lang.cgi
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

// Anthony Phillips
// Project 5
// Program 3: Fastest Runner
// Pre-populates an array of Runners, prints the contents of the array, 
// then lists the fastest and second-fastest runners and their corresponding times.

public class FastestRunner{

    public static void main(String[] args){
            Runner[] runners = {
                new Runner("Elena", 341),
                new Runner("Thomas", 273),
                new Runner("Hamilton", 278),
                new Runner("Suzie", 329),
                new Runner("Phil", 445),
                new Runner("Matt", 402),
                new Runner("Alex", 388),
                new Runner("Emma", 275),
                new Runner("John", 243),
                new Runner("James", 334),
                new Runner("Jane", 412),
                new Runner("Emily", 393),
                new Runner("Daniel", 299),
                new Runner("Neda", 343),
                new Runner("Aaron", 317),
                new Runner("Kate", 265),
            };

            System.out.println(Runner.asciiTable(runners));

            Arrays.sort(runners);

            System.out.println(runners[0].toString());
            System.out.println(runners[1].toString());
    }

    // Private Runner class
    // Implements Comparable<T> and
    private static class Runner
        implements Comparable<Runner>{

        // Name (read-only)
        private final String name;

        public String getName(){
            return this.name;
        }

        // Time in minutes (read-only)
        private final Integer time;

        public Integer getTime(){
            return this.time;
        }

        // Constructor
        public Runner(String name, Integer time){
            this.name = name;
            this.time = time;
        }

        // Prints an ascii-style table containing the
        // names of runners and their corresponding times
        public static String asciiTable(Runner[] runners){
            int longestNameLength = 0;
            int longestTimeLength = 0;

            for (Runner runner : runners){
                int nameLength = runner.getName().length();
                int timeLength = runner.getTime().toString().length();

                if (nameLength > longestNameLength)
                    longestNameLength = nameLength;

                if (timeLength > longestTimeLength)
                    longestTimeLength = timeLength;
            }

            // +---+---+
            String outputTable = "+" + StringUtils.rightPad("", longestNameLength, '-') + "+"
                + StringUtils.rightPad("", longestTimeLength, '-') + "+\n";

            for (Runner runner : runners){
                // |Name|Time|
                outputTable += "|" + StringUtils.rightPad(runner.getName(), longestNameLength) + "|"
                    + StringUtils.rightPad(runner.getTime().toString(), longestTimeLength) + "|\n";
                // +---+---+
                outputTable += "+" + StringUtils.rightPad("", longestNameLength, '-') + "+"
                    + StringUtils.rightPad("", longestTimeLength, '-') + "+\n";
            }

            return outputTable;
        }

        // Overridden toString()
        public String toString(){
            return String.format("%s, %dh %dm", this.getName(),
                this.getTime() / 60, this.getTime() % 60);
        }

        // Implementation of Comparable<T>
        public int compareTo(Runner o){
            return this.getTime().compareTo(o.getTime());
        }
    }
}
