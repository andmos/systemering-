/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author espen
 */



public class Course_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT * FROM course where menu_id = ?";
    private int menu_id;
    
    public Course_List(int menu_id){
        this.menu_id = menu_id;
    }
    
    public List buildCourseList() {
        List<Course> list = new ArrayList<Course>();
        try {
            db.openConnection();
            
            line = db.getConnection().prepareStatement(sqlConstructor);
            line.setInt(1, menu_id);
            res = line.executeQuery();
            while (res.next()) {
                Course menu = new Course();
                
                menu.course_id = res.getInt("course_id");
                menu.name_course = res.getString("name_course");
                menu.price = res.getInt("price");
  
                list.add(menu);
            }
        } catch (SQLException e) {
            System.out.println("Could not get name from DB " + e.getMessage());    
        } finally {
            db.closeResSet(res);
            db.closeStatement(line);
            db.closeConnection();
        }
        return list;
    }

    public List getMenu() {
        List<Menucombination> list = buildCourseList();
        return list; //.size()>0 ? list : null;
    }

}

