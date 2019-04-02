package actionbean;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author i-am-prinx
 */
public class LogoutAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;
    
    public LogoutAction() { }
    
    // check if a user exists in session, remove the user if there is.
    public String execute() throws Exception {
        if ( session.containsKey("User")){
            session.remove("User");
        }
        return SUCCESS;
    }
    
    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
