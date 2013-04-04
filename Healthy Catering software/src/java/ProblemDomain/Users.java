package ProblemDomain;

import HelpClasses.DatabaseCon;

import javax.faces.context.FacesContext;
import java.sql.*;

/**
 *
 * @author andreas Users - class with variables for User object and database
 * connection.
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
     * Constructor checks if a user is logged inn on the website, and creates an
     * object from data in the database.
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
        String sqlStatement = "UPDATE user SET name=? WHERE username =?";
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlStatement);
            line.setString(1, name);
            line.setString(2, user);
            line.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());

        } finally {
            db.closeStatement(line);
            db.closeConnection();
        }
    }

    /**
     * @param newAddress sets new address
     */
    public void setAddress(String newAddress) {
        this.address = newAddress;
        String sqlStatement = "UPDATE user SET address=? WHERE username =?";
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlStatement);
            line.setString(1, newAddress);
            line.setString(2, user);
            line.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());

        } finally {
            db.closeStatement(line);
            db.closeConnection();
        }
    }

    private boolean checkPasswordCriteria(String newPassword) {
        // numbers, special characters, alphabet, length
        String reg = "^(?=.*[0-9])(?=.*[`~!@#$%^&*()_+./{}|:\"<>?])[a-zA-Z0-9].{6,10}$";
        return (newPassword.matches(reg)) ? true : false;
    }

    private int verifyPassword(String newPassword, String confirmedPassword) {

        if (!newPassword.equals(confirmedPassword)) {
            return 1;
        }
        if (password.equals(newPassword)) {
            return 2;
        }
        if (!checkPasswordCriteria(newPassword) && newPassword.matches("^(?=.*[`~!@#$%^&*()_+./{}|:\"<>?])[a-zA-Z0-9].{6,10}$")) {
            return 3;
        }
        if (!checkPasswordCriteria(newPassword) && newPassword.matches("^(?=.*[0-9])[a-zA-Z0-9].{6,10}$")) {
            return 4;
        }
        if (!checkPasswordCriteria(newPassword) && newPassword.matches("^(?=.*[0-9])(?=.*[`~!@#$%^&*()_+./{}|:\"<>?])[a-zA-Z0-9]$")) {
            return 5;
        }

        return 0;
    }

    /**
     * @param newPassword inn and sets new password
     *
     * return: 
     * 0 = password is changed 
     * 1 = newPassword and confirmPassword is  not equal 
     * 2 = newPassword is the same as registered in the database 
     * 3 = newPassword does not contain numbers 
     * 4 = newPassword does not contain special characters 
     * 5 = newPassword is of wrong length
     * 6 = SQL ERROR 
     */
    public int setPassword(String newPassword, String confirmPassword) {

        int passwordCheck = verifyPassword(newPassword, confirmPassword);
        if (passwordCheck != 0) {
            return passwordCheck;
        } else {
            this.password = newPassword;
            String sqlStatement = "UPDATE user SET password=? WHERE username =?";
            try {
                db.openConnection();
                line = db.getConnection().prepareStatement(sqlStatement);
                line.setString(1, newPassword);
                line.setString(2, user);
                line.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Could not get name from DB " + e.getMessage());
                return 6;

            } finally {
                db.closeStatement(line);
                db.closeConnection();
                return 0;
            }
        }    
    }

    /**
     * @return id of user object
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param newId sets new id
     */
    public void setId(int newId) {
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