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
    public static PSystem read(String fileName){
        try{
            FileReader reader = new FileReader(fileName);
            JSONParser parser = new JSONParser();
            JSONObject systemJSON = (JSONObject)parser.parse(reader);

            //Parses User array
            JSONArray userJArray = (JSONArray)systemJSON.get("users");
            ArrayList<User> users = new ArrayList<User>();
            for(Object user : userJArray){
                users.add(toUser((JSONObject)user));
            }

            //Parse PropertyManager Array
            JSONArray managerJArray = (JSONArray)systemJSON.get("property managers");
            ArrayList<PropertyManager> propertyManagers = new ArrayList<PropertyManager>();
            for(Object manager : managerJArray){
                propertyManagers.add(toManager((JSONObject)manager));
            }

            //Parses Property array
            JSONArray propertyJArray = (JSONArray)systemJSON.get("properties");
            ArrayList<Property> properties = new ArrayList<Property>();
            for(Object property : propertyJArray){
                properties.add(toProperty((JSONObject)property));
            }

            PSystem system = new PSystem(properties, users, propertyManagers);
            return system;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a JSONObject into a User object
     * @param Juser The JSONObject being converted
     * @return The User object created from the JSONObject
     */
    private static User toUser(JSONObject Juser){
        String username = (String)Juser.get("username");
        String password = (String)Juser.get("password");
        String name = (String)Juser.get("name");
        String dateOfBirth = (String)Juser.get("date of birth");
        String homeAddress = (String)Juser.get("home address");
        String email = (String)Juser.get("e-mail");
        String phoneNumber = (String)Juser.get("phone number");
        String studentID = (String)Juser.get("student ID");
        int creditScore = Math.toIntExact((long)Juser.get("credit score"));
        User user = new User(username, password, name, dateOfBirth,
                             homeAddress, email, phoneNumber, studentID);

        JSONArray reviewArray = (JSONArray)Juser.get("reviews");
        for(Object reviewObject : reviewArray){
            JSONObject Jreview = (JSONObject)reviewObject;
            int rating = (int)Jreview.get("rating");
            String Rtitle = (String)Jreview.get("title");
            String Rdescription = (String)Jreview.get("description");
            String author = (String)Jreview.get("author");
            user.addReview(rating, Rtitle, Rdescription, author);
        }

        JSONArray messageArray = (JSONArray)Juser.get("messages");
        for(Object messageObject : messageArray){
            JSONObject Jmessage = (JSONObject)messageObject;
            String author = (String)Jmessage.get("author");
            String description = (String)Jmessage.get("description");
            user.addMessage(author, description);
        }

        JSONArray disabilityArray = (JSONArray) Juser.get("disabilities");
        for(int i=0; i < disabilityArray.size(); i++){
            user.addDisability(username, (String)disabilityArray.get(i));
        }

        user.updateCreditScore(username, creditScore);

        return user;
    }

    /**
     * Converts a JSONObject into a PropertyManager object
     * @param Jmanager The JSONObject being converted
     * @return The PropertyManager object created from the JSONObject
     */
    private static PropertyManager toManager(JSONObject Jmanager){
        String username = (String)Jmanager.get("username");
        String password = (String)Jmanager.get("password");
        String name = (String)Jmanager.get("name");
        String dateOfBirth = (String)Jmanager.get("date of birth");
        String homeAddress = (String)Jmanager.get("home address");
        String email = (String)Jmanager.get("e-mail");
        String phoneNumber = (String)Jmanager.get("phone number");
        PropertyManager manager = new PropertyManager(username, password, name, dateOfBirth,
                homeAddress, email, phoneNumber);

        JSONArray reviewArray = (JSONArray)Jmanager.get("reviews");
        for(Object reviewObject : reviewArray){
            JSONObject Jreview = (JSONObject)reviewObject;
            int rating = Math.toIntExact((long)Jreview.get("rating"));
            String Rtitle = (String)Jreview.get("title");
            String Rdescription = (String)Jreview.get("description");
            String author = (String)Jreview.get("author");
            manager.addReview(rating, Rtitle, Rdescription, author);
        }

        JSONArray messageArray = (JSONArray)Jmanager.get("messages");
        for(Object messageObject : messageArray){
            JSONObject Jmessage = (JSONObject)messageObject;
            String author = (String)Jmessage.get("author");
            String description = (String)Jmessage.get("description");
            manager.addMessage(author, description);
        }

        return manager;
    }

    /**
     * Converts a JSONObject into a Property object
     * @param Jproperty The JSONObject being converted
     * @return The Property object created from the JSONObject
     */
    private static Property toProperty(JSONObject Jproperty){
        String manager = (String)Jproperty.get("manager");
        String title = (String)Jproperty.get("title");
        String description = (String)Jproperty.get("description");
        String address = (String)Jproperty.get("address");
        int capacity = Math.toIntExact((long)Jproperty.get("capacity"));
        double baseRent = (double)Jproperty.get("base rent");
        Property property = new Property(manager, title, description, address, capacity, baseRent);

        JSONArray feeArray =(JSONArray) Jproperty.get("extra fees");
        for(int i=0; i < feeArray.size(); i++){
            String extraFee = (String)feeArray.get(i);
            String name = extraFee.split(":")[0];
            double fee = Double.parseDouble(extraFee.split("$")[1]);
            property.addFee(manager, name, fee);
        }

        JSONArray renterArray = (JSONArray)Jproperty.get("renters");
        for(int i=0; i < renterArray.size(); i++){
            String renter = (String)renterArray.get(i);
            property.addRenter(renter, manager);
        }

        JSONArray unitArray = (JSONArray)Jproperty.get("units");
        for(Object unitObject : unitArray){
            JSONObject Junit = (JSONObject) unitObject;
            String addressModifier = (String)Junit.get("address modifier");
            int Ucapacity = Math.toIntExact((long)Junit.get("capacity"));
            property.addUnit(addressModifier, Ucapacity, manager);

            for(String renter : (String[])Junit.get("renters")){
                property.addUnitRenter(renter, addressModifier, manager);
            }

            JSONArray reviewArray = (JSONArray)Junit.get("reviews");
            for(Object reviewObject : reviewArray){
                JSONObject Jreview = (JSONObject)reviewObject;
                int rating = Math.toIntExact((long)Jreview.get("rating"));
                String Rtitle = (String)Jreview.get("title");
                String Rdescription = (String)Jreview.get("description");
                String author = (String)Jreview.get("author");
                property.addUnitReview(rating, Rtitle, Rdescription, author, addressModifier);
            }
        }

        JSONArray reviewArray = (JSONArray)Jproperty.get("reviews");
        for(Object reviewObject : reviewArray){
            JSONObject Jreview = (JSONObject)reviewObject;
            int rating = Math.toIntExact((long)Jreview.get("rating"));
            String Rtitle = (String)Jreview.get("title");
            String Rdescription = (String)Jreview.get("description");
            String author = (String)Jreview.get("author");
            property.addReview(rating, Rtitle, Rdescription, author);
        }

        return property;
    }
}
