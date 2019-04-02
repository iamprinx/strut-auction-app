package actionbean;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import db.sql.implementer.UserOperationsImpl;
import models.User;


/**
 *
 * @author i-am-prinx
 */
public class RegistrationAction extends UserOperationsImpl implements SessionAware{
    private Map<String, Object> sessionMap;
    
    private String confirm_password;
    private User user;
    
    public RegistrationAction() { }
    
    public String execute() throws Exception {
        user = insertInto();
        
        // if there's already an existing user in session management,
        // at the creation of a new user, the user in the previous 
        // session should be removed and the newly registered user should be
        // set in session.
        if (user != null && sessionMap.containsKey("User")){
           sessionMap.remove("User");
        }
        
        if (user != null){
            sessionMap.put("User", user);
            return SUCCESS;
        }
        return ERROR;
    }
    
    @Override
    public void validate() {
        if ( getUsername().length() == 0 ){
            addFieldError("username", "this field is required");
        }
        if ( getFirstname().length() == 0 ){
            addFieldError("firstname", "this field is required");
        }
        if ( getLastname().length() == 0 ){
            addFieldError("lastname", "this field is required");
        }
        if ( getEmail().length() == 0 ){
            addFieldError("email", "this field is required");
        }
        if ( getPassword().length() == 0 ){
            addFieldError("password", "this field is required");
        }
        if ( getConfirm_password().length() == 0 ){
            addFieldError("confirm_password", "this field is required");
        }
        // when password and confirm password entry doesn't match
        if ( !getPassword().equals(getConfirm_password())){
            addFieldError("confirm_password", "passwords do not match");
        }
    }
    
    @Override
    public void setSession(Map<String, Object> map){
        this.sessionMap = map;
    }
    
    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

}
