package Beans;

import ProblemDomain.Users;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named("user")
public class UserBean implements Serializable {

    private String name;
    private String username;
    private String password;
    private String address;
    private boolean error;
    private boolean errorPanelGroup;
    private Users user = new Users();

    public String getUsername() {
        this.username = user.getUsername();
        return this.username;
    }

    public void setUsername(String newName) {
        this.username = newName;
        user.setUsername(newName);
    }

    public String getPassword() {
        this.password = user.getPassword();
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
        user.setPassword(newPassword);
    }

    public String getAddress() {
        this.address = user.getAddress();
        return this.address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
        user.setAddress(newAddress);
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        this.name = name;
        user.setName(name);
    }

    public boolean getError() {
        return error;
    }

    public boolean getErrorPanelGroup() {
        return errorPanelGroup;
    }
    /*
     *Register a new user and set error = true/false dephendin on outcome,
     * Also setts the the errorPanelGroup = true so we can view errors
     */

    public void newUser() {
        errorPanelGroup = true;
        error = user.newUser();
    }

    /*
     *Log out method, returns navigation case
     */
    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession(false).invalidate();
        return "Logout";
    }
}
