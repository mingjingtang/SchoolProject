/**
 * Created by mingjingtang on 10/4/16.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class LineChart_AWT extends ApplicationFrame {

	public LineChart_AWT(String applicationTitle) throws IOException {
		super(applicationTitle);
	}

	public void generateChart(String chartTitle, List<Float> dataList) throws IOException {
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"n", "X(n)",
				createDataset(dataList),
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);
		File chartFile = new File( "lineChart-" + LineChart_AWT.getEpochTime() + ".png" );
		ChartUtilities.saveChartAsJPEG( chartFile , lineChart , 1000 , 370);
	}

	private DefaultCategoryDataset createDataset(List<Float> dataList) {
		/**
		 * remove initial value
		 */
		dataList.remove(0);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(int i = 0; i < dataList.size(); i++){
			dataset.addValue(dataList.get(i),"Data Point", String.valueOf(i));
		}
		return dataset;
	}

	public static int getEpochTime(){
		return Math.toIntExact(System.currentTimeMillis() / 1000);
	}
}
