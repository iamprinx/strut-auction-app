/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionbean.beanutils;

import connection.ConnectionFactory;
import db.sql.implementer.ProductOperationsImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import models.Product;
import models.User;

/**
 *
 * @author i-am-prinx
 */
public class FrequentOperations {
    
    /**
     * this will help to query the database and retrieve all products belonging
     * to the authenticated user and also help to save retrieved product to
     * session.
     * @param user 
     */
    public static Set<Product> getUserProduct(User user){
        Set<Product> authUserProductList = null;
        
        Connection con = ConnectionFactory.getConnection();
        try {
            String query = "select * from product where owner=?";
            
            PreparedStatement ps = con.prepareStatement(query);            
            ps.setInt(1, user.getId());

            authUserProductList = ProductOperationsImpl.get(ps);
            
            return authUserProductList;
        } catch (SQLException e) {
            System.out.println("Error while connecting and creating prepared statement: \n" + e);
        }
        finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error while closing database:\n" + e);
                }
            }
        }
        
        return null;
    }
}
