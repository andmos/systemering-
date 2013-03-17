package Beans;

import ProblemDomain.Users;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



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
        this.username = newName; 
        user.setUsername(newName);
    }
    public String getPassword(){
        this.password = user.getPassword();
        return this.password; 
    }
    public void setPassword(String newPassword){
        this.password = newPassword; 
        user.setPassword(newPassword);
    }
    
    public String getAddress(){
        this.address = user.getAddress();
        return this.address; 
    }
    public void setAddress(String newAddress){
        System.out.println("VI KOMMER OSS INN I SETMETODEN ");
        this.address = newAddress; 
        user.setAddress(newAddress);
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
    }
    
    public void newUser(){
        System.out.println("VI KOMMER SÅ LANGT");
        user.newUser();
    }   
}
