import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class InventoryManagement{

    private static String filename;

    public static void main(String[] args){
        if (args.length > 0){
            filename = args[0];
        }
        else{
             filename = System.getProperty("java.io.tmpdir") + "/InventoryManagement";
             System.out.printf("No file specified. Using %s.\n", filename);
        }

        File file = new File(filename);

        if (!file.exists()){
            System.out.printf("File %s does not exist. Creating file.\n", filename);
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Unable to create file.");
            }
            if (file.exists()){
                System.out.printf("File %s created.\n", filename);
            }
        }
        else{
            System.out.printf("Using pre-existing file %s.\n", filename);
        }

        System.out.println(filename);

        while (true){
            return;
        }
    }

    private static boolean writeInventory(){return true;}
}
