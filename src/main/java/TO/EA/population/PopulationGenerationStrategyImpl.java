package TO.EA.population;

import TO.EA.parameters.Const;
import TO.EA.population.PopulationGenerationStrategy;
import TO.LS.LocalSearch;
import TO.Model.Vertex;
import TO.Util.Algorithm;

import java.util.ArrayList;
import java.util.Random;

public class PopulationGenerationStrategyImpl implements PopulationGenerationStrategy {

    private LocalSearch localSearch;

    public PopulationGenerationStrategyImpl(LocalSearch localSearch){
        this.localSearch = localSearch;
    }

    public ArrayList<ArrayList<Vertex>> generatePopulation(){

        Random rnd = new Random();
        ArrayList<ArrayList<Vertex>> population = new ArrayList<>();

        for(int i = 0; i< Const.POPULATION_SIZE; i++){
            ArrayList<Vertex> result = localSearch.generateSolution(rnd.nextInt(100));
            population.add(result);
        }
        return population;

    }
}
