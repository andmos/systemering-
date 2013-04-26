package Beans;


import Lists.Orders_List;
import ProblemDomain.Users;
import Lists.Users_List;
import ProblemDomain.Orders;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import HelpClasses.Mailing;

@RequestScoped
@Named("user")
/**
 * User bean handles request from a user.
 */
public class UserBean implements Serializable {

    private String usernameChange;
    private String name;
    private String username;
    private String password;
    private String newPassword;
    private String newPasswordConfirmed;
    private String address;
    private boolean editable;
    /*This variables is used to error management*/
    private int error = 5;
    private boolean errorPanelGroup;
    private boolean resetPassword;
    private boolean changedInformation;
    private boolean adminLogin = false;
    private boolean getOrdersRegUsers;
    private int passwordStatus;
    private Users user = new Users();
    private Users_List userlist = new Users_List();
    private Orders_List orderlist = new Orders_List();
    private Orders order = new Orders();
    private boolean isCompany;
    //New user from management view variables
    private String newName;
    private String newUsername;
    private String newAddress;
    private String newRole;
    private String text;
    private String email;
    
/**
 * Metho to reset the variables to be able to use them later.
 */
    public void reset() {
        setAddress(null);
        setName(null);
        setUsername(null);
        setNewPassword(null);
        setNewEmail(null);
    }
    
    /**
     * 
     * @return Error variable.
     */
    public boolean getOrdersRegUsers() {
        return getOrdersRegUsers;
    }

    /**
     * 
     * @return Return the username from the database.
     */
    public String getUsername() {
        this.username = user.getUsername();
        return user.getUsername();
    }

    /**
     * 
     * @param newName Sets the username from the view.
     */
    public void setUsername(String newName) {
        user.setUsername(newName);
        this.username = newName;
    }

    /**
     * 
     * @return the password from the database.
     */
    public String getPassword() {
        this.password = user.getPassword();
        return user.getPassword();
    }

    /**
     * 
     * @return If a user is in a company or not.
     */
    public boolean getIsCompany() {
        return isCompany;
    }
    /**
     * 
     * @param isCompany Sets if the user is in a company or not.
     */
    public void setIsCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }
    /**
     * 
     * @return The new password you set in the view.
     */
    public String getNewPassword() {
        return newPassword;
    }
    /**
     * 
     * @return The new password confirm you set in the view.
     */
    public String getNewPasswordConfirmed() {
        return newPasswordConfirmed;
    }
    /**
     * 
     * @param newPassword Sets the new password from the view.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    /**
     * 
     * @param newPasswordConfirmed Sets the new password confirm from the view.
     */
    public void setNewPasswordConfirmed(String newPasswordConfirmed) {
        this.newPasswordConfirmed = newPasswordConfirmed;
    }

    
     /**
      * 
      * @param newPassword Sets the new password from the view.
      */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * 
     * @return The address from the database.
     */
    public String getAddress() {
        this.address = user.getAddress();
        return user.getAddress();
    }

    /**
     * 
     * @param newAddress Sets the new address from the view.
     */
    public void setAddress(String newAddress) {
        user.setAddress(newAddress);
        this.address = newAddress;
    }
    /**
     * 
     * @return email
     */

    public String getNewEmail() {
        return email;
    }
    /**
     * sets the email
     * @param email 
     * 
     */

    public void setNewEmail(String email) {
        this.email = email;
    }
    
    
    

    /**
     * 
     * @return The name from the database.
     */
    public String getName() {
        return user.getName();
    }

    /**
     * 
     * @param name Sets the name from the view.
     */
    public void setName(String name) {
        user.setName(name);
        this.name = name;
    }

    /**
     * 
     * @return Error variable.
     */
    public int getError() {
        return error;
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getErrorPanelGroup() {
        return errorPanelGroup;
    }
/**
 * 
 * @return Error variable.
 */
    public boolean getAdminLogin() {
        return adminLogin;
    }
    
    /**
     * 
     * @return The status of the password changing.
     */
    public int getPasswordStatus() {
        return passwordStatus;
    }
    /**
     * 
     * @return The new address from the view.
     */
    public String getNewAddress() {
        return newAddress;
    }
    /**
     * 
     * @return The new name from the view.
     */
    public String getNewName() {
        return newName;
    }
    /**
     * 
     * @return The new role from the view.
     */
    public String getNewRole() {
        return newRole;
    }
    /**
     * 
     * @return The new username from the view.
     */
    public String getNewUsername() {
        return newUsername;
    }
    /**
     * 
     * @param newAddress Sets the new address from the view.
     */
    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
    /**
     * 
     * @param newName Sets the new name from the view.
     */
    public void setNewName(String newName) {
        this.newName = newName;
    }
    /**
     * 
     * @param newRole Sets the new role from the view.
     */
    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
    /**
     * 
     * @param newUsername Sets the new username from the view.
     */
    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

   /**
    * Register a new user.
    * Also sets error variables to use in the view.
    */
    public void newUser() {
        if (!FacesContext.getCurrentInstance().getExternalContext().isUserInRole("management")) {
            errorPanelGroup = true;
            user.setPassword(password, newPasswordConfirmed);
            error = user.newUser(isCompany);
        } else { //Logged in as management
            user.newUser(newName, newUsername, newAddress, newRole);
            setNewName(null);
            setNewUsername(null);
            setNewAddress(null);
            setNewRole(null);
            // Sends a email to the new user with the password
            Mailing mail = new Mailing();
            String recipients[] = {getNewEmail()};
            String bccRecipients[] = {getNewEmail() };
            mail.sendMail(recipients, bccRecipients, "Password for HealtyCatering", "Your password are: password1 \n You should change your password later");
        }
    }

    /**
     * Method to log out a user.
     * @return Navigation case.
     */
    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            System.out.println("feil i doLogout" + e.getMessage());
        }
        return "Logout";
    }

    /**
     * Change the password of a user.
     * Also sets a error variable to use in the view.
     */
    public void changePassword() {
        error = passwordStatus = user.setnewPassword(newPassword, newPasswordConfirmed);
    }
    /**
    * 
    * @return The changed username from the view.
    * Used in chooseUser.
    */
    public String getUsernameChange() {
        return usernameChange;
    }
    /**
     * 
     * @param usernameChange Sets the changed username from the view.
     * Used in chooseUser.
     */
    public void setUsernameChange(String usernameChange) {
        this.usernameChange = usernameChange;
    }

    /**
     * Changes a users information.
     * @return Navigation case.
     * Also handle error variable to use in the view.
     */
    public String changeUser() {
        String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        String address = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("address");
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        user.changeUser(name, address, username);
        setUsernameChange(username);
        changedInformation = true;
        return "ManageUsers";
    }
    /**
     * Method to change your own user.
     */
    public void changeYourUser() {
        String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        String address = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("address");
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        user.changeUser(name, address, username);
        setUsernameChange(username);
    }
    /**
     * Sets The variable so you know what username(user) you want to change.
     * @return Navigation case.
     */
    public String chooseUser() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("management")) {
            setUsernameChange(username);
            return "changeUser";
        } else {
            return null;
        }
    }

    /**
     * Error variable.
     */
    public void loginAdmin() {
        adminLogin = true;
    }
    /**
     * Error variable.
     */
    public void notLoginAdmin() {
        adminLogin = false;
    }
    /**
     * 
     * @return A complete list of all customers.
     */
    public List getNormalUsers() {
        List list = userlist.getUsers("userNormal");
        List list2 = userlist.getUsers("userCompany");
        for (int i = 0; i < list2.size(); i++) {
            list.add(list2.get(i));
        }

        return list;
    }
    /**
     * 
     * @return A complete list of all management users.
     */
    public List getManagementUsers() {
        String role = "management";
        return userlist.getUsers(role);
    }
    /**
     * 
     * @return A complete list of all driver users.
     */
    public List getDriverUsers() {
        String role = "driver";
        return userlist.getUsers(role);
    }
    /**
     * 
     * @return A complete list of all chef users.
     */
    public List getChefUsers() {
        String role = "chef";
        return userlist.getUsers(role);
    }
    /**
     * 
     * @return A complete list of all  salesmen users.
     */
    public List getSalesmen(){
        String role = "salesmen";
        return userlist.getUsers(role);
    }
    /**
     * 
     * @return A complete of a specific user.
     */
    public List getUser() {
        return userlist.getUser(usernameChange);
    }
    /**
     * Reset the password for a specific user.
     * @return Navigation case.
     */
    public String resetPassword() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        user.resetPassword(username);
        setUsernameChange(username);
        resetPassword = true;
        return "ManageUsers";
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getResetPassword() {
        return resetPassword;
    }
    /**
     * 
     * @return Error variable.
     */
    public boolean getChangedInformation() {
        return changedInformation;
    }  
    
    
}
