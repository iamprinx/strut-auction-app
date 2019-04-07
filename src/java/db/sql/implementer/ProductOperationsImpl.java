package db.sql.implementer;

import com.opensymphony.xwork2.ActionSupport;
import db.SqlOperations;
import java.io.File;
import java.util.Set;
import models.User;

/**
 * This helps to implement CRUD operations for product objects...
 * @author i-am-prinx
 */
public class ProductOperationsImpl extends ActionSupport implements SqlOperations<User> {
    private int id;         // product unique identity
    private String name;    // product name
    private int price;      // product price
    private File image;
    private int owner;      // product owner: user uploading product should be the owner.
    
    
    @Override
    public void validate( ){
        if ( name.length() == 0){
            addFieldError("name", "this field is required");
        }
        if ( (Integer)price <= 0 ){
            addFieldError("price", "negative value not allowed");
        }
    }
    
    
    @Override
    public User get(Integer... Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User insertInto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User updateData(User obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*                                                                    
        *                                                                   *
                            SETTERS AND GETTERS BELOW
        *                                                                   *
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
    
    
    
    
    
}
