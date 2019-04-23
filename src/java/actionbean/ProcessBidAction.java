package actionbean;

import java.sql.Connection;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import db.sql.implementer.BidOperationsImpl;
import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Product;
import models.User;

/**
 *
 * @author i-am-prinx
 */
public class ProcessBidAction extends BidOperationsImpl implements SessionAware  {
    private int amount;
    private Map<String, Object> session;
    private Product product;
    private User user;
    
    public String execute(){
        user = (User) session.get("auth_user");
        product = (Product) session.get("bid_product");
        String query = "insert into bids (amount, product, user) values ( ?, ?, ? )";
        
        if (user != null && product != null ){
            
            Connection con = ConnectionFactory.getConnection();
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, getAmount());
                ps.setInt(2, product.getId());
                ps.setInt(3, user.getId());
                
                ps.execute();
                if (ps.getUpdateCount() >= 1) {
                    return SUCCESS;
                }
            } catch(SQLException ex){
                System.out.println("Error while inserting :\n\n" + ex);
            }
        }
        
        return ERROR;
    }
    
    @Override
    public void validate(){
        if ( amount < 0){
            addFieldError("amount", "Negative value not allowed");
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
