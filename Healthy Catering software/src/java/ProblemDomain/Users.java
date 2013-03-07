package ProblemDomain;

/**
 *
 * @author havardb
 */
public class users {
public int id;
public String name;
public String address;
public String password;

    public String getPassword() {
        return password;
    }

    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
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
