import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.ArrayList;

public class InventoryManagement{

    private static final String SYS_TMPDIR = System.getProperty("user.home");
    private static final String SYS_FILE_SEP = System.getProperty("file.separator");
    private static final String DEFAULT_FILENAME = ".inventory_management.json";

    private static File file;

    private static List<Entry> entries = new ArrayList<Entry>();

    public static void main(String[] args){
        String filename = SYS_TMPDIR + SYS_FILE_SEP + DEFAULT_FILENAME;;

        if (args.length > 0)
            filename = args[0];

        file = new File(filename);

        if (file.exists()){
            if (!file.canWrite()){
                System.out.printf("Unable to write to file %s.%n", file.getAbsolutePath());
                return;
            }
            if (file.canRead()){
               try {
                   readInventory();
               } catch (IOException e){
                   System.out.printf("Error reading file %s.%n", file.getAbsolutePath());
                   return;
               }
            } else {
                System.out.printf("Unable to read file %s.%n", file.getAbsolutePath());
                return;
            }
        } else {
            try{
                file.createNewFile();
            } catch (IOException e){
                System.out.printf("Error creating file %s.%n", file.getAbsolutePath());
                return;
            }
        }

        System.out.println("Codes are entered as 1 to 8 characters.");
        System.out.println("Use \"e\" for enter, \"f\" for find, \"l\" for list, \"q\" for quit");

        Scanner stdIn = new Scanner(System.in);
        mainloop:
        while (true){
            System.out.print("Command: ");
            String input = stdIn.nextLine();

            String[] inputTokens = input.trim().split(" ");

            String command = inputTokens[0];
            String argument = inputTokens.length > 1 ? inputTokens[1] : null;

            switch (command){
                case "q":
                    try{
                        writeInventory();
                    } catch (IOException e){
                        System.out.printf("Unable to write to file %s.%n", file.getAbsolutePath());
                    }
                    break mainloop;
                case "l":
                    if (entries.isEmpty()){
                        System.out.println("No entries.");
                        continue;
                    }

                    for (Entry entry : entries)
                        System.out.println(entry.toString());
                    break;
                case "e":
                    if (argument == null){
                        System.out.printf("Command '%s' requires an argument.%n", command);
                        continue;
                    }

                    if (entries.contains(new Entry(argument))){
                        System.out.printf("Entry with code matching '%s' already exists.%n",
                            argument);
                        continue;
                    }

                    int quantity;
                    while (true){
                        System.out.print("Enter quantity: ");

                        if (stdIn.hasNextInt()){
                            quantity = stdIn.nextInt();
                            break;
                        }

                        System.out.println("Quantity must be an integer.");
                        stdIn.nextLine();
                    }

                    String notes;
                    System.out.print("Enter notes: ");
                    notes = stdIn.next();

                    entries.add(new Entry(argument, quantity, notes));

                    stdIn.nextLine();
                    break;
                case "f":
                    if (argument == null){
                        System.out.printf("Command '%s' requires an argument.%n", command);
                        continue;
                    }

                    if (entries.isEmpty()){
                        System.out.println("No entries.");
                        continue;
                    }

                    for (Entry entry : entries){
                        if (entry.equals(new Entry(argument))){
                            System.out.println(entry.toString());
                            break;
                        }
                    }
                    break;
                default:
                    System.out.printf("Invalid command '%s'.%n", command);
                    continue;
            }
        }
    }

    private static void writeInventory()
    throws IOException{
        if (file.canWrite()){
            Gson gson = new Gson();
            String json = gson.toJson(entries);
            Files.write(file.toPath(), json.getBytes());
        }
    }

    private static void readInventory()
    throws IOException{
        if (file.canRead()){
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(file.toPath()));
            if (!json.isEmpty()){
                entries = gson.fromJson(json, new TypeToken<List<Entry>>(){}.getType());
            }
        }
    }
}
