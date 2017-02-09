/**
 * Created by mingjingtang on 9/25/16.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LogGraph {
	private float A;
	private float initalValue;
	private List<Float> xnArrayList;
	private List<Float> xn1ArrayList;

	private LineChart_AWT chart0;
	private XYLogAxesDemo chart1;

	public LogGraph(float A, float initalValue) {
		this.A = A;
		this.initalValue = initalValue;
	}

	public List<Float> getMemory(){
		return xnArrayList;
	}

	/**
	 * dynamic programming
	 */
	public List<Float> calculateData(int n){
		if (n == 0) {
			return new ArrayList<>(Arrays.asList(initalValue));
		}

		xnArrayList = new ArrayList<>();
		xnArrayList.add(this.calculateData(0).get(0));
		//print initial value
		LogGraph.print(xnArrayList, 0);

		for(int i = 1; i < n + 1; i++){
			xnArrayList.add(A * xnArrayList.get(i - 1) * (1 - xnArrayList.get(i - 1)));
			LogGraph.print(xnArrayList, i);
		}
		return xnArrayList;
	}

	public List<Float> getcalculateData(int n){
		xn1ArrayList = new ArrayList<>();
		for(int i = 0; i < n; i++){
			xn1ArrayList.add(xnArrayList.get(i + 1));
			LogGraph.print(xn1ArrayList,i);
		}
		return xn1ArrayList;
	}

	public void generateGraph() throws IOException {
		chart0 = new LineChart_AWT(
				"LineChart Demo"
		);
		chart0.generateChart("X(n) VS n", xnArrayList);

		chart1 = new XYLogAxesDemo(
				"Scatter Plot Demo"
		);
		chart1.generateChart2("LOG X(n+1) vs X(n)", xnArrayList, xn1ArrayList);
	}

	private static void print(List<Float> memory, int index){
		System.out.println(memory.get(index) + " \t" + index);
	}
}
