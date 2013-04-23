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
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@SessionScoped
@Named("model")
public class ChartBean implements Serializable {

    private PieChartModel pieModel;
    //private Statistics stat = new Statistics();

    public ChartBean() {
        System.out.println("vi initierer chartbean ");
        createPieModel();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    /*private void createPieModel() {
        pieModel = new PieChartModel();
        List<Statistics_id_count> list = stat.getMostPopularMenu();
        System.out.println("test pie chart ");
        for (Statistics_id_count s : list) {
            pieModel.set("Menu ID:" + s.getId(), s.getCount());
        }
        pieModel.set("lol", 123);
        pieModel.set("lols", 123);
    }*/
    private void createPieModel() {  
        pieModel = new PieChartModel();  
  
        pieModel.set("Brand 1", 540);  
        pieModel.set("Brand 2", 325);  
        pieModel.set("Brand 3", 702);  
        pieModel.set("Brand 4", 421);  
    } 
}
