package TO.EA;

import TO.GreedyAlg.NearestNeihbor;
import TO.Model.Vertex;
import TO.Util.XmlReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        ArrayList<Vertex> vertexList = XmlReader.getData();
        NearestNeihbor NN = new NearestNeihbor(vertexList);


        EvolutionaryAlg evolutionaryAlg = new EvolutionaryAlg(vertexList, NN);
        ArrayList<Vertex> evolutionSolution = evolutionaryAlg.generateSolution();

    }
}
