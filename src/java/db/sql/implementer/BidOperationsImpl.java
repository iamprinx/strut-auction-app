package db.sql.implementer;

import com.opensymphony.xwork2.ActionSupport;
import connection.ConnectionFactory;

import db.SqlOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import models.Bid;
import models.UserProductBid;


/**
 *
 * @author i-am-prinx
 */
public class BidOperationsImpl extends ActionSupport implements SqlOperations<Bid, Integer>{
    
    /**
     * this generic method helps to insert data into bid table.
     * 
     * @param query
     *      sql string that will be used for inserting data into the specified database table.
     * @return 
     *      true -- if insertion succeeds
     *      false -- if insertion fails
     */
    public Boolean insertInto(String query) {
        Connection con = ConnectionFactory.getConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return true;
        }
        catch(SQLException ex){
            System.out.println("Error while inserting data into bid table:\n" + ex);
        }
        return false;
    }
    
    /**
     * helps to retrieve all bid history for a particular product.
     * The retrieved data will contain the user ( bidder's --> first_name last_name email ),
     * product( product name and image ) and the bid amount.
     * @param product_id
     *      product id which bid history is to be retrieved.
     * @return
     */
    public Set<UserProductBid> getAll(Integer product_id) {
        Connection con = ConnectionFactory.getConnection();
        Set<UserProductBid> bids_users_product = new HashSet();
               
        String query = "select b.amount, u.firstname, u.lastname, u.email, p.name, p.image";
        query += " from product p join users u on p.owner = u.id join bids b on u.id = b.user where p.id =?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, product_id);
            ResultSet result = ps.executeQuery();
            
            while(result.next()){
                UserProductBid productsbid = new UserProductBid();
                
                productsbid.setBidAmount(result.getInt("amount"));
                productsbid.setEmail(result.getString("email"));
                productsbid.setFirstname(result.getString("firstname"));
                productsbid.setLastname(result.getString("lastname"));
                productsbid.setProductName(result.getString("name"));
                productsbid.setProductImage(result.getString("image"));
                
                System.out.println(result.getString("image"));
                System.out.println(result.getString("image"));
                System.out.println(result.getString("image"));
                
                bids_users_product.add(productsbid);
            }
            
            return bids_users_product;
        }
        catch(SQLException ex){
            System.out.println("Error occured while trying to retrieve all bid for product:\n" + ex);
        }
            
        return null;
    }

    @Override
    public Bid insertInto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bid updateData(Bid obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Bid get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Bid> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
