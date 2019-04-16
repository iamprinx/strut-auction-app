package actionbean;

import db.sql.implementer.ProductOperationsImpl;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

import models.Product;

import java.util.Set;


/**
 *
 * @author i-am-prinx
 */
public class ProductMarketAction extends ProductOperationsImpl implements ServletRequestAware {
    
    private Set<Product> productSet;
    private HttpServletRequest request;
    
    @Override
    public String execute() throws Exception {        
        productSet = getAll();      // retrieve all product
        getServletRequest().setAttribute("products", productSet);
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getServletRequest( ) {
        return request;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }
    
}
