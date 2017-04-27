import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;

public class InventoryManagement extends Application{

    // Grab platform-dependent system properties
    private static final String SYS_USER_HOME = System.getProperty("user.home");
    private static final String SYS_FILE_SEP = System.getProperty("file.separator");

    // The default filename
    private static final String DEFAULT_FILENAME = ".inventory_management.json";

    // The file we will be interacting with
    private static File file;
    
    private static Inventory inventory;
    
    public static void main(String[] args){
        
        // Set the default absolute path
        String filename = SYS_USER_HOME + SYS_FILE_SEP + DEFAULT_FILENAME;

        // If the user supplied a file, override the default
        if (args.length > 0)
            filename = args[0];
        
        file = new File(filename);
        
        inventory = new Inventory(file);
        inventory.load();
    }
    
    
    @Override
    public void start(final Stage stage){
        stage.setTitle("Inventory Management");

        

        stage.show();
    }
}
