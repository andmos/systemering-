/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemDomain;

/**
 *
 * @author espen
 */

public class Statistics_id_count {
    private int count;
    private String menu;
    
    public Statistics_id_count(int count,String menu){
        
        this.count=count;
        this.menu=menu;
    }
    
    
    public String getMenu(){
        return menu;
    }
    public int getCount(){
        return count;
    }
    public String toString(){
        return "Menu: " + menu + " Count: "+ count + " \n";
    }
}
