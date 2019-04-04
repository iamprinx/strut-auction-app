package actionbean;

import models.User;
import db.sql.implementer.UserOperationsImpl;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class UserList extends UserOperationsImpl implements SessionAware {
    private Map<String, Object> session;
    private Set<User> userSet = new HashSet();
    
    public UserList(){  }
    
    @Override
    public String execute() throws Exception {
        if (session.containsKey("userlist")){
            session.remove("userlist");
        }
        userSet = getAll();
        session.put("userlist", userSet);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
