package TO.EA.selection;

import TO.EA.selection.PopulationSelectionStrategy;
import TO.Model.Vertex;
import TO.Util.Evaluator;

import java.util.ArrayList;
import java.util.Random;

public class SelectionStrategyWithRandomWorstSubject implements PopulationSelectionStrategy {

    private Evaluator evaluator;

    public SelectionStrategyWithRandomWorstSubject(){
        this.evaluator = new Evaluator();
    }

    @Override
    public void selectNextPopulation(ArrayList<ArrayList<Vertex>> population, ArrayList<Vertex> child) {

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
}
