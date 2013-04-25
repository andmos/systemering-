package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author
 * havardb
 */
public class tempUser {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private PreparedStatement line = null;
    private ResultSet res = null;
    public String name;
    public String address;
    public int order_id;
    public String sqlAddNewTempUser = "insert into tempUser(name,address) values(?,?)";
    public String sqlremoveTempUser = "delete from tempUser where order_id=?";
    public String sqlUpdateTempUser = "update tempUser set order_id=? where name=?";

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    
    public void addNewTempUser(){
        try{
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlAddNewTempUser);
            line.setString(1,this.name);
            line.setString(2, this.address);
            line.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failure in addNewTempUser():"+e.getMessage());
        }finally{
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
    }
    
    public void removeTempUser(){
        try{
             db.openConnection();
            line = db.getConnection().prepareStatement(sqlremoveTempUser);
            line.setInt(1,this.order_id);
            line.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failure in addNewTempUser():"+e.getMessage());
        }finally{
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
    }
    public void updateTempUser(){
        try{
             db.openConnection();
            line = db.getConnection().prepareStatement(sqlUpdateTempUser);
            line.setInt(1,this.order_id);
            line.setString(2,this.name);
            line.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failure in addNewTempUser():"+e.getMessage());
        }finally{
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }
    }
}
