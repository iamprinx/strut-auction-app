package actionbean;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import db.sql.implementer.UserOperationsImpl;
import models.User;



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
        user = get();                 // retrieve user trying to login 
        
        User sessionUser = null;
        
        // check session if the requesting user has already been logged in.
        if ( sessionMap.containsKey("user")){
            sessionUser = (User) sessionMap.get("user");
        }
        
        // if the user is present in the session, just log in the user
        if ( sessionUser != null && sessionUser.getUsername().equals(user.getUsername())){
            return SUCCESS;
        }
        
        // if the inputted login password is equal to the password retreived from
        // data source. This will only happen if no user is found in session
        if ( user != null && user.getPassword().equals(getPassword())){
            sessionMap.put("user", user);     // include this new user in session
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
