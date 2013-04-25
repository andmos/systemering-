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
 */
@SessionScoped
@Named("newUser")
public class NewUserBean implements Serializable {

    private String newName;
    private String newAddress;
    private String newUsername;
    private Users user = new Users();
    private boolean isCompany;

    public String getNewAddress() {
        return newAddress;
    }

    public boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

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
