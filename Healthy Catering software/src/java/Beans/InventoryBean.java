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

    public String getIngredientName() {
        return ingredient.name;
    }



    public int getIngredientQuantity() {
        return ingredient.quantity;
    }

    public String getType() {
        return inventory.type;
    }

    public int getInventory_id() {
        return inventory.getInventory_id();
    }

    public void setType(String type) {
        this.type = type;
        inventory.setType(type);
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
        ingredient.setName(ingredientName);
    }


    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
        ingredient.setQuantity(ingredientQuantity);
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
        inventory.setInventory_id(inventory_id);
    }

    public List<Inventory> getInventory() {
        return inventorylist.getInventory();
    }

    public List<Ingredient> getIngredients() {
        return ingredientlist.getIngredients(inventory_id);
    }

    public String chooseInventory() {
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inventory_id");
        setInventory_id(Integer.parseInt(value));
        return "ViewInventory";
    }

    public void addQuantity() {
        ingredient.addQuantity(inventory_id);
        setIngredientName(null);
        setIngredientQuantity(0);
    }

    public void removeQuantity() {
        ingredient.removeQuantity(inventory_id);
        setIngredientName(null);
        setIngredientQuantity(0);
    }
    
    public void addNewIngredient(){
        ingredient.addNewIngredient(inventory_id);
        setIngredientName(null);
        setIngredientQuantity(0);
    }

    public List<String> getIngredientsName() {
        List<String> list = new ArrayList();
        for (int i = 0; i < getIngredients().size(); i++) {
            list.add(getIngredients().get(i).getName());
        }
        return list;
    }
    
}
