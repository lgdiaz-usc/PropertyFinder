package PropertyFinder;

import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Team Dolphin (Liam Diaz and Sincere Dixon)
 * Collects the JSON data of a PSystem object and writes it to a file
 */
public class DataWriter {
    /**
     * Writes the JSON data of a PSystem object to a file
     * @param system The PSystem object whose JSON data is being written
     */
    public static void write(PSystem system){
        JSONObject data = system.toJSON();

        try{
            FileWriter writer = new FileWriter("data.json");
            writer.write(data.toJSONString());
            writer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void writeLease(String lease){
        try{
            FileWriter writer = new FileWriter("lease.txt");
            writer.write(lease);
            writer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
