package Beans;

import ProblemDomain.Users;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author
 * havardb
 * Bean to handle requests for a new user from salesmen view.
 */
@SessionScoped
@Named("newUser")
public class NewUserBean implements Serializable {

    private String newName;
    private String newAddress;
    private String newUsername;
    private Users user = new Users();
    private boolean isCompany;

    /**
     * 
     * @return Address from form in view.
     */
    public String getNewAddress() {
        return newAddress;
    }

    /**
     * 
     * @return If a user is company from form the view.
     */
    public boolean getIsCompany() {
        return isCompany;
    }
/**
 * 
 * @param isCompany Sets if a user is company from the form in the view
 */
    public void setIsCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }
/**
 * 
 * @return Name from form in view.
 */
    public String getNewName() {
        return newName;
    }
/**
 * 
 * @return Username from form in the view.
 */
    public String getNewUsername() {
        return newUsername;
    }
/**
 * 
 * @param newAddress Sets a new address from form in the view
 */
    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
/**
 * 
 * @param newName Sets a new name from the form in the view.
 */
    public void setNewName(String newName) {
        this.newName = newName;
    }
/**
 * 
 * @param newUsername Sets a new username from the form in the view.
 */
    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
/**
 * Method to create a new user.
 * Resets all variables so the view is corrected.
 */
    public void newUser() {
        String role;
        if (isCompany) {
            role = "userCompany";
        } else {
            role = "userNormal";
        }
        user.newUser(newName, newName, newAddress, role);
        setIsCompany(false);
        setNewAddress(null);
        setNewName(null);
        setNewUsername(null);
    }
}
