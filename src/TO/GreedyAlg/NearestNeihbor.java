package TO.GreedyAlg;

import TO.Util.Algorithm;
import TO.Util.Experiment;
import TO.Model.Vertex;
import TO.Util.Report;

import java.util.ArrayList;

public class NearestNeihbor implements Algorithm, Experiment {

    private static final int SOLUTION_LENGTH = 50;
    private ArrayList<Vertex> vertexList;

    public NearestNeihbor(ArrayList<Vertex> vertexList){
        this.vertexList = vertexList;
    }

    @Override
    public void generateReport(Report report) {
        report.createReport(this);
    }

    public ArrayList<Vertex> generateSolution(int startId){

        GUtil util = new GUtil();
        ArrayList<Vertex> result = new ArrayList<>();
        Vertex start = vertexList.get(startId);
        result.add(start);

        int i = 0;
        while(i < SOLUTION_LENGTH-1){
            int closestId = util.findClosestVertex(result);
            Vertex vertex = vertexList.get(closestId);
            result.add(vertex);
            i += 1;
        }

        return result;

    }



}







