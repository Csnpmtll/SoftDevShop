package Cart;

import javax.swing.table.DefaultTableModel;
import Cart.CartDao;

public class CartService {
    CartDao dao = new CartDao();
    public DefaultTableModel showCartData(){
        return dao.getCart();
    }
}
