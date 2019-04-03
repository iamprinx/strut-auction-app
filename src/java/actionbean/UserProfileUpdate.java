package actionbean;

import db.sql.implementer.UserOperationsImpl;
import java.util.Map;
import models.User;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class UserProfileUpdate extends UserOperationsImpl implements SessionAware {
    private Map<String, Object> session;
    private User user;
    
    public UserProfileUpdate() { }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
    @Override
    public String execute() throws Exception{
        user = (User) session.get("user");
        session.remove("user");
        if ( user != null ){
            User updated_user = updateData(user);
            if (updated_user != null){                
                session.put("user", updated_user);
                return SUCCESS;
            }
        }
        return ERROR;
    }
    
}
