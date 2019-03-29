package db.sql.implementer;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;

import connection.ConnectionFactory;
import models.User;
import db.SqlOperations;

/**
 * This class helps to implement the crud structure defined within the SqlOperations
 * and it is used to manipulate the data saved in the user table( that's why user is 
 * the generic type passed ). 
 * It also serves as the ActionSupport, so that the getter and setter method retrieved
 * from View can be accessed here to perform all the necessary manipulation. 
 * 
 * # # # # # # # # # # # 
 * SEPERATION OF CONCERN:
 * # # # # # # # # # # # 
 * 
 * this class is an abstract class that encapsulate the crud operations, it is not
 * meant to be used for form validations and routing( execute ), which was why it 
 * is created to be an abstract class. 
 * The execute and validate method should be implemented by subclasses of this class.
 * 
 * @author i-am-prinx
 */

public class UserOperationsImpl extends ActionSupport implements SqlOperations<User> {
    
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected String email;
    protected int id;
    
    
    /*                                                                    
        *                                                               *
                            SQL OPERATIONS BELOW
        *                                                               *
    */

    
    /* helps to retrieve a particular user instance from data source
     * @retuns User
    */
    @Override
    public User get() {
        
        Connection con = ConnectionFactory.getConnection();
        try {
            // retrieve a user from data source with username and return user
        } catch (Exception e) {
            
        } finally {
            
        }
        
        return null;
    }
    
    /*                                                                    
        *                                                               *
                            SETTERS AND GETTERS BELOW
        *                                                               *
    */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
