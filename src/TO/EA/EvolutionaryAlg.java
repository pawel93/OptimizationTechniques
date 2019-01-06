package TO.EA;

import TO.Util.Algorithm;
import TO.Util.Evaluator;
import TO.LS.LocalSearch;
import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.Random;

public class EvolutionaryAlg {


    private ArrayList<Vertex> vertexList;
    private Algorithm greedyAlg;

    public EvolutionaryAlg(ArrayList<Vertex> vertexList, Algorithm greedyAlg){
        this.vertexList = vertexList;
        this.greedyAlg = greedyAlg;
    }

    public ArrayList<ArrayList<Vertex>> generateStartPopulation(){

        LocalSearch localSearch = new LocalSearch(vertexList, greedyAlg);
        Random rnd = new Random();
        ArrayList<ArrayList<Vertex>> population = new ArrayList<>();

        for(int i=0; i<Const.POPULATION_SIZE; i++){
            ArrayList<Vertex> result = localSearch.generateSolution(rnd.nextInt(100));
            population.add(result);
        }
        return population;

    }


    public void selectPopulation(ArrayList<ArrayList<Vertex>> population, ArrayList<Vertex> child){
        Evaluator evaluator = new Evaluator();
        int position = 0;
        int maxCost = evaluator.evaluateSolution(population.get(position));
        for(int i=0; i<population.size(); i++){
            int cost = evaluator.evaluateSolution(population.get(i));
            if(cost > maxCost){
                maxCost = cost;
                position = i;
            }
        }
        int childCost = evaluator.evaluateSolution(child);
        if(childCost < maxCost){
            population.set(position, child);
        }
    }


    public void selectPopulationWithRandom(ArrayList<ArrayList<Vertex>> population, ArrayList<Vertex> child){
        Evaluator evaluator = new Evaluator();
        ArrayList<Integer> positions = new ArrayList<>();
        Random rnd = new Random();
        int childCost = evaluator.evaluateSolution(child);

        for(int i=0; i<population.size(); i++){
            int cost = evaluator.evaluateSolution(population.get(i));
            if(cost > childCost){
                positions.add(i);
            }
        }
        if(positions.size() > 0){
            int n = positions.get(rnd.nextInt(positions.size()));
            population.set(n, child);
        }


    }

    public ArrayList<Vertex> generateSolution(){

        Random rnd = new Random();
        LocalSearch localSearch = new LocalSearch(vertexList, greedyAlg);
        Evaluator evaluator = new Evaluator();
        Recombination recombination = new Recombination(vertexList);

        ArrayList<ArrayList<Vertex>> population = generateStartPopulation();

        long startTime = System.currentTimeMillis();
        do{
            ArrayList<Vertex> parent1 = population.get(rnd.nextInt(Const.POPULATION_SIZE));
            ArrayList<Vertex> parent2 = population.get(rnd.nextInt(Const.POPULATION_SIZE));
            ArrayList<Vertex> child = recombination.recombine(parent1, parent2);

            localSearch.generateSolution(child);
            selectPopulation(population, child);


        }while((System.currentTimeMillis()-startTime)<Const.TIME_MS);

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




}
