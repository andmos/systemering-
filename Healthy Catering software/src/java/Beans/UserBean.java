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
    private String newPassword;
    private String newPasswordConfirmed;
    private String address;
    private boolean error;
    private boolean errorPanelGroup;
    private Users user = new Users();
    /**
     * 
     * @return username from the Users- class.
     */
    public String getUsername() {
        this.username = user.getUsername();
        return this.username;
    }
    /**
     * 
     * @param newName gets new name from the site and sends it to Users - class
     */
    public void setUsername(String newName) {
        this.username = newName;
        user.setUsername(newName);
    }
    /**
     * 
     * @return returns password from Users - class 
     */
    public String getPassword() {
        this.password = user.getPassword();
        return this.password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPasswordConfirmed() {
        return newPasswordConfirmed;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setNewPasswordConfirmed(String newPasswordConfirmed) {
        this.newPasswordConfirmed = newPasswordConfirmed;
    }
    
    
    /**
     * 
     * @param newPassword gets new password from the site and sends it to Users - class 
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
        user.setPassword(newPassword);
    }
    
    /**
     * 
     * @return gets address from the Users - class 
     */ 
    public String getAddress() {
        this.address = user.getAddress();
        return this.address;
    }
    /** 
     * 
     * @param newAddress gets new address from the site and sends it to Users - class 
     */
    public void setAddress(String newAddress) {
        this.address = newAddress;
        user.setAddress(newAddress);
    }
    /**
     * 
     * @return returns name from the Users - class 
     */                    
    public String getName() {
        return user.getName();
    }
    /**
     * 
     * @param name gets new name from the site and sends it to Users - class 
     */
    public void setName(String name) {
        this.name = name;
        user.setName(name);
    }
    /**
     * 
     * @return returns error message to the site if registration goes bad. 
     */
    public boolean getError() {
        return error;
    }
    
    
    public boolean getErrorPanelGroup() {
        return errorPanelGroup;
    }
    
    /** 
     * Register a new user and set error = true/false dephendin on outcome,
     * Also setts the the errorPanelGroup = true so we can view errors.
     */
    public void newUser() {
        errorPanelGroup = true;
        error = user.newUser();
    }

    /**
     *Log out method, returns navigation case for the JSF - site.
     */
    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession(false).invalidate();
        return "Logout";
    }
    
    public void changePassword(){
        user.setPassword(newPassword, newPasswordConfirmed);
    }
}
