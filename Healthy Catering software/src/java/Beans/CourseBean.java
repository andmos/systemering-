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

    public double getPrice() {
        return course.getPrice();
    }

    public boolean getUpdateCourseError() {
        return updateCourseError;
    }

    public boolean getCourseError() {
        return addCourseError;
    }
    
    public boolean getAddCourseError(){
        return addCourseError;
    }

    public CourseBean() {
    }

    public void setPrice(double price) {
        this.price = price;
        course.setPrice(price);
    }

    public int getCourseID() {
        return course.getCourse_id();
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
        course.setMenu_id(menuID);
    }

    public String getName() {
        return course.getName_course();
    }

    public String getDescription() {
        return course.getDescription();
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
        course.setCourse_id(courseID);
    }

    public void setDescription(String description) {
        this.description = description;
        course.setDescription(description);
    }

    public void setName(String name) {
        this.name = name;
        course.setName_course(name);
    }

    public boolean getDeleteCourseError() {
        return deleteCourseError;
    }

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
