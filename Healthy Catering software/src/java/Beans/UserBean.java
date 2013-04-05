package Beans;

import ProblemDomain.Users;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestScoped
@Named("user")
public class UserBean implements Serializable {

    private String name;
    private String username;
    private String password;
    private String newPassword;
    private String newPasswordConfirmed;
    private String address;
    /*This variables is used to error management, 5 = Undifiend value*/
    private int error = 5;
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
        return user.getUsername();
    }
    /**
     * 
     * @param newName gets new name from the site and sends it to Users - class
     */
    public void setUsername(String newName) {
        user.setUsername(newName);
        this.username = newName;
    }
    /**
     * 
     * @return returns password from Users - class 
     */
    public String getPassword() {
        this.password = user.getPassword();
        return user.getPassword();
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
        return user.getAddress();
    }
    /** 
     * 
     * @param newAddress gets new address from the site and sends it to Users - class 
     */
    public void setAddress(String newAddress) {
        user.setAddress(newAddress);
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
        user.setName(name);
        this.name = name;
    }
    /**
     * 
     * @return returns error message to the site if registration goes bad. 
     */
    public int getError() {
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
     * Register a new user and set error = true/false dephending on outcome,
     * Also sets the the errorPanelGroup = true so we can view errors.
     */
    public void newUser() {
        errorPanelGroup = true;
        System.out.println("passord: "+password+" ,confirm password: "+newPasswordConfirmed);
        user.setPassword(password, newPasswordConfirmed);
        error = user.newUser();
    }

    /**
     *Log out method, returns navigation case for the JSF - site.
     * We want to log you out when you first enter the site, this was mainly because of some navigation errors,
     * if you left the site logged in.
     
     */
    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        try {
        request.logout();
        }catch(ServletException e){
            System.out.println("feil i doLogout"+e.getMessage());
        }
        return "Logout";
    }
    
    /**
     * Changes the password for a user
     */
    public void changePassword(){
        error = passwordStatus = user.setnewPassword(newPassword, newPasswordConfirmed);
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
