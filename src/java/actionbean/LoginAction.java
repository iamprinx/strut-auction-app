package actionbean;

import com.opensymphony.xwork2.ActionContext;
import db.sql.implementer.UserOperationsImpl;

import models.User;


/**
 * Helps to authenticate and validate a user at login. It also routes a user to
 * a defined page based on the end result of authentication.
 * @author i-am-prinx
 */
public class LoginAction extends UserOperationsImpl {
    /*                                                                    
        *                                                                    *
                Retrieve a user instance and validate the passkey 
                   (of retrieved user) with the passed password
        *                                                                    *
    */
    private User user;
    
    public LoginAction() { }
    
    @Override
    public String execute() throws Exception {
        user = get();                 // retrieve user trying to login 
        
        // if the inputted login password is equal to the password retreived from
        // data source.
        if (user.getPassword().equals(getPassword())){
            return SUCCESS;
        }
        return ERROR;
    }
    
    @Override
    public void validate() {
       if ( getUsername().length() == 0){
           addFieldError("username", "this field is required");
       }
       if ( getPassword().length() == 0){
           addFieldError("password", "this field required");
       }
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
