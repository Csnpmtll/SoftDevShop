package Cart;

import static Connect.ConnectDB.connectDB;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javax.swing.table.DefaultTableModel;

public class CartDao {
    public DefaultTableModel getCart(){
        try{
            DB database = connectDB();
            DBCollection collection = database.getCollection("cart");
            DBCursor cursor = collection.find();
            String[] columnname={"productname","size","quantity","price"};
            DefaultTableModel model = new DefaultTableModel(columnname,0);
            while(cursor.hasNext()){
                DBObject obj = cursor.next();
                String productname=(String) obj.get("productname").toString();
                String size=(String) obj.get("size").toString();
                String quantity=(String) obj.get("quantity").toString();
                String price=(String) obj.get("price").toString();
                
                model.addRow(new Object[] {productname,size,quantity,price});
            }
            return model;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
