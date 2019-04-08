package actionbean;

import db.sql.implementer.ProductOperationsImpl;

import models.Product;
import models.User;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
        String imgPath = constructFilePath( sdf.format(uploaded_at));
        
        File file = new File(imgPath, getImageFileName());
        FileUtils.copyFile(getImage(), file);
        
        product = insertInto(user.getId(), file.toString(), sdf.format(uploaded_at));
        
        if (product != null) {
            return SUCCESS;
        }
        
        return ERROR;
    }
    
    
    /**
     * 
     * @param uploaded_at
     * @return 
     */
    private String constructFilePath(String uploaded_at){
        char sep = File.separatorChar;    // dynamic path separator for different OS           
        
        // using user unique details to make up path
        String uniquePath = sep  + "product-image" + sep + user.getId() + "" + sep;    
        
        // using product unique details to make up path
        uniquePath += user.getUsername() + sep + uploaded_at + sep;
        
        // retreiving the path where request is been served, this is absolute path from root dir.
        String contextPath = ServletActionContext.getServletContext().getRealPath("/");
        
        // going out from web folder ( it's not advice to keep things within the web folder)
        contextPath = contextPath.substring(0, contextPath.length() - 11);
        
        // merging unique path ( path constructed using unique identity ) with context path
        contextPath = contextPath + sep + uniquePath ;
        
        return contextPath;
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
