package db.sql.implementer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.opensymphony.xwork2.ActionSupport;
import db.SqlOperations;

import java.io.File;
import java.util.Set;

import models.Product;
import connection.ConnectionFactory;

/**
 * This helps to implement CRUD operations for product objects...
 * @author i-am-prinx
 */
public class ProductOperationsImpl extends ActionSupport implements SqlOperations<Product> {
    private int id;         // product unique identity
    private String name;    // product name
    private int price;      // product price
    private File image;     // product image
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
    public Product get(Integer... Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product insertInto() { 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * helps to insert a product into the database
     * @param userId
     *      the user that is making the insertion of the product
     * @param imgPath
     *      the directory path signifying to the location in which the file is 
     *      saved in the host machine
     * @param uploadTime
     *      the date time in which the product is been sent for upload.
     * 
     * @return a temporary product model object
     */
    public Product insertInto(int userId, String imgPath, String uploadTime){
        Connection con = ConnectionFactory.getConnection();
        String query  = "insert into product(name, price, image, uploaded_at, owner) values(?, ?, ?, ?, ?)";
        
                
        Product product = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, getName());
            ps.setInt(2, getPrice());
            ps.setString(3, imgPath);
            ps.setString(4, uploadTime);
            ps.setInt(5, userId);
            
            int rows_affected = ps.executeUpdate();
            
            if (rows_affected >= 1){
                product.setName(getName());
                product.setPrice(getPrice());
                product.setImage(imgPath);
                product.setUploadTime(uploadTime);
                product.setOwner(userId);
            }
            
            return product;
        } catch (SQLException e) {
            System.out.println("Error insert product to db: \n" + e);
        } finally {
            if (con != null){
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println("Error closing connection \n" + ex);
                }
            }
        }
      
        return product;
    }

    @Override
    public Product updateData(Product obj) {
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
