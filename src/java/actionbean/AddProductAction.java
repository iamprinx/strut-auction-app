package actionbean;

import db.sql.implementer.ProductOperationsImpl;
import models.Product;
import models.User;

import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class AddProductAction extends ProductOperationsImpl implements SessionAware {
    private String contentType;
    private String filename;
    
    private User user;
    private Product product;
    private Map<String, Object> session;
    
    
    public String execute( ) throws Exception {
        
        /* 
            Logic Note:       Logic Note:       Logic Note:         Logic Note:
            -------------------------------------------------------------------
         * when a product is uploaded, that product belongs to the user that's 
         * uploading it... Every uploaded product will be saved in a directory 
         * whose path comprises of the unique identity of it's owner, so when a 
         * a product is been uploaded, the retrieval of the user that is uploading
         * the image from the session is very necessary.
         */
        
        // if no user is saved in session, it means that session has been cleared.
        // if that is the case, all other operations will be truncated.
        if (session.containsKey("auth_user") && session.get("auth_user") != null){
            user = (User)session.get("auth_user");
        } else {
            System.out.println("ERROR::: No user found in session");
            return ERROR;
        }
        
        // creating a unique date time( in string format ) for each product upload 
        SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd HH:mm:ss");
        Date uploaded_at = new Date();
        
        // CONSTRUCTING PRODUCT IMAGE UPLOAD PATH AND SAVING IMAGE TO CONSTRUCTED PATH
        
        // dynamic path separator for different OS
        char sep = File.separatorChar;               
        
        // using user unique details to make up path
        String img_path = sep  + user.getId() + "" + sep + "" + user.getUsername();    
        
        // using product unique details to make up path
        img_path += sep + "product-image" + sep + sdf.format(uploaded_at) + sep + filename;
        
        
        System.out.println(img_path);
        
        
        // insert the product upload to the database.
        product = insertInto(user.getId(), img_path, sdf.format(uploaded_at));
        System.out.println(System.getProperty("user.dir"));
        
        if (product != null){
            File file = new File(img_path);
            boolean flag = file.createNewFile();
            
            if (flag != false){
                System.out.println("couldn't create directory");
                return SUCCESS;
            }
        }
        else {
            System.out.println("Product was not inserted.... Severe Error");
            return ERROR;
        }
        
        
        return SUCCESS;
    }
    
    

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
    public void setImageContentType(String contentType) {
       this.contentType = contentType;
    }

    public void setImageFileName(String filename) {
       this.filename = filename;
    }
    
}
