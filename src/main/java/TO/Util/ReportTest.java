package TO.Util;

import TO.EA.EvolutionaryAlg;
import TO.GreedyAlg.GraspGC;
import TO.GreedyAlg.GraspNN;
import TO.GreedyAlg.GreedyCycle;
import TO.GreedyAlg.NearestNeihbor;
import TO.LS.LocalSearch;
import TO.LS.MSLS;
import TO.Model.Vertex;
import TO.Util.Report;
import TO.Util.XmlReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ReportTest {

    public static void main(String[] args)throws IOException, ParserConfigurationException, SAXException {

        ArrayList<Vertex> vertexList = XmlReader.getData();

        NearestNeihbor NN = new NearestNeihbor(vertexList);
        GraspNN graspNN = new GraspNN(vertexList);
        GreedyCycle greedyCycle = new GreedyCycle(vertexList);
        GraspGC graspGC = new GraspGC(vertexList);

        LocalSearch localSearch = new LocalSearch(vertexList, NN);
        MSLS multipleLS = new MSLS(vertexList, NN);

        //EvolutionaryAlg evolutionaryAlg = new EvolutionaryAlg(vertexList, NN);
        //ArrayList<Vertex> evolutionSolution = evolutionaryAlg.generateSolution();

        Report report = new Report("MSLS.txt");
        multipleLS.generateReport(report);

    }
}
