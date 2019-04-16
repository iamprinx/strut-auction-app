package actionbean;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import java.util.Set;

import actionbean.beanutils.FrequentOperations;
import db.sql.implementer.UserOperationsImpl;
import models.Product;
import models.User;

/**
 *
 * @author i-am-prinx
 */
public class UserProfileUpdate extends UserOperationsImpl implements SessionAware {
    private Map<String, Object> session;
    private User user;
    
    public UserProfileUpdate() { }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
    @Override
    public String execute() throws Exception{
        user = (User) session.get("auth_user");
        session.remove("auth_user");
        if ( user != null ){
            User updated_user = updateData(user);
            if (updated_user != null){                
                session.put("auth_user", updated_user);
                
                Set<Product> authUserProduct = FrequentOperations.customDBqueryForProduct(user);
                session.put("auth_user_products", authUserProduct);
                
                return SUCCESS;
            }
        }
        return ERROR;
    }
    
}
