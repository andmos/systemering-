package Beans;

import ProblemDomain.Course;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author
 * havardb
 * Bean to handle everything related to courses.
 */
@SessionScoped
@Named("course")
public class CourseBean implements Serializable {

    private Course course = new Course();
    private int courseID;
    private String name;
    private String description;
    private int menuID;
    private double price;
    //Error handling
    private boolean updateCourseError;
    private boolean deleteCourseError;
    private boolean addCourseError;

    /**
     * 
     * @return Price of a course.
     */
    public double getPrice() {
        return course.getPrice();
    }
/**
 * 
 * @return Error variable.
 */
    public boolean getUpdateCourseError() {
        return updateCourseError;
    }
/**
 * 
 * @return Error variable.
 */
    public boolean getCourseError() {
        return addCourseError;
    }
    
    /**
     * 
     * @return Error variable.
     */
    public boolean getAddCourseError(){
        return addCourseError;
    }
/**
 * 
 * @param price Set price from view.
 */
    public void setPrice(double price) {
        this.price = price;
        course.setPrice(price);
    }
/**
 * 
 * @return A courses ID.
 */
    public int getCourseID() {
        return course.getCourse_id();
    }

    /**
     * 
     * @return The menu ID it belongs to.
     */
    public int getMenuID() {
        return menuID;
    }
/**
 * 
 * @param menuID Sets menu ID from view.
 */
    public void setMenuID(int menuID) {
        this.menuID = menuID;
        course.setMenu_id(menuID);
    }
/**
 * 
 * @return Course name.
 */
    public String getName() {
        return course.getName_course();
    }
/**
 * 
 * @return Course description.
 */
    public String getDescription() {
        return course.getDescription();
    }

    /**
     * 
     * @param courseID Set the ID to a course from the view.
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
        course.setCourse_id(courseID);
    }

    /**
     * 
     * @param description Set the description to a course from the view.
     */
    public void setDescription(String description) {
        this.description = description;
        course.setDescription(description);
    }
/**
 * 
 * @param name Sets the name of a course from the view.
 */
    public void setName(String name) {
        this.name = name;
        course.setName_course(name);
    }

    /**
     * 
     * @return  Error variable.
     */
    public boolean getDeleteCourseError() {
        return deleteCourseError;
    }

    /**
     * Sets ID to a course you have chosen from the database.
     * @return Navigation case.
     */
    public String chooseCourse() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("course_id");
        setCourseID(Integer.parseInt(value));
        String value2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        setMenuID(Integer.parseInt(value2));
        String value3 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("price");
        setPrice(Double.parseDouble(value3));
        setName(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name"));
        setDescription(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("description"));
        return "EditCourse";
    }
/**
 * Update a course you have chosen from the database.
 * @return Error message variable
 */
    public String updateCourse() {
        if (course.updateCourse()) {
            updateCourseError = true;
            addCourseError = false;
            deleteCourseError = false;
            return "EditMenu";
        } else {
            updateCourseError = false;
            return null;
        }
    }
/**
 * Delete a course you have chosen from the database.
 * Also sets a error message variable.
 */
    public void deleteCourse() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("course_id");
        setCourseID(Integer.parseInt(value));
        String value2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        setMenuID(Integer.parseInt(value2));
        if (course.deleteCourse()) {
            updateCourseError = false;
            addCourseError = false;
            deleteCourseError = true;
        } else {
            deleteCourseError = false;
        }
    }
    /**
     * Add a course to the menu you have chosen fro the database.
     * Also sets error message variables.
     */
    public void addCourse(){
        String value2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("menu_id");
        setMenuID(Integer.parseInt(value2));
        if(course.addCourse()){
            //Sets the variables back to null for future adding of courses
            setName(null);
            setPrice(0);
            setDescription(null);
            addCourseError = true;
            updateCourseError = false;
            deleteCourseError = false;
        }else{
            addCourseError = false;
        }
    }
}
