package TO.LS;

import TO.Util.Algorithm;
import TO.Util.Evaluator;
import TO.Util.Experiment;
import TO.Model.Vertex;
import TO.Util.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class IteratedLS implements Algorithm, Experiment {

    private static final int TIME_MS = 50000;

    private ArrayList<Vertex> vertexList;
    private Algorithm greedyAlg;

    public IteratedLS(ArrayList<Vertex> vertexList, Algorithm greedyAlg){
        this.vertexList = vertexList;
        this.greedyAlg = greedyAlg;
    }

    @Override
    public void generateReport(Report report) {
        report.createReport(this);
    }

    public ArrayList<Vertex> filter(ArrayList<Vertex> result){
        ArrayList<Vertex> filtered = new ArrayList<>();

        for(Vertex vertex : vertexList){
            if(!result.contains(vertex)){
                filtered.add(vertex);
            }
        }

        return filtered;
    }

    public void perturbateSolution(ArrayList<Vertex> result){
        Random random = new Random();
        for(int i=0; i<2; i++){
            int position = random.nextInt(50);
            List<Vertex> filtered = vertexList.stream().filter(p -> !result.contains(p)).collect(Collectors.toList());
            result.set(position, filtered.get(random.nextInt(50)));

        }

    }

    public ArrayList<Vertex> generateSolution(int startId){

        LocalSearch localSearch = new LocalSearch(vertexList, greedyAlg);
        Evaluator evaluator = new Evaluator();

        ArrayList<Vertex> result = localSearch.generateSolution(startId);
        ArrayList<Vertex> nextResult = new ArrayList<>(result);
        int currentCost = evaluator.evaluateSolution(result);

        long startTime = System.currentTimeMillis();
        do{
            perturbateSolution(nextResult);
            localSearch.generateSolution(nextResult);
            int nextCost = evaluator.evaluateSolution(nextResult);

            if(nextCost < currentCost){
                currentCost = nextCost;
                result = new ArrayList<>(nextResult);
            }

        }while((System.currentTimeMillis() - startTime) < TIME_MS);

        return result;

    }



}
