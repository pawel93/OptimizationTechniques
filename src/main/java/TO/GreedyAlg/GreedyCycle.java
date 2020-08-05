package TO.GreedyAlg;

import TO.Util.Algorithm;
import TO.Util.Experiment;
import TO.Model.Vertex;
import TO.Util.Report;

import java.util.ArrayList;



public class GreedyCycle implements Algorithm, Experiment {

    private static final int SOLUTION_LENGTH = 50;
    private ArrayList<Vertex> vertexList;

    public GreedyCycle(ArrayList<Vertex> vertexList){
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

        int stopId = util.findClosestVertex(result);
        Vertex endVertex = vertexList.get(stopId);
        result.add(endVertex);

        int i = 0;
        while(i < SOLUTION_LENGTH-2){

            int minCost = Integer.MAX_VALUE;
            int position = 0;
            Vertex vertexToInsert = null;
            for(Vertex vertex : vertexList){
                if(util.validateVertex(result, vertex.id)){

                    Step step = util.findBestPositionForVertex(result, vertex);
                    if(step.getCost() < minCost){
                        minCost = step.getCost();
                        position = step.getPosition();
                        vertexToInsert = vertex;
                    }

                }
            }
            result.add(position, vertexToInsert);
            i += 1;
        }
        return result;

    }


}

