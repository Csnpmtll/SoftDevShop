package Connect;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Boss
 */
public class ConnectDB {
    public static MongoClientURI uri;
    public static MongoClient mongoClient;
    public static DB database;
    
    public static DB connectDB(){
        try{
            uri = new MongoClientURI("mongodb://admin:admin1234@ds145923.mlab.com:45923/sneaker");
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDB("sneaker");
            System.out.println("Connected "+database);
            return database;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
