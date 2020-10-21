package PropertyFinder;

import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Reads JSON data from a JSON file and converts it into a PSystem object
 */
public class DataReader {
    /**
     * Converts a JSON file into a PSystem object
     * @param fileName The name of the JSON file being read from
     * @return The PSystem object created from the data in the JSON file
     */
    public PSystem read(String fileName){
        return null;
    }

    /**
     * Converts a JSONObject into a User object
     * @param Juser The JSONObject being converted
     * @return The User object created from the JSONObject
     */
    private User toUser(JSONObject Juser){
        return null;
    }

    /**
     * Converts a JSONObject into a PropertyManager object
     * @param Jmanager The JSONObject being converted
     * @return The PropertyManager object created from the JSONObject
     */
    private PropertyManager toManager(JSONObject Jmanager){
        return null;
    }

    /**
     * Converts a JSONObject into a Property object
     * @param Jproperty The JSONObject being converted
     * @return The Property object created from the JSONObject
     */
    private Property toProperty(JSONObject Jproperty){
        return null;
    }
}
