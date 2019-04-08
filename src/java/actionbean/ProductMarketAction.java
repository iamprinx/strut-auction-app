package actionbean;

import db.sql.implementer.ProductOperationsImpl;
import models.Product;

import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class ProductMarketAction extends ProductOperationsImpl implements SessionAware {
    
    private Map<String, Object> session;
    private Set<Product> productSet;
    
    @Override
    public String execute() throws Exception {
        if (session.containsKey("productlist")){
            session.remove("productlist");
        }
        
        productSet = getAll();      // retrieve all product
        System.out.println("the number of objects in productSet are: " + productSet.toArray().length );
        session.put("productlist", productSet);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
}
