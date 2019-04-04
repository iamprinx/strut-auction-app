package actionbean;

import db.sql.implementer.UserOperationsImpl;
import models.User;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class RequestedUniqueUser extends UserOperationsImpl implements SessionAware {
    private Map<String, Object> session;
    private User retrieved_user;
    private int userid;
    
    public RequestedUniqueUser() {    }
    
    
    @Override
    public String execute() throws Exception {
        
        if( session.containsKey("requested_user")) session.remove("requested_user");
        if ( getUserid() == 0 ) return ERROR; 
        
        System.out.println(getUserid());
        System.out.println(getUserid());
        System.out.println(getUserid());
               
        retrieved_user = get(getUserid());
        if ( retrieved_user != null ){
            session.put("requested_user", retrieved_user);
        }
        return SUCCESS;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
}
