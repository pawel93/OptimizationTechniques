package TO.EA;

import TO.EA.parameters.MaxCommonSequenceParameter;
import TO.EA.parameters.SolutionLengthParameter;
import TO.EA.population.PopulationGenerationStrategy;
import TO.EA.population.PopulationGenerationStrategyImpl;
import TO.EA.recombination.CommonSequenceFinder;
import TO.EA.recombination.Recombination;
import TO.EA.selection.PopulationSelectionStrategy;
import TO.EA.selection.SelectionStrategyWithWorstSubject;
import TO.GreedyAlg.NearestNeihbor;
import TO.LS.LocalSearch;
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

        LocalSearch localSearch = new LocalSearch(vertexList, NN);
        PopulationGenerationStrategy populationGenerationStrategy = new PopulationGenerationStrategyImpl(localSearch);
        PopulationSelectionStrategy populationSelectionStrategy = new SelectionStrategyWithWorstSubject();

        MaxCommonSequenceParameter maxCommonSequenceParameter = new MaxCommonSequenceParameter();
        SolutionLengthParameter solutionLengthParameter = new SolutionLengthParameter();
        CommonSequenceFinder commonSequenceFinder = new CommonSequenceFinder(maxCommonSequenceParameter, solutionLengthParameter);
        Recombination recombination = new Recombination(vertexList, commonSequenceFinder, solutionLengthParameter);

        EvolutionaryAlg evolutionaryAlg = new EvolutionaryAlg(localSearch, populationGenerationStrategy, populationSelectionStrategy, recombination);
        ArrayList<Vertex> evolutionSolution = evolutionaryAlg.generateSolution();

    }
}
