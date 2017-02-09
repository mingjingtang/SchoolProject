/**
 * Created by mingjingtang on 9/22/16.
 *
 * Find API that you want to use, then
 *      search.maven.org
 *  to find the Gradle compile String for that API.
 */

import java.io.IOException;


public class Driver {
    public static void main(String[]args) throws IOException {
        LogGraph graph = new LogGraph(2.0f, 0.9f);
        graph.calculateData(499);
        graph.getcalculateData(499);
        graph.generateGraph();
    }
}


