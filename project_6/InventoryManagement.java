import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import inventory.Product;

public class InventoryManagement{
    
    private static final String defaultFilename = "InventoryManagement"; 

    private static File file;

    public static void main(String[] args){
        if (args.length > 0){
            initializeFile(args[0]);
        }
        else{
            System.out.println("No file specified.");
            String tmpdir = System.getProperty("java.io.tmpdir");
            String separator = System.getProperty("file.separator");
            initializeFile(tmpdir + separator + defaultFilename);
        }

        while (true){
            return;
        }
    }

    private static boolean writeInventory(){
        return true;
    }

    private static boolean readInventory(){
        return true;
    }

    private static void initializeFile(String filename){
        file = new File(filename);

        if (!file.exists()){
            System.out.printf("File %s does not exist.\n", file.getPath());
            System.out.println("Creating file.");
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Unable to create file.");
            }
            if (file.exists()){
                System.out.printf("File %s created.\n", file.getPath());
            }
        }
        else
            System.out.printf("Using pre-existing file %s.\n", file.getPath());
    }
}
