package ProblemDomain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author
 * havardb
 */
public class Course {

    public int course_id;
    public String name_course;
    public double price;
    public String description;
    public int menu_id;
    public boolean editable;
    private PreparedStatement line = null;
    private ResultSet res = null;
    public HelpClasses.DatabaseCon db = new HelpClasses.DatabaseCon(); //makes object of DatabaseCon class
    public String sqlUpdateCourse = "update course set description=?, price=?, name_course=? where menu_id=? and course_id=?";

    public double getPrice() {
        return price;
    }

    public void setMenu_id(int menuID) {
        this.menu_id = menuID;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public String getName_course() {
        return name_course;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setName_course(String name_course) {
        this.name_course = name_course;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public boolean updateCourse() {
        try {
            db.openConnection();
            line = db.getConnection().prepareStatement(sqlUpdateCourse);
            line.setString(1, description);
            line.setDouble(2, price);
            line.setString(3, name_course);
            line.setInt(4, menu_id);
            line.setInt(5, course_id);
            line.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Failure in updateCourse():" + e.getMessage());
            return false;
        } finally {
            db.closeConnection();
            db.closeResSet(res);
            db.closeStatement(line);
        }

    }
}
