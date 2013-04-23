package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 *
 * @author
 * havardb
 */
public class Users_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    public String sqlUsers = "select name,address,users.username from users,roles where users.username=roles.username and roles.role=?";
    public String sqluser = "select name,address from users where username=?";

    public List getUsers(String role) {
        List list = new ArrayList();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlUsers);
            line.setString(1,role);
            res = line.executeQuery();
                while (res.next()) {
                    Users user = new Users();
                    user.name = res.getString("name");
                    user.address = res.getString("address");
                    user.username = res.getString("username");
                    list.add(user);
                }
        } catch (SQLException e) {
            System.out.println("getUsers() failed:"+e.getMessage());
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }
    
    public List getUser(String username){
        List list = new ArrayList();
        try{
            db.openConnection();
            line = db.getConnection().prepareStatement(sqluser);
            line.setString(1,username);
            res = line.executeQuery();
            while(res.next()){
                Users user = new Users();
                user.name = res.getString("name");
                user.address = res.getString("address");
                list.add(user);
            }
        }catch(SQLException e){
            System.out.println("getUser() failed:"+e.getMessage());
        } finally{
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }
}
