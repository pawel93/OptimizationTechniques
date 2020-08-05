package TO.EA;

import TO.Util.Algorithm;
import TO.Util.Evaluator;
import TO.LS.LocalSearch;
import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.Random;

public class EvolutionaryAlg {

    private PopulationGenerationStrategy populationGenerationStrategy;
    private PopulationSelectionStrategy selectionStrategy;

    private Evaluator evaluator;
    private Recombination recombination;
    private LocalSearch localSearch;

    public EvolutionaryAlg(ArrayList<Vertex> vertexList, Algorithm greedyAlg){
        this.populationGenerationStrategy = new PopulationGenerationStrategyImpl(vertexList, greedyAlg);
        this.selectionStrategy = new SelectionStrategyWithWorstSubject();
        this.evaluator = new Evaluator();
        this.localSearch = new LocalSearch(vertexList, greedyAlg);
        this.recombination = new Recombination(vertexList);
    }

    public ArrayList<Vertex> findBestIndividualInThePopulation(ArrayList<ArrayList<Vertex>> population){

        ArrayList<Vertex> bestIndividual = population.get(0);
        int bestCost = evaluator.evaluateSolution(bestIndividual);

        for(ArrayList<Vertex> individual: population){
            int cost = evaluator.evaluateSolution(individual);
            if(cost < bestCost){
                bestCost = cost;
                bestIndividual = individual;
            }
        }
        return bestIndividual;
    }

    public ArrayList<Vertex> generateSolution(){

        Random rnd = new Random();
        ArrayList<ArrayList<Vertex>> population = populationGenerationStrategy.generatePopulation();

        long startTime = System.currentTimeMillis();
        do{
            ArrayList<Vertex> parent1 = population.get(rnd.nextInt(Const.POPULATION_SIZE));
            ArrayList<Vertex> parent2 = population.get(rnd.nextInt(Const.POPULATION_SIZE));
            ArrayList<Vertex> child = recombination.recombine(parent1, parent2);

            localSearch.generateSolution(child);
            selectionStrategy.selectNextPopulation(population, child);

        }while((System.currentTimeMillis()-startTime)<Const.TIME_MS);

        return findBestIndividualInThePopulation(population);
    }




}
