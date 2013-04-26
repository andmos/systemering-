package Beans;

import Lists.Ingredient_List;
import Lists.Inventory_List;
import ProblemDomain.Course;
import ProblemDomain.Ingredient;
import ProblemDomain.Inventory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author
 * havardb
 * A bean to handle requests to a inventory and ingredients.
 */
@SessionScoped
@Named("inventory")
public class InventoryBean implements Serializable {

    private int inventory_id;
    private String type;
    private Inventory inventory = new Inventory();
    private Ingredient ingredient = new Ingredient();
    private Inventory_List inventorylist = new Inventory_List();
    private Ingredient_List ingredientlist = new Ingredient_List();
    private String ingredientName;
    private int ingredientQuantity;

    /**
     * 
     * @return Ingredient name.
     */
    public String getIngredientName() {
        return ingredient.name;
    }
/**
 * 
 * @return Ingredient quantity.
 */
    public int getIngredientQuantity() {
        return ingredient.quantity;
    }

    /**
     * 
     * @return Inventory type.
     */
    public String getType() {
        return inventory.type;
    }
/**
 * 
 * @return Inventory ID.
 */
    public int getInventory_id() {
        return inventory.getInventory_id();
    }
/**
 * 
 * @param type Sets the type of a inventory from the view.
 */
    public void setType(String type) {
        this.type = type;
        inventory.setType(type);
    }

    /**
     * 
     * @param ingredientName Sets the ingredient name from the view.
     */
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
        ingredient.setName(ingredientName);
    }

/**
 * 
 * @param ingredientQuantity Sets the ingredient quantity from the view.
 */
    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
        ingredient.setQuantity(ingredientQuantity);
    }
/**
 * 
 * @param inventory_id Sets the inventory ID from the view.
 */
    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
        inventory.setInventory_id(inventory_id);
    }

    /**
     * 
     * @return A list of the inventory from the database.
     */
    public List<Inventory> getInventory() {
        return inventorylist.getInventory();
    }

    /**
     * 
     * @return A list of ingredients from the database on a specific inventory.
     */
    public List<Ingredient> getIngredients() {
        return ingredientlist.getIngredients(inventory_id);
    }

    /**
     * Sets the inventory ID of a chosen inventory from the database.
     * @return Navigation case 
     */
    public String chooseInventory() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inventory_id");
        setInventory_id(Integer.parseInt(value));
        return "ViewInventory";
    }
/**
 * Adds the given quantity to a ingredient.
 */
    public void addQuantity() {
        ingredient.addQuantity(inventory_id);
        setIngredientName(null);
        setIngredientQuantity(0);
    }

    /**
     * Removes the given quantity to a ingredient.
     */
    public void removeQuantity() {
        ingredient.removeQuantity(inventory_id);
        setIngredientName(null);
        setIngredientQuantity(0);
    }
    /**
     * Add a new ingredient to the database.
     */
    public void addNewIngredient(){
        ingredient.addNewIngredient(inventory_id);
        setIngredientName(null);
        setIngredientQuantity(0);
    }
/**
 * 
 * @return All the names of the ingredients on a specific inventory.
 */
    public List<String> getIngredientsName() {
        List<String> list = new ArrayList();
        for (int i = 0; i < getIngredients().size(); i++) {
            list.add(getIngredients().get(i).getName());
        }
        return list;
    }
    
    /**
     * @return Dry ingredients.
     */
    public List<Ingredient> getDryIngredients(){
        return ingredientlist.getIngredients(1);
    }
    
    /**
     * 
     * @return Cold ingredients.
     */
    public List<Ingredient> getColdIngredients(){
        return ingredientlist.getIngredients(2);
    }
    
}
