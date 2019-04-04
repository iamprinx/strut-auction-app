package db.sql.implementer;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import connection.ConnectionFactory;
import models.User;
import db.SqlOperations;
import java.sql.Statement;
import java.util.Set;
import java.util.HashSet;

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
    
        
    /**
     * helps to retrieve a particular user instance from data source
     * 
     * @param Id --> representing the id of a particular object
     *  an array like ( structured in form of array ) optional argument.
     *  if this is passed, retrieval will be done based on this.
     *  
     * @retuns User
    */
    @Override
    public User get(Integer... Id) {
        
        Connection con = ConnectionFactory.getConnection();
        String query = (String) "select * from user where username=?";
        
        User retrieved_user = new User();
        
        try {
            PreparedStatement ps = null;
            
            if (Id.length >= 1){
                // if a unique key is passed to retrieve a particular object
                String query_for_arg = "select * from user where id=?";
                ps = con.prepareStatement(query_for_arg);
                for(Integer item : Id){
                    ps.setInt(1, item);
                }
            }
            
            else {
                // if no unique key is passed to retrieve a particular object
                ps = con.prepareStatement(query);
                ps.setString(1, getUsername());
            }
            
            ResultSet result = ps.executeQuery();
            
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
    
    /**
     * helps to insert data into the data source
     * @return user
     */
    @Override
    public User insertInto(){
        User user = new User();
        int rows_affected = 0;
        
        Connection con = ConnectionFactory.getConnection();
        String query = "insert into user ( username, firstname, lastname, email, passkey )";
        query += "values(?, ?, ?, ?, ?)";
        
        // inserting details to data source
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, getUsername());
            ps.setString(2, getFirstname());
            ps.setString(3, getLastname());
            ps.setString(4, getEmail());
            ps.setString(5, getPassword());
            rows_affected = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Unable to insert data to table : \n" + e);
        } finally {
            if ( con != null ){
                try {
                    con.close();
                } catch( Exception e){ System.out.print("Unable to close connection"); }
            }
        }
        
        // if user's details are inserted to data source, then set same details
        // to the user model object.
        if (rows_affected != 0){
            user.setUsername(getUsername());
            user.setFirstname(getFirstname());
            user.setLastname(getLastname());
            user.setEmail(getEmail());
            user.setPassword(getPassword());
            
            return user;
        }
        
        return null;
    }
    
    
    @Override
    public User updateData(User obj) {
        User user = new User();
        // this populate the user object with obj data
        user = obj; 
        
        Connection con = ConnectionFactory.getConnection();
        int id = obj.getId();   // get the id of the passed object data
        String query = "update user set firstname=?, lastname=?, email=? where id=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, getFirstname());
            ps.setString(2, getLastname());
            ps.setString(3, getEmail());
            ps.setInt(4, id);
            
            // update the user object with the appropriate data filled in form.
            user.setFirstname(getFirstname());
            user.setLastname(getLastname());
            user.setEmail(getEmail());
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update error:\n" + e);
        } finally {
            if ( con != null ){
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println("Error closing connection");
                }
            }
        }
        return user; 
    }
    
    
    @Override
    public Set<User> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "select * from user";
        Set<User> userSet = new HashSet();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()){
                User user = new User();
                
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setFirstname(result.getString("firstname"));
                user.setLastname(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                
                userSet.add(user);
            }        
        } catch (Exception e) {
            System.out.println("Error while retrieving all user data\n"+ e);
        }finally {
            if( con != null){
                try {
                    con.close();
                } catch(Exception e){
                    System.out.println("Error while closing connection \n"+ e);
                }
            }
        }
        
        return userSet;
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
