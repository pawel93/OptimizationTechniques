package TO.GreedyAlg;

import TO.Util.Algorithm;
import TO.Util.Experiment;
import TO.Model.Vertex;
import TO.Util.Report;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GraspGC implements Algorithm, Experiment {

    private static final int SOLUTION_LENGTH = 50;
    private ArrayList<Vertex> vertexList;

    public GraspGC(ArrayList<Vertex> vertexList){
        this.vertexList = vertexList;
    }

    @Override
    public void generateReport(Report report) {
        report.createReport(this);
    }

    public ArrayList<Vertex> generateSolution(int startId){

        GUtil util = new GUtil();
        ArrayList<Vertex> result = new ArrayList<>();
        Vertex startVertex = vertexList.get(startId);
        result.add(startVertex);

        int stopId = util.findRandomClosestVertex(result, 3);
        Vertex endVertex = vertexList.get(stopId);
        result.add(endVertex);

        int i = 0;
        while(i < SOLUTION_LENGTH-2){

            ArrayList<Step> candidates = new ArrayList<>();
            for(Vertex vertex : vertexList){
                if(util.validateVertex(result, vertex.id)){
                    Step step = util.findBestPositionForVertex(result, vertex);
                    candidates.add(step);
                }
            }
            candidates.sort(Comparator.comparing(Step::getCost));
            Random rnd = new Random();
            int n = rnd.nextInt(3);
            Step drawn = candidates.get(n);

            result.add(drawn.getPosition(), drawn.getVertex());
            i += 1;
        }
        return result;


    }






}
