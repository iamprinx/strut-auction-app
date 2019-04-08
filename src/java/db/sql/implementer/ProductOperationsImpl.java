package db.sql.implementer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.opensymphony.xwork2.ActionSupport;
import db.SqlOperations;

import java.io.File;
import java.util.Set;

import connection.ConnectionFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

import models.Product;
import models.User;

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
    public Product get(Integer... Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "select * from product";
        Set<Product> productSet = new HashSet();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()){
                Product product = new Product();
                
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setPrice(result.getInt("price"));
                product.setImage(result.getString("image"));
                product.setUploadTime(result.getString("uploaded_at"));
                product.setOwner(result.getInt("owner"));
                
                productSet.add(product);
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
        return productSet;
    }

    @Override
    public Product insertInto() { throw new UnsupportedOperationException("Not supported yet."); }
    
    
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
        int rows_affected = 0;
                
        Product product = new Product();
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, getName());
            ps.setInt(2, getPrice());
            ps.setString(3, imgPath);
            ps.setString(4, uploadTime);
            ps.setInt(5, userId);
            
            rows_affected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insert product to db: \n" + e);
            return null;
        } finally {
            if (con != null){
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println("Error closing connection \n" + ex);
                }
            }
        }
        
        
        if (rows_affected != 0){
            product.setPrice(getPrice());
            product.setImage(imgPath);
            product.setUploadTime(uploadTime);
            product.setOwner(userId);
            product.setName(getName());
            
            return product;
        }
        
        return null;
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
