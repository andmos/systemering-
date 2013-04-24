package Beans;

import ProblemDomain.Course;
import ProblemDomain.Ingredient;
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
@Named("inventory")
public class InventoryBean implements Serializable {
    private int inventory_id;
    private String type;
    private Ingredient ingredient = new Ingredient();
}
