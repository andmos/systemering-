package ProblemDomain;

import HelpClasses.DatabaseCon;

import javax.faces.context.FacesContext;
import java.sql.*;

/**
 *
 * @author havardb
 */
public class Users {

    public String username;
    public String name;
    public String address;
    public String password;
    public int id; 
    public DatabaseCon db = new DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    /*
     * SQL - querys
     */
    public String sqlConstructor = "Select * from users where username =?";
    public String sqlInsert = "INSERT INTO users (`id`, `name`, `address`, `password`, `username`) VALUES (?, ?, ?, ?, ?)";
    
    public Users() {
        if (user != null) {
            this.username = user;
            try {
                db.openConnection();
                line = db.getConnection().prepareStatement(sqlConstructor);
                line.setString(1, this.username);
                res = line.executeQuery();
                while (res.next()) {
                    this.name = res.getString("name");
                    this.address = res.getString("address");
                    this.password = res.getString("password");

                }
            } catch (SQLException e) {
                System.out.println("Could not get name from DB " + e.getMessage());

            } finally {
                db.closeResSet(res);
                db.closeStatement(line);
                db.closeConnection();
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
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

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int newId){
        this.id = newId; 
    }
    
    public void newUser() {
        try {
                db.openConnection();
                line = db.getConnection().prepareStatement(sqlInsert);
                line.setInt(1, 7); //hardcoded for TESTING 
                line.setString(2, this.name);
                line.setString(3, this.address);
                line.setString(4, this.password);
                line.setString(5, this.username);
                
                
                line.executeUpdate(); 
                
            } catch (SQLException e) {
                System.out.println("Could not create user in DB" + e.getMessage());

            } finally {
                db.closeResSet(res);
                db.closeStatement(line);
                db.closeConnection();
            }
    }
}
