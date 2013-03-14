package Beans;

/**
 *
 * @author
 * havardb
 */


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
    
    public String username; 
    public String password; 
    public String address; 
   
    
    
    public String getUsername(){
        return username; 
    }
    public void setUsername(String newName){
        username = newName; 
    }
    public String getPassword(){
        return password; 
    }
    public void setPassword(String newPassword){
        password = newPassword; 
    }
    public String getAddress(){
        return address; 
    }
    public void setAddress(String newAddress){
        address = newAddress; 
    }
    
    
}
