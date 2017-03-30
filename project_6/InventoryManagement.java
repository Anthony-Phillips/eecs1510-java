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

        if (file.isDirectory()){
            System.out.println("Unable to use file: it is a directory");
            return;
        }

        while (true){
            return;
        }
    }

    private static boolean writeInventory()
    throws Exception{
        if (file.canWrite()){

        }
        return true;
    }

    private static boolean readInventory(){
        if (file.canRead()){
            
        }
        return true;
    }

    private static void initializeFile(String filename){
        file = new File(filename);

        if (!file.exists()){
            System.out.printf("File %s does not exist.\n", file.getAbsolutePath());
            System.out.println("Creating file.");
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Unable to create file.");
            }
            if (file.exists()){
                System.out.printf("File %s created.\n", file.getAbsolutePath());
            }
        }
        else 
            System.out.printf("Using existing file %s.\n", file.getAbsolutePath());
    }
}
