/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import static Connect.ConnectDB.connectDB;
import Index.FormIndex;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Boss
 */
public class UserDao {
    DB database = connectDB();
    User user;
    public User getUser(String id,String pwd) {
        try {
            DBCollection collection = database.getCollection("user");
            BasicDBObject query = new BasicDBObject();
            query.put("username", id);
            query.put("password", pwd);
            DBCursor cursor = collection.find(query);
            String username = (String) cursor.one().get("username");
            String password = (String) cursor.one().get("password");
            String name = (String) cursor.one().get("name");
            user = new User(username, password, name);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }public boolean checkLogin(String username,String pwd) {
        boolean flag = false;
        try {
            UserDao dao = new UserDao();
            DBCollection collection = database.getCollection("user");
            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            query.put("password", pwd);
            int num = collection.find(query).count();
            System.out.println(num);
            if (num > 0) {
                flag = true;
                user = dao.getUser(username,pwd);
                dao.updateLogin(user);
                FormIndex form = new FormIndex();
                form.show();
            } else {
                JOptionPane.showMessageDialog(null, "ไม่มีUsernameนี้อยู่ในระบบหรือPasswordไม่ถูกต้อง");
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    public void updateLogin(User user) {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://admin:admin1234@ds145923.mlab.com:45923/sneaker");
            MongoClient mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("sneaker");
            MongoCollection<Document> collection = database.getCollection("user");
            Bson filter = new Document("username", user.getUsername());
            Bson newValue = new Document("status", "online");
            Bson updateOperationDocument = new Document("$set", newValue);
            collection.updateOne(filter, updateOperationDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
