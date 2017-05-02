import javafx.application.Application;
import javafx.stage.*;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.*;
import javafx.util.converter.DefaultStringConverter;

// Anthony Phillips
// Final Project
// This is a GUI application that allows for the management of a file-based inventory

public class InventoryManagement extends Application{
    // The "primary" stage
    private static Stage window;

    /* javafx comes with a neat ObservableList class which will allow
     * us to bind a list to the table we will use in the GUI.
     * For this reason, I found it unnecessary to create an inventory
     * class that acts as a wrapper for the list of entries;
     * ObservableList already contains add, delete, sort, and contains functionality
     */
    private static ObservableList<Entry> inventory;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(final Stage stage){
        window = stage;
        window.setTitle("Inventory Management");
        window.show();

        List<String> args = getParameters().getRaw();
        if (args.size() > 0)
            inventoryManager(new File(args.get(0)));

        // Load Inventory Functionality
        Button loadInventoryBtn = new Button("Load Inventory");
        loadInventoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                inventoryManager(fileChooser.showOpenDialog(new Stage()));
            }
        });

        // New Inventory functionality
        Button newInventoryBtn = new Button("New Inventory");
        newInventoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                inventoryManager(null);
            }
        });

        VBox vbox = new VBox();
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(newInventoryBtn, loadInventoryBtn);

        window.setScene(new Scene(vbox, 400, 400));
    }

    private static void inventoryManager(File file){

        if (file != null){
            // Try to load the file
            if (!load(file)){
                // If they gave us a file that we can't load,
                // let them know and return to the load file screen
                alert("Unable to load file " + file.getAbsolutePath());
                return;
            }
        } else {
            // If they chose "new file", create a new empty list
            inventory = FXCollections.observableArrayList();
        }

        // The table we will be interfacing with
        TableView<Entry> table = new TableView<Entry>();
        table.setMinHeight(600);
        table.setEditable(true);

        // Code Column
        TableColumn<Entry, String> codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(
            new PropertyValueFactory<Entry, String>("code"));
        codeCol.setMinWidth(100);

        // Quantity Column
        TableColumn<Entry, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Entry, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Entry, Integer> edit) {
                    int quantity = edit.getNewValue();
                    if (quantity < 0){
                        alert("Quantity must be greater than or equal to zero.");

                        // Reset to the old value
                        edit.getTableView().getItems().get(
                        edit.getTablePosition().getRow()).setQuantity(edit.getOldValue());

                        // Re-render with the old value
                        edit.getTableView().getColumns().get(0).setVisible(false);
                        edit.getTableView().getColumns().get(0).setVisible(true);
                        return;
                    }
                    edit.getTableView().getItems().get(
                    edit.getTablePosition().getRow()).setQuantity(quantity);
                }
            }
        );
        quantityCol.setCellValueFactory(
            new PropertyValueFactory<Entry, Integer>("quantity"));
        quantityCol.setMinWidth(100);

        // Note Column
        TableColumn<Entry, String> noteCol = new TableColumn<>("Note");
        noteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        noteCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Entry, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Entry, String> edit) {
                    edit.getTableView().getItems().get(
                    edit.getTablePosition().getRow()).setNote(edit.getNewValue());
                }
            }
        );
        noteCol.setCellValueFactory(
            new PropertyValueFactory<Entry, String>("note"));
        noteCol.setMinWidth(300);

        // This will bind the inventory to the table;
        // any changes made in the table (edits) or to the list
        // (add / delete) will be propagated both ways.
        // For this reason, I found it unnecessary to create an inventory
        // class that acts as a wrapper for the list of entries;
        // ObservableList already contains add, delete, sort, and contains functionality
        table.getColumns().addAll(codeCol, quantityCol, noteCol);
        table.setItems(inventory);

        // Add Functionality
        TextField codeTxt = new TextField();
        codeTxt.setPromptText("Code");
        TextField quantityTxt = new TextField();
        quantityTxt.setPromptText("Quantity");
        TextField noteTxt = new TextField();
        noteTxt.setPromptText("Note");

        Button addBtn = new Button("Add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Make sure they entered a code
                if (codeTxt.getText().length() == 0){
                    alert("Please enter a code.");
                    return;
                }

                Entry entry = new Entry(codeTxt.getText());

                // Make sure the entry is unique
                if (inventory.contains(entry)){
                    alert("An entry already exists with that code.");
                    return;
                }

                // Make sure the quantity is an int
                int quantity;
                try{
                    quantity = Integer.parseInt(quantityTxt.getText());
                } catch (Exception ex)
                {
                    alert("Quantity must be an integer.");
                    return;
                }

                if (quantity < 0){
                    alert("Quantity must be greater than or equal to zero.");
                    return;
                }

                entry.setNote(noteTxt.getText());

                // Add and sort
                inventory.add(entry);
                FXCollections.sort(inventory);

                codeTxt.clear();
                noteTxt.clear();
                quantityTxt.clear();
            }
        });

        // Delete Functionality
        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                // Remove selected rows from table
                inventory.removeAll(table.getSelectionModel().getSelectedItems());
                table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
            }
        });

        // Save functionality
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent e){
               File saveFile = file;

               // If the file is null, prompt for the file
               if (saveFile == null){
                   FileChooser fileChooser = new FileChooser();
                   saveFile = fileChooser.showSaveDialog(new Stage());
               }

               // Notify the user of save success / failure
               if (!save(saveFile))
                   alert("Unable to save file " + file.getAbsolutePath());
               else
                   alert("Successfully saved to file.");
           }
        });

        // Search functionality
        TextField searchTxt = new TextField();
        searchTxt.setPromptText("Search");
        searchTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                // If the query is blank, just show the entire inventory
                if (newValue.length() == 0){
                    table.setItems(inventory);
                    return;
                }

                // Query will be case insensitive
                String query = newValue.toLowerCase();
                ObservableList<Entry> resultSet =
                    FXCollections.observableArrayList();

                // Populate the resultset
                for (Entry entry : inventory){
                    if (entry.getCode().toLowerCase().contains(query)
                     || entry.getNote().toLowerCase().contains(query)){

                        resultSet.add(entry);
                    }
                }

                // Display the resultset
                table.setItems(resultSet);
            }
        });

        HBox topHbox = new HBox();
        topHbox.getChildren().addAll(saveBtn, searchTxt);
        topHbox.setPadding(new Insets(10,10,10,10));
        topHbox.setSpacing(10);

        HBox bottomHbox = new HBox();
        bottomHbox.setPadding(new Insets(10,10,10,10));
        bottomHbox.setSpacing(10);
        bottomHbox.getChildren().addAll(codeTxt, quantityTxt,
                                  noteTxt, addBtn, deleteBtn);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(topHbox, table, bottomHbox);

        window.setScene(new Scene(vbox, 700, 750));
    }

    // Allows us to pass a string that will alert the user
    private static void alert (String message){
        Stage alert = new Stage();

        VBox vbox = new VBox(new Text(message));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        alert.setScene(new Scene(vbox));
        alert.show();
    };

    // Loads the contents of a file into the inventory ObservableList
    private static boolean load(File file){
        Gson gson = new Gson();
        String json;

        // Try to get the JSON string from the file
        try {
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e){
            return false;
        }

        // If the JSON string is not empty, parse it into a list
        if (!json.isEmpty()){
            try {
            inventory = FXCollections.observableList(gson.fromJson(json,
                new TypeToken<ObservableList<Entry>>(){}.getType()));
            } catch (JsonParseException e){
                return false;
            }
        }
        return true;
    }

    // Saves the current inventory to the specified file
    private static boolean save(File file){
        Gson gson = new Gson();

        // Convert the list to a JSON string
        String json = gson.toJson(inventory);

        // Write the json string to the file
        try {
            Files.write(file.toPath(), json.getBytes());
        } catch (IOException e){
            return false;
        }

        return true;
    }

}
