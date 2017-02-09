/**
 * Created by mingjingtang on 10/10/16.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class XYLogAxesDemo extends ApplicationFrame {

	public XYLogAxesDemo(final String title) throws IOException {
		super(title);
	}

	public void generateChart2(String chartTitle, List<Float> dataList, List<Float>dataList2) throws IOException {

		final XYSeriesCollection dataset = new XYSeriesCollection();
		final XYSeries s = new XYSeries("XY data");
		for (int i=0; i<dataList2.size(); i++) {
			float x = dataList.get(i);
			float y = dataList2.get(i);
			s.add(y, x);
		}
		dataset.addSeries(s);

		final JFreeChart chart = ChartFactory.createScatterPlot(
				chartTitle,          // chart title
				"X(n)",                    // domain axis label
				"X(n+1)",                  // range axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				true,                     // include legend
				true,
				false
		);

		final XYPlot plot = chart.getXYPlot();
		final NumberAxis domainAxis = new LogarithmicAxis("Log(x)");
		domainAxis.setAutoRangeIncludesZero(false);
		final NumberAxis rangeAxis = new LogarithmicAxis("Log(y)");
		rangeAxis.setAutoRangeIncludesZero(false);
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);

		File chartFile = new File( "XYLogAxesDemo-" + XYLogAxesDemo.getEpochTime() + ".png" );
		ChartUtilities.saveChartAsJPEG( chartFile , chart , 1000 , 370);
	}

	public static int getEpochTime(){
		return Math.toIntExact(System.currentTimeMillis() / 1000);
	}
}


