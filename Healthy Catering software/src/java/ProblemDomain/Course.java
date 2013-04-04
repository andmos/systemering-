package ProblemDomain;
/**
 *
 * @author havardb
 */
public class Course {
public int course_id;
public String name_course;
public double price;
public String description;

    public double getPrice() {
        return price;
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
    
    

    
}
