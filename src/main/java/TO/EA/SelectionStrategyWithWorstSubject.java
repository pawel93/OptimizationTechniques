package TO.EA;

import TO.Model.Vertex;
import TO.Util.Evaluator;

import java.util.ArrayList;

public class SelectionStrategyWithWorstSubject implements PopulationSelectionStrategy {

    private Evaluator evaluator;
    private int worstSolutionPosition;
    private int maxCost;

    public SelectionStrategyWithWorstSubject(){
        this.evaluator = new Evaluator();
    }

    private void evaluateChild(ArrayList<ArrayList<Vertex>> population, ArrayList<Vertex> child){
        int childCost = evaluator.evaluateSolution(child);
        if(childCost < maxCost){
            population.set(worstSolutionPosition, child);
        }
    }

    public void selectNextPopulation(ArrayList<ArrayList<Vertex>> population, ArrayList<Vertex> child){
        worstSolutionPosition = 0;
        maxCost = evaluator.evaluateSolution(population.get(worstSolutionPosition));
        for(int i=0; i<population.size(); i++){
            int cost = evaluator.evaluateSolution(population.get(i));
            if(cost > maxCost){
                maxCost = cost;
                worstSolutionPosition = i;
            }
        }
        evaluateChild(population, child);
    }
}
