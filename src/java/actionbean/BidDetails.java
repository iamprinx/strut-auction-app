package actionbean;


import db.sql.implementer.BidOperationsImpl;
import db.sql.implementer.ProductOperationsImpl;
import models.UserProductBid;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import models.Product;


/**
 *
 * @author i-am-prinx
 */
public class BidDetails extends BidOperationsImpl implements ServletRequestAware {
    private HttpServletRequest request;
    private Set<UserProductBid> user_product_bid;
    public Integer productId;
    
    private Product biddedProduct;
    private ProductOperationsImpl productSqlOperators = new ProductOperationsImpl();
    
    
    public String execute(){   
        biddedProduct = productSqlOperators.get(productId);
        user_product_bid = getAll(productId);
        
        getServletRequest().setAttribute("productbids", user_product_bid);
        getServletRequest().setAttribute("biddedProduct", biddedProduct);
        
        return SUCCESS;
    }    
    
    public HttpServletRequest getServletRequest(){
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
