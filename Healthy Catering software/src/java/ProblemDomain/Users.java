package ProblemDomain;

import javax.faces.context.FacesContext;

/**
 *
 * @author havardb
 */
public class Users {
public String username;  
public String name;
public String address;
public String password;

private String bruker = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

public Users(){
   if(bruker != null){
       this.username = bruker; 
   }
}


    


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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
    

    
}
