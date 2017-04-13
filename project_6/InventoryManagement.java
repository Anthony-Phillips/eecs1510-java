import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.ArrayList;

// Anthony Phillips / Alexander Beck
// Project 6 : Inventory Management
// This program acts as an inventory management application
// that allows the user to create and read inventory entries

public class InventoryManagement{

    // Grab platform-dependent system properties
    private static final String SYS_USER_HOME = System.getProperty("user.home");
    private static final String SYS_FILE_SEP = System.getProperty("file.separator");

    // The default filename
    private static final String DEFAULT_FILENAME = ".inventory_management.json";

    // The file we will be interacting with
    private static File file;

    // The array of entries
    private static List<Entry> entries = new ArrayList<Entry>();

    public static void main(String[] args){
        // Set the default absolute path
        String filename = SYS_USER_HOME + SYS_FILE_SEP + DEFAULT_FILENAME;;

        // If the user supplied a file, override the default
        if (args.length > 0)
            filename = args[0];

        file = new File(filename);

        // Perform file checks
        if (file.exists()){
            if (!file.canWrite()){
                // If we can't write to the file, return
                System.out.printf("Unable to write to file %s%n", file.getAbsolutePath());
                return;
            }
            if (file.canRead()){
                // If we can read the file, load its contents
                try {
                    readInventory();
                    System.out.printf("Loaded file %s%n%n", file.getAbsolutePath());
                } catch (IOException e){
                    System.out.printf("Error reading file %s%n", file.getAbsolutePath());
                    return;
                }
            } else {
                // If we can't read the file, return
                System.out.printf("Unable to read file %s%n", file.getAbsolutePath());
                return;
            }
        } else {
            // If the file doesn't exist, try creating it
            try{
                file.createNewFile();
                System.out.printf("Created new file %s.%n%n", file.getAbsolutePath());
            } catch (IOException e){
                System.out.printf("Error creating file %s%n", file.getAbsolutePath());
                return;
            }
        }

        System.out.println("Codes are entered as 1 to 8 characters.");
        System.out.println("Use \"e\" for enter, \"f\" for find, \"l\" for list, \"q\" for quit");

        Scanner stdIn = new Scanner(System.in);

        // Main loop
        mainLoop:
        while (true){
            System.out.printf("%nCommand: ");
            String input = stdIn.nextLine();

            String[] inputTokens = input.trim().split(" ");

            // Grab the command (first token) and argument (second token, if it exists)
            String command = inputTokens[0];
            String argument = inputTokens.length > 1 ? inputTokens[1] : null;

            switch (command){
                // ENTER
                case "e":
                    // Make sure the user supplied an argument
                    if (argument == null){
                        System.out.printf("Command '%s' requires an argument.%n", command);
                        continue;
                    }

                    // Make sure the entry doesn't already exist
                    if (entries.contains(new Entry(argument))){
                        System.out.printf("Entry with code matching '%s' already exists.%n",
                            argument);
                        continue;
                    }

                    // Grab the quantity
                    int quantity;
                    while (true){
                        System.out.print("Enter quantity: ");

                        if (stdIn.hasNextInt()){
                            quantity = stdIn.nextInt();
                            stdIn.nextLine();
                            break;
                        }

                        System.out.println("Quantity must be an integer.");
                        stdIn.nextLine();
                    }

                    // Grab the notes
                    String notes;
                    System.out.print("Enter notes: ");
                    notes = stdIn.nextLine();

                    // Add the entry
                    entries.add(new Entry(argument, quantity, notes));

                    break;

                // FIND
                case "f":
                    // Make sure the user supplied an argument
                    if (argument == null){
                        System.out.printf("Command '%s' requires an argument.%n", command);
                        continue;
                    }

                    // Make sure we have entries to search
                    if (entries.isEmpty()){
                        System.out.println("No entries.");
                        continue;
                    }

                    // Find and display the entry
                    for (Entry entry : entries){
                        if (entry.equals(new Entry(argument))){
                            System.out.println(entry.toString());
                            continue mainLoop;
                        }
                    }

                    // If we couldn't find the entry, convey that to the user
                    System.out.println("Entry not found.");
                    break;

                // LIST
                case "l":
                    // Use the required static method to list all entries
                    listAllEntries();
                    break;

                // QUIT
                case "q":
                    // Write to the inventory, then return
                    try{
                        writeInventory();
                        System.out.printf("Saved to file %s%n", file.getAbsolutePath());
                    } catch (IOException e){
                        System.out.printf("Error writing to file %s%n", file.getAbsolutePath());
                    }
                    return;

                // DEFAULT
                default:
                    System.out.printf("Invalid command '%s'.%n", command);
                    continue;
            }
        }
    }

    // Required static method to list all entries
    private static void listAllEntries(){
        // Make sure we have entries to display
        if (entries.isEmpty()){
            System.out.println("No entries.");
            return;
        }

        // List the entries
        for (Entry entry : entries)
            System.out.println(entry.toString());
    }

    // Converts the array to a JSON string and writes it to the file
    private static void writeInventory()
    throws IOException{
        // Check if we can write to the file
        if (file.canWrite()){
            Gson gson = new Gson();

            // If we can write to the file, convert the entries
            // array to a JSON string, and write it to the file
            String json = gson.toJson(entries);
            Files.write(file.toPath(), json.getBytes());
        }
    }

    // Grabs the JSON string from the file and populates the array
    private static void readInventory()
    throws IOException{
        // Check if we can read the file
        if (file.canRead()){
            Gson gson = new Gson();

            // If we can read the file, grab the JSON string
            // from the file and populate the entries array
            String json = new String(Files.readAllBytes(file.toPath()));
            if (!json.isEmpty()){
                entries = gson.fromJson(json, new TypeToken<List<Entry>>(){}.getType());
            }
        }
    }
}
