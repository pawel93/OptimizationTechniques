package TO.EA;

import TO.LS.LocalSearch;
import TO.Model.Vertex;
import TO.Util.Algorithm;

import java.util.ArrayList;
import java.util.Random;

public class PopulationGenerationStrategyImpl implements PopulationGenerationStrategy{

    private LocalSearch localSearch;

    public PopulationGenerationStrategyImpl(ArrayList<Vertex> vertexList, Algorithm greedyAlg){
        localSearch = new LocalSearch(vertexList, greedyAlg);
    }

    public ArrayList<ArrayList<Vertex>> generatePopulation(){

        Random rnd = new Random();
        ArrayList<ArrayList<Vertex>> population = new ArrayList<>();

        for(int i=0; i<Const.POPULATION_SIZE; i++){
            ArrayList<Vertex> result = localSearch.generateSolution(rnd.nextInt(100));
            population.add(result);
        }
        return population;

    }
}
