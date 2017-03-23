import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// Anthony Phillips
// Project 4
// Program 1: Finding the Oldest
// Accepts a list of people and their ages and
// gives the user the name of the oldest person

public class Oldest{

    public static void main(String[] args){
        List<Person> people = new ArrayList<Person>();
        Person oldestPerson;

        // Note that we could just have a variable, "oldestAge",
        // outside the loop, and compare each successive person
        // (the class and array are not necessary for this program)

        // Input loop
        while (true){
            String name;
            int age;

            name = JOptionPane.showInputDialog("Please enter a person's name."
                + "\nEnter 'quit' to quit.");

            if (name.equals("quit"))
                break;

            // Age loop
            while (true){
                try{
                    age = Integer.parseInt(
                        JOptionPane.showInputDialog("Please enter an age for " + name + "."));
                    break;

                } catch (Exception ex){
                    // If parse failed, alert the user and reprompt
                    JOptionPane.showMessageDialog(null, "The age must be an integer number.");
                }
            }

            people.add(new Person(name, age));
        }

        // If the user didn't enter any people
        if (people.size() == 0){
            JOptionPane.showMessageDialog(null, "No people to compare.");
            return;
        }

        // Find the oldest person
        oldestPerson = people.stream()
            .max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
            .get();

        JOptionPane.showMessageDialog(null, oldestPerson.getName() + " is the oldest.");
    }

    // Person class
    private static class Person{

        private String name;

        public String getName(){
            return this.name;
        }

        private int age;

        public int getAge(){
            return this.age;
        }

        // Name and age constructor
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
    }
}
