package actionbean;

import java.util.Map;
import models.Product;
import db.sql.implementer.ProductOperationsImpl;
import org.apache.struts2.interceptor.SessionAware;
/**
 *
 * @author i-am-prinx
 */
public class BidAction extends ProductOperationsImpl implements SessionAware {
    private Product product;
    private Map<String, Object> session;
    public int productId;
    
    public String execute(){
        session.remove("bid_product");
        product = get(productId);
        session.put("bid_product", product);
        return SUCCESS;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
        
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
