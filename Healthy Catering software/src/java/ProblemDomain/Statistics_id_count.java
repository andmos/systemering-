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
    private int id,count;
    
    public Statistics_id_count(int id, int count){
        this.id=id;
        this.count=count;
    }
    
    public int getId(){
        return id;
    }
    public int getCount(){
        return count;
    }
}
