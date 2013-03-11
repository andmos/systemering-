
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ProblemDomain.Users;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import HelpClasses.Feedback;
import javax.annotation.PostConstruct;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {

    public String username;
    public String password;

    //Saving the username in a session
    public void setSessionUsername(String current) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("Username", current);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Feedback checkLogin() {

        return Feedback.UserLoginOK;
    }

    public Feedback logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return Feedback.UserLogoutOK;
    }
}
