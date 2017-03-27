// Uses StringUtils.rightPad from the Apache Commons Lang library v3.5
// https://commons.apache.org/proper/commons-lang/download_lang.cgi
import org.apache.commons.lang3.StringUtils;

// Anthony Phillips
// Project 5
// Program 3: Fastest Runner
// Takes in a string and tells whether it is valid or not.
// Valid phone numbers match a the pattern given in the assignment.

public class FastestRunner{

    public static void main(String[] args){
        // I'd really prefer to make a "Runner" class and populate a single
        // array of such objects, but the assignment specifies to do this:
        String[] names = {
            "Elena",    "Thomas",   "Hamilton", "Suzie",
            "Phil",     "Matt",     "Alex",     "Emma",
            "John",     "James",    "Jane",     "Emily",
            "Daniel",   "Neda",     "Aaron",    "Kate"
        };

        Integer[] times = {
            341,    273,    278,    329,
            445,    402,    388,    275,
            243,    334,    412,    393,
            299,    343,    317,    265
        };

        System.out.println(runnerAsciiTable(names, times));

        // If I could have, I would have done an Arrays.sort() on the Runner[]
        // and then just called fastest with runners[0] and second-fastest with runners[1]
        System.out.println("The fastest runner is: " + names[fastestIndex(times)]);
        System.out.println("The second-fastest runner is: " + names[secondFastestIndex(times)]);
    }

    // Prints an ascii-style table containing the
    // names of runners and their corresponding times
    private static String runnerAsciiTable(String[] names, Integer[] times){
        int longestNameLength = 0;

        for (String name : names){
            int nameLength = name.length();

            if (nameLength > longestNameLength)
                longestNameLength = nameLength;
        }

        int longestTimeLength = 0;

        for (Integer time : times){
            int timeLength = time.toString().length();

            if (timeLength > longestTimeLength)
                longestTimeLength = timeLength;
        }

        // +---+---+
        String outputTable = "+" + StringUtils.rightPad("", longestNameLength, '-') + "+"
            + StringUtils.rightPad("", longestTimeLength, '-') + "+\n";

        for (int i = 0; i < names.length || i < times.length; i++){
            // |Name|Time|
            outputTable += "|" + StringUtils.rightPad(names[i], longestNameLength) + "|"
                + StringUtils.rightPad(times[i].toString(), longestTimeLength) + "|\n";
            // +---+---+
            outputTable += "+" + StringUtils.rightPad("", longestNameLength, '-') + "+"
                + StringUtils.rightPad("", longestTimeLength, '-') + "+\n";
        }

        return outputTable;
    }

    // Returns index of the fastest runner
    private static int fastestIndex(Integer[] times){
        int fastestIndex = 0;
        int fastestTime = Integer.MAX_VALUE;

        for (int i = 0; i < times.length; i++){
            int time = times[i];

            if (time < fastestTime){
                fastestTime = time;
                fastestIndex = i;
            }
        }

        return fastestIndex;
    }

    // Returns index of the second-fastest runner
    private static int secondFastestIndex(Integer[] times){
        int fastestTime = times[fastestIndex(times)];
        int secondFastestIndex = 0;
        int secondFastestTime = Integer.MAX_VALUE;

        for (int i = 0; i < times.length; i++){
            int time = times[i];

            if (time < secondFastestTime && time > fastestTime){
                secondFastestTime = time;
                secondFastestIndex = i;
            }
        }

        return secondFastestIndex;
    }
}
