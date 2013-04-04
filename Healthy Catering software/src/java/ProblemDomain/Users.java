package ProblemDomain;

import HelpClasses.DatabaseCon;

import javax.faces.context.FacesContext;
import java.sql.*;

/**
 *
 * @author andreas
 * Users - class with variables for User object and database connection. 
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
    // SQL - statements: 
    public String sqlConstructor = "SELECT * FROM users WHERE username =?";
    public String sqlnewUser = "INSERT INTO users VALUES (?, ?, ?, ?)";
    public String sqlnewUserRole = "INSERT INTO roles values('user',?)"; //user - role is hardcoded inn becouse a user registration from the site should be a normal user. 
    /**
     * Constructor checks if a user is logged inn on the website, and creates an object from data in the database. 
     */
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
    /**
     * @return returns username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param newUsername Takes inn new username, and sets it
     */
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    /**
     * 
     * @return password for user object 
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * 
     * @return name of user object
     */
    public String getName() {
        return this.name;
    }
    /**
     * 
     * @return address of user object 
     */
    public String getAddress() {
        return this.address;
    }
    /**
     * @param name sets new name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param newAddress sets new address  
     */
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
    /**
     * @param newPassword inn and sets new password
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    /**
     * @return id of user object 
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * @param newId sets new id
     */
    public void setId(int newId){
        this.id = newId; 
    }
    /**
     * Makes a new user input in the database, both in the users and role table.
     */
    public boolean newUser() { 
        try {
                db.openConnection();
                db.getConnection().setAutoCommit(false);
                line = db.getConnection().prepareStatement(sqlnewUser);
                line.setString(1, this.name);
                line.setString(2, this.address);
                line.setString(3, this.password);
                line.setString(4, this.username);
                line.executeUpdate();
                db.closeStatement(line);
                line = db.getConnection().prepareStatement(sqlnewUserRole);
                line.setString(1, this.username);
                line.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Could not create user in DB" + e.getMessage());
                return false;

            } finally {
                db.closeResSet(res);
                db.closeStatement(line);
                db.setAutoCommit();
                db.closeConnection();
            }
    }
}