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
    /*This variables is used to error management*/
    private boolean error;
    private boolean errorPanelGroup;
    private boolean adminLogin = false;
    private int passwordStatus;
            
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
    
    public boolean getAdminLogin(){
        return adminLogin;
    }

    public int getPasswordStatus() {
        return passwordStatus;
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
    /**
     * Changes the password for a user
     */
    public void changePassword(){
        passwordStatus = user.setPassword(newPassword, newPasswordConfirmed);
    }
    
    /**
     * Changes user information
     */
    public void changeUser(){
        user.setName(name);
        user.setAddress(address);
    }
    
    /*
     *Changes a variable if a user wants to log in as a admin.
     * This is used to change the content on the login site so its more reliable for a admin
     */
    
    public void loginAdmin(){
        adminLogin = true;
    }
    
    public void notLoginAdmin(){
        adminLogin = false;
    }
    
    
}
