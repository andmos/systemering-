/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author espen
 */
import ProblemDomain.Statistics;
import ProblemDomain.Statistics_id_count;
import ProblemDomain.Statistics_username_priceSum;
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

    public ChartBean() {
        System.out.println("vi initierer chartbean ");
        createPieModelMostPopularMenu();
        createPieModelMostProfitableCustomers();
        createPieModelNumberOf();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    private void createPieModelMostPopularMenu() {
        pieModel = new PieChartModel();
        List<Statistics_id_count> list = stat.getMostPopularMenu();
        System.out.println("test pie chart ");
        for (Statistics_id_count s : list) {            
            pieModel.set("Menu: " + s.getMenu(), s.getCount());
        }
        
    }
    private void createPieModelMostProfitableCustomers(){
        pieModel2= new PieChartModel();
        List<Statistics_username_priceSum> list = stat.getMostProfitableCustomers();
        
        for(Statistics_username_priceSum s: list){
            pieModel2.set("Username: " + s.getUsername(), s.getPriceSum());
        }
    }
    public List getActiveCustomers(){
        return stat.getActiveCustomers();
    }
    
    private void createPieModelNumberOf(){
        pieModel3 = new PieChartModel();
        pieModel3.set("Chefs: ", stat.getNumberOfChefs());
        pieModel3.set("Customer: ", stat.getNumberOfCustomers());
        pieModel3.set("Management: ", stat.getNumberOfManagement());
    }
}
