package Beans;

/**
 *
 * @author
 * havardb
 */

import ProblemDomain.Users;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.DataSource;


@SessionScoped 
@Named("user")
public class UserBean implements Serializable {
    private String name;
    private String username; 
    private String password; 
    private String address; 
    private Users user = new Users();
   
    
    public String getUsername(){
        this.username = user.getUsername();
        return this.username; 
    }
    public void setUsername(String newName){
        user.setUsername(newName);
    }
    public String getPassword(){
        this.password = user.getPassword();
        return this.password; 
    }
    public void setPassword(String newPassword){
        user.setPassword(newPassword);
    }
    
    public String getAddress(){
        this.address = user.getAddress();
        return this.address; 
    }
    public void setAddress(String newAddress){
        user.setAddress(newAddress);
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
    }
    
    public void newUser(){
        System.out.println("TEST");
    }
    
    
}
