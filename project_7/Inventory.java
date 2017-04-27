import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Inventory{

    public Inventory(){
        this.entries = new ArrayList<Entry>();
    }

    /*
     * The list of entries that this class manages
     */

    private List<Entry> entries;

    public List<Entry> getEntries(){
        return this.entries;
    }

    public void setEntries(List<Entry> entries){
        this.entries = entries;
    }

    /*
     * The file the inventory will be reading / writing to
     */

    private File file;

    public File getFile(){
        return this.file;
    }

    public void setFile(File file){
        this.file = file;
    }

    public void setFile(String absolutePath){
        this.setFile(new File(absolutePath));
    }

    /*
     * Read the JSON contents of a file and populate this.entries
     */

    public boolean readFile(){
        return this.readFile(this.getFile());
    }

    public boolean readFile(String absolutePath){
        return this.readFile(new File(absolutePath));
    }

    public boolean readFile(File file){
        Gson gson = new Gson();
        String json;

        try {
            json = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e){
            return false;
        }

        if (!json.isEmpty()){
            this.setEntries(
                gson.fromJson(json, new TypeToken<List<Entry>>(){}.getType()));
        }

        return true;
    }

    /*
     * Write the current state of this.entries to a file
     */

    public boolean writeFile(){
        return this.writeFile(this.getFile());
    }

    public boolean writeFile(String absolutePath){
        return this.writeFile(new File(absolutePath));
    }

    public boolean writeFile(File file){
        Gson gson = new Gson();
        String json = gson.toJson(entries);

        try {
            Files.write(file.toPath(), json.getBytes());
        } catch (IOException e){
            return false;
        }

        return true;
    }

    /*
     * CRUD methods
     */

    // Create
    public void insert(Entry entry){
        this.entries.add(entry);
        Collections.sort(this.entries);
    }

    // Read
    public List<Entry> select(String query){
        List<Entry> resultSet = new ArrayList<Entry>();
        for (Entry entry : this.getEntries()){
            if (entry.equals(query)){
                resultSet.add(entry);
            }
        }
        return resultSet;
    }

    // Update
    public boolean update(Entry entry){
        this.entries.set()
    }

    // Delete
}
