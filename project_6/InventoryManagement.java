import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class InventoryManagement{

    private static final String SYS_TMPDIR = System.getProperty("java.io.tmpdir");
    private static final String SYS_FILE_SEP = System.getProperty("file.separator");
    private static final String DEFAULT_FILENAME = "InventoryManagement";

    private static boolean isNewFile = false;

    private static File file;

    private static Entry[] entries = new Entry[200];

    public static void main(String[] args){
        String filename;
        if (args.length > 0)
            filename = args[0];
        else
            filename = SYS_TMPDIR + SYS_FILE_SEP + DEFAULT_FILENAME;

        if (!initializeFile(filename)){
            System.out.println("File initialization failed. Aborting.");
            return;
        }

        if (!isNewFile){
           try {
               readInventory();
               System.out.println("Successfully read file.");
           } catch (Exception ex){
               System.out.printf("Unable to read file %s.%n", file.getAbsolutePath());
               return;
           }
        }

        Scanner stdIn = new Scanner(System.in);
        mainloop:
        while (true){
            System.out.print("Command: ");
            String input = stdIn.nextLine();

            String[] inputTokens = input.split(" ");

            String command = inputTokens[0];

            String argument = null;
            if (inputTokens.length > 1){
                argument = inputTokens[1];
            }

            switch (command){
                case "q":
                    break mainloop;
                case "l":
                    listEntries();
                    break;
                case "e":
                    if (argument == null){
                        System.out.printf("Command '%s' requires one argument.%n", command);
                        continue;
                    }
                    createEntry();
                    break;
                case "f":
                    if (argument == null){
                        System.out.printf("Command '%s' requires one argument.%n", command);
                        continue;
                    }
                    findEntry();
                    break;
                default:
                    System.out.println("Invalid command: " + command);
                    continue;
            }
        }
    }

    private static void findEntry(){

    }

    private static void listEntries(){

    }

    private static void createEntry(){

    }

    private static boolean writeInventory()
    throws Exception{
        if (file.canWrite()){
            return true;
        }
        return false;
    }

    private static boolean readInventory(){
        if (file.canRead()){
            return true;
        }
        return false;
    }

    private static boolean initializeFile(String filename){
        file = new File(filename);

        if (!file.exists()){
            isNewFile = true;
            System.out.printf("File %s does not exist.%n", file.getAbsolutePath());
            System.out.println("Creating file.");
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Unable to create file.");
                return false;
            }
            if (file.exists())
                System.out.printf("File %s successfully created.%n", file.getAbsolutePath());
        } else
            System.out.printf("Using existing file %s.%n", file.getAbsolutePath());

        if (file.isDirectory()){
            System.out.println("Unable to use file: it is a directory");
            return false;
        }

        return true;
    }
}
