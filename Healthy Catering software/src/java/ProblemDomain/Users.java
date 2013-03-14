package ProblemDomain;

import HelpClasses.DatabaseCon;

import javax.faces.context.FacesContext;

/**
 *
 * @author
 * havardb
 */
public class Users {

    public String username;
    public String name;
    public String address;
    public String password;
    public DatabaseCon con = new DatabaseCon();
    private String bruker = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    
    public Users() {
        if (bruker != null) {
            this.username = bruker;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void newUser(){
        System.out.println(name);
    }
}
