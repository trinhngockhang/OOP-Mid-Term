import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Chart extends Stage{
	
	public Chart(int level){
			String levelString = Integer.toString(level);
		   CategoryAxis xAxis = new CategoryAxis();
	       xAxis.setLabel("History Bar Chart");
	       ArrayList<String[]> historyData = Function.readFile("history.txt",levelString);
	      
	       NumberAxis yAxis = new NumberAxis();
	       yAxis.setLabel("Score");
	 
	  
	       // Tạo đối tượng BarChart
	       BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
	 
	  
	       // Series 1 - Số liệu năm 2014
	       XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
	       dataSeries1.setName("Level :" + levelString);
	       for(int i=0;i<historyData.size();i++) {
	    	   String[] data = historyData.get(i);
	    	   System.out.println(data[0]);
	    		   System.out.println("ngay" + data[2]);
	    		   dataSeries1.getData().add(new XYChart.Data<String, Number>(data[2],Integer.parseInt( data[1])));
	    	   
	       }
	      
	       //dataSeries1.getData().add(new XYChart.Data<String, Number>("PHP", 2.792));
	 
	  
	       // Thêm Series vào BarChart
	       barChart.getData().add(dataSeries1);
	 
	       barChart.setTitle("Your history");
	 
	       VBox vbox = new VBox(barChart);
	 
	       Scene scene = new Scene(vbox, 400, 200);
	 
	       this.setScene(scene);
	       this.setHeight(600);
	       this.setWidth(700);
	 
	       this.show();
	}
	
	
	
}
