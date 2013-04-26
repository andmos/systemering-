/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author espen
 * this bean keeps track of statistics
 */
import ProblemDomain.Statistics;
import HelpClasses.Statistics_id_count;
import HelpClasses.Statistics_username_priceSum;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@SessionScoped
@Named("model")
public class ChartBean implements Serializable {

    private PieChartModel pieModel;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private Statistics stat = new Statistics();

    /**
     * Constructor.
     */
    public ChartBean() {
        createPieModelMostPopularMenu();
        createPieModelMostProfitableCustomers();
        createPieModelNumberOf();
    }
/**
 * 
 * @return pie-model for statistics.
 */
    public PieChartModel getPieModel() {
        return pieModel;
    }
  /**
   * 
   * @return pie-model for statistics.
   */
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
   /**
   * 
   * @return pie-model for statistics.
   */
    public PieChartModel getPieModel3() {
        return pieModel3;
    }
    /**
     * Creates most popular menu statistics from database and puts it into one of the models.
     */
    private void createPieModelMostPopularMenu() {
        pieModel = new PieChartModel();
        List<Statistics_id_count> list = stat.getMostPopularMenu();
        for (Statistics_id_count s : list) {            
            pieModel.set("Menu: " + s.getMenu(), s.getCount());
        }
        
    }/**
     * Creates most profitable customer from database and puts it into one of the pie-models.
     */
    private void createPieModelMostProfitableCustomers(){
        pieModel2= new PieChartModel();
        List<Statistics_username_priceSum> list = stat.getMostProfitableCustomers();
        for(Statistics_username_priceSum s: list){
            pieModel2.set("Username: " + s.getUsername(), s.getPriceSum());
        }
    }
    /**
     * 
     * @return Active users witch are registered, either as an employee or an customer.
     */
    public List getActiveCustomers(){
        return stat.getActiveCustomers();
    }
    
    /**
     * Creates the actual pie models.
     */
    private void createPieModelNumberOf(){
        pieModel3 = new PieChartModel();
        pieModel3.set("Chefs: ", stat.getNumberOfChefs());
        pieModel3.set("Customer: ", stat.getNumberOfCustomers());
        pieModel3.set("Management: ", stat.getNumberOfManagement());
    }
}
