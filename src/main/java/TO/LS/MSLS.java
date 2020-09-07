package TO.LS;

import TO.Util.Algorithm;
import TO.Util.Evaluator;
import TO.Util.Experiment;
import TO.Model.Vertex;
import TO.Util.Report;

import java.util.ArrayList;
import java.util.Random;

public class MSLS implements Algorithm, Experiment {

    private static final int NUM_ITER = 100;

    private ArrayList<Vertex> vertexList;
    private Algorithm greedyAlg;

    public MSLS(ArrayList<Vertex> vertexList, Algorithm greedyAlg){
        this.vertexList = vertexList;
        this.greedyAlg = greedyAlg;
    }

    @Override
    public void generateReport(Report report) {
        report.createReport(this);
    }

    public ArrayList<Vertex> generateSolution(int startId){

        Random rnd = new Random();
        LocalSearch localSearch = new LocalSearch(vertexList, greedyAlg);
        Evaluator evaluator = new Evaluator();

        ArrayList<Vertex> result = localSearch.generateSolution(startId);
        int cost = evaluator.evaluateSolution(result);

        for(int i = 0; i<NUM_ITER; i++){
            ArrayList<Vertex> nextResult = localSearch.generateSolution(rnd.nextInt(99));
            int nextCost = evaluator.evaluateSolution(nextResult);

            if(nextCost < cost){
                result = nextResult;
                cost = nextCost;
            }
        }

        return result;

    }


}
