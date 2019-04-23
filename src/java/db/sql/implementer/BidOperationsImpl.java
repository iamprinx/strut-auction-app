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
     * helps to retrieve all bid history for a particular product
     * @param product_id
     *      product id which bid history is to be retrieved.
     * @return
     */
    public Set<Bid> getAll(Integer product_id) {
        Connection con = ConnectionFactory.getConnection();
        Set<Bid> bid_set = new HashSet<Bid>();
        String query = "select * from bids where product=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, product_id);
            ResultSet result = ps.executeQuery();
            
            while(result.next()){
                Bid product_bid = new Bid();
                product_bid.setAmount(result.getInt("amount"));
                product_bid.setBidder(result.getInt("user"));
                product_bid.setId(result.getInt("id"));
                product_bid.setProduct(result.getInt("product"));
                
                bid_set.add(product_bid);
            }
            
            return bid_set;
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
