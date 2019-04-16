package actionbean;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import models.User;
import java.util.Set;

import db.sql.implementer.UserOperationsImpl;
import actionbean.beanutils.FrequentOperations;
import models.Product;



/**
 * Helps to authenticate and validate a user at login. It also routes a user to
 * a defined page based on the end result of authentication.
 * @author i-am-prinx
 */
public class LoginAction extends UserOperationsImpl implements SessionAware {
    private User user;
    private Map<String, Object> sessionMap;
    
    public LoginAction() { }
    
    /*                                                                    
        *                                                                    *
                Retrieve a user instance and validate the passkey 
                   (of retrieved user) with the passed password
        *                                                                    *
    */
    @Override
    public String execute() throws Exception {
        // retrieve user trying to login with passed in username 
        // ( getUsername() from defined super class )
        user = get(getUsername()); 
        
        User sessionUser = null;
        
        // check session if the requesting user has already been logged in.
        if ( sessionMap.containsKey("auth_user")){
            sessionUser = (User) sessionMap.get("auth_user");
        }
        
        // retrieves all the product of the currently logged in user and save it
        // in session
        Set<Product> authUserProduct = FrequentOperations.customDBqueryForProduct(user);
        sessionMap.put("auth_user_products", authUserProduct);
        
        // if the user is present in the session, just log in the user
        if ( sessionUser != null && sessionUser.getUsername().equals(user.getUsername())){
            return SUCCESS;
        }       
        
        // if the inputted login password is equal to the password retreived from
        // data source. This will only happen if no user is found in session
        if ( user != null && user.getPassword().equals(getPassword())){
            sessionMap.put("auth_user", user);     // include this new user in session
            return SUCCESS;
        }
        
        return ERROR;
    }
    
    @Override
    public void validate() {
       if ( user == null && getUsername().length() == 0){
           addFieldError("username", "this field is required");
       }
       if ( user == null && getPassword().length() == 0){
           addFieldError("password", "this field required");
       }
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }
    
    
    /*
        *                                                               *
          Getter methods below helps to access the properties of a user
          that was retrieved from the database.
        *                                                               *
    */  
    public String getFirstname()    {  return user.getFirstname(); }
    public String getLastname()     {  return user.getLastname();  }
    public String getEmail()        {  return user.getEmail();     }
    public String getFullname() { return user.getFirstname() + " " + user.getLastname(); }
}
