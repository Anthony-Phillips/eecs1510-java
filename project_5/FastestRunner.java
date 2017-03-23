import java.util.Arrays;
import java.util.Formatter;

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

            System.out.println(asciiTable(runners));

            Arrays.sort(runners);

            System.out.println(runners[0].getName());
            System.out.println(runners[1].getName());
    }

    private static String asciiTable(Runner[] runners){
        String outputTable = "";

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

        int totalBufferLength = longestNameLength + longestTimeLength;

        outputTable = String.format("+%1$" + totalBufferLength + "+" , "-");

        for (Runner runner : runners){

        }

        return outputTable;
    }

    private static class Runner
        implements Comparable<Runner>{

        private String name;

        public String getName(){
            return this.name;
        }

        private Integer time;

        public Integer getTime(){
            return this.time;
        }

        public Runner(String name, Integer time){
            this.name = name;
            this.time = time;
        }

        public int compareTo(Runner o){
            return this.time.compareTo(o.getTime());
        }
    }
}
