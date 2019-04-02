package models;

/**
 * This is a User data structure in the data source. This will be used to retain
 * fetched users temporarily while application is still up.
 * @author i-am-prinx
 */
public class User {
    
    // data source fields( columns ) representations
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int id;
    
    public User(){ }       // default constructor

    
    
    /*                                                                    
        *                                                               *
                            SETTERS AND GETTERS BELOW
        *                                                               *
    */
    
    public String getFullname() {
        return getFirstname() + " " + getLastname();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
