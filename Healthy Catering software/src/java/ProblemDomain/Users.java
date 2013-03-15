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
    public DatabaseCon db = new DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    /*
     * SQL - querys
     */
    public String sqlConstructor = "Select * from users where username =?";

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

    public void newUser() {
        System.out.println(name);
    }
}
