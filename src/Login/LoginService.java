/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import static Connect.ConnectDB.connectDB;
import Index.FormIndex;
import User.UserDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import javax.swing.JOptionPane;

/**
 *
 * @author Boss
 */
public class LoginService {
    UserDao userdao=new UserDao();
    public boolean login(String username,String password){
        if(userdao.checkLogin(username, password)==true){
            userdao.getUser(username, password);
            return true;
        }return false;
    }
}
