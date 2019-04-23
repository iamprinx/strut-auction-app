package actionbean;

import actionbean.beanutils.FrequentOperations;
import db.sql.implementer.ProductOperationsImpl;

import models.Product;
import models.User;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class AddProductAction extends ProductOperationsImpl implements SessionAware {
    private File image;
    private String imageContentType;
    private String imageFileName;
    
    private Map<String, Object> session;
    
    private User user;
    private Product product;
    
    
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
        SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd");
        Date uploaded_at = new Date();
        String imageName = constructImageName(sdf.format(uploaded_at));
        
        // directory where image will be saved in the file system
        // context_path is the path where our servlet is currently running in our drive
        String contextPath = ServletActionContext.getServletContext().getRealPath("/");
        contextPath = contextPath.substring(0, contextPath.length() - 11);
        contextPath += "/product-image";
        
        File file = new File(contextPath, imageName);
        FileUtils.copyFile(getImage(), file);
        
        // we'll simply save the image path as the unique name constructed for
        // the uploaded image.
        product = insertInto(user.getId(), imageName, sdf.format(uploaded_at));
        
        if (product != null) {
            Set<Product> authUserProduct = FrequentOperations.getUserProduct(user);
            session.put("auth_user_products", authUserProduct);
            return SUCCESS;
        }
        
        return ERROR;
    }
    
    
    /**
     * 
     * @param uploaded_at
     * @return 
     */
    private String constructImageName(String uploaded_at){
        // constructing file name to a unique name
        String file_name = user.getId() + "_" + user.getUsername() + "_" + uploaded_at;
        file_name += "_" + getImageFileName();
        
        System.out.println("New image file name is " +  file_name);                
        
        return file_name;
    }
    
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
    
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

}
