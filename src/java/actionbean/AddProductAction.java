package actionbean;

import db.sql.implementer.ProductOperationsImpl;
import java.io.File;
import models.Product;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class AddProductAction extends ProductOperationsImpl implements SessionAware {
    private File file;
    private String contentType;
    private String filename;
    
    private Product product;
    private Map<String, Object> session;
    
    
    public String execute( ) throws Exception {
        System.out.println(getImage());
        System.out.println(filename);
        System.out.println(getName());
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
