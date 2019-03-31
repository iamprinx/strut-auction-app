package db.sql.implementer;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

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
    
    protected User retrieved_user;
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

    
    /**
     * helps to retrieve a particular user instance from data source
     * @retuns User
    */
    @Override
    public User get() {
        
        Connection con = ConnectionFactory.getConnection();
        String query = "select * from user where username=?";
        
        retrieved_user = new User();;
        
        try {
            PreparedStatement prepared_stmnt = con.prepareStatement(query);
            prepared_stmnt.setString(1, getUsername());
            ResultSet result = prepared_stmnt.executeQuery();
            
            while (result.next()){
               retrieved_user.setUsername(result.getString("username"));
               retrieved_user.setFirstname(result.getString("firstname"));
               retrieved_user.setLastname(result.getString("lastname"));
               retrieved_user.setEmail(result.getString("email"));
               retrieved_user.setPassword(result.getString("passkey"));
               retrieved_user.setId(result.getInt("id"));
               
               return retrieved_user;
            }
        } catch (Exception e) {
            System.out.println("Single Retrieval Error: \n\n" + e);
        } finally {
            if (con != null ){
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println("Error encountered while closing connection ( SR ):\n\n" + e);
                }
            }
        }
        
        return null;
    }
    
    /*                                                                    
        *                                                                   *
                            SETTERS AND GETTERS BELOW
          it should be noted that these setter and getter method can only 
          be used to retrieve the values filled in form. calling any of these
          method below wouldn't give us the value of the user that was 
          retrieved.
    
          example:
            getUsername()            !=         retrieved_user.getUsername()
            form value is returned             a user's value is returned
    
          so this is to clarify that non of these methods below can be used
          to access the properties of a user retrieved from the data source.
        *                                                                   *
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
