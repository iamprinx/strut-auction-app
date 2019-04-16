package actionbean;

import actionbean.beanutils.FrequentOperations;
import db.sql.implementer.UserOperationsImpl;
import models.User;

import java.util.Map;
import java.util.Set;
import models.Product;

import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class RequestedUniqueUser extends UserOperationsImpl implements SessionAware {
    private Map<String, Object> session;
    private User retrieved_user;
    private String username;
    
    public RequestedUniqueUser() {    }
    
    
    @Override
    public String execute() throws Exception {
        
        if( session.containsKey("requested_user")) session.remove("requested_user");
        if ( getUsername() == "" ) return ERROR; 
        
        System.out.println(getUsername());
        System.out.println(getUsername());
        System.out.println(getUsername());
               
        retrieved_user = get(getUsername());
        if ( retrieved_user != null ){
            session.put("requested_user", retrieved_user);
            Set<Product> authUserProduct = FrequentOperations.customDBqueryForProduct(retrieved_user);
            session.put("requested_user_products", authUserProduct);
        }
        
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
}
