/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lists;

import ProblemDomain.Course;
import ProblemDomain.Menus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author
 * espen
 */
public class Course_List {

    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    private String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    private PreparedStatement line = null;
    private ResultSet res = null;
    private String sqlConstructor = "SELECT * FROM course where menu_id = ?";
    private int menu_id;

    public Course_List(int menu_id) {
        this.menu_id = menu_id;
    }

    /**
     * Menu_id
     * =
     * 0
     * ,If
     * you
     * have
     * not
     * choosen
     * a
     * menu
     * from
     * your
     * order
     * history.
     * Menu_id
     * !=
     * 0
     * ,If
     * you
     * have
     * choosen
     * a
     * menu
     * from
     * your
     * order
      * history.
     */
    public List buildCourseList() {
        List<Course> list = new ArrayList<Course>();
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlConstructor);
            line.setInt(1, menu_id);
            res = line.executeQuery();
            while (res.next()) {
                Course course = new Course();
                course.course_id = res.getInt("course_id");
                course.name_course = res.getString("name_course");
                course.menu_id = res.getInt("menu_id");
                //userNormal = normal price, userCompany = normal price  * VAT
                if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userNormal")) {
                    course.price = res.getInt("price")*1.25;
                }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("userCompany")){
                    course.price = res.getInt("price");
                }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("management")){
                    course.price = res.getInt("price");
                }else{
                    course.price = res.getInt("price")*1.25;
                }
                course.description = res.getString("description");
                list.add(course);
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

    public List getCourses() {
        List<Menus> list = buildCourseList();
        return list; //.size()>0 ? list : null;
    }
}