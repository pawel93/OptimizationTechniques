package TO.LS;

import TO.Util.Algorithm;
import TO.Util.Evaluator;
import TO.Util.Experiment;
import TO.Model.Edge;
import TO.Model.Vertex;
import TO.Util.Report;

import java.util.ArrayList;

final class EdgeStep{

    private final int minCost;
    private final int positionStart;
    private final int positionEnd;
    public EdgeStep(int minCost, int positionStart, int positionEnd){
        this.minCost = minCost;
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
    }

    public int getCost() {
        return minCost;
    }

    public int getPositionStart() {
        return positionStart;
    }

    public int getPositionEnd() {
        return positionEnd;
    }
}

final class VertexStep{

    private final int position;
    private final int minCost;
    private final Vertex vertexToSwap;
    public VertexStep(int position, int minCost, Vertex vertexToSwap){
        this.position = position;
        this.minCost = minCost;
        this.vertexToSwap = vertexToSwap;
    }

    public int getPosition() {
        return position;
    }

    public int getCost() {
        return minCost;
    }

    public Vertex getVertex() {
        return vertexToSwap;
    }
}

public class LocalSearch implements Algorithm, Experiment {


    private ArrayList<Vertex> vertexList;
    private Algorithm greedyAlg;

    public LocalSearch(ArrayList<Vertex> vertexList, Algorithm greedyAlg){
        this.vertexList = vertexList;
        this.greedyAlg = greedyAlg;
    }

    @Override
    public void generateReport(Report report) {
        report.createReport(this);
    }

    public boolean validateVertex(ArrayList<Vertex> result, int id){
        for(Vertex vertex : result){
            if(vertex.id == id){
                return false;
            }
        }
        return true;
    }

    public int calculateCost(Vertex vertexStart, Vertex vertexEnd)
    {
        int end = vertexEnd.getId();
        ArrayList<Edge> edges = vertexStart.edges;
        for(Edge edge : edges)
        {
            if(edge.getId() == end)
                return edge.getCost();
        }
        return 0;
    }

    public void reverseArc(int start, int end, ArrayList<Vertex> result)
    {
        ArrayList<Vertex> subArc = new ArrayList<>();

        int arc = Math.abs(start - end)+1;

        for(int i = start; i< (start+arc); i++)
        {
            subArc.add( result.get(i%50) );
        }
        int N = start;
        //Collections.reverse(subArc);
        for(int i = subArc.size()-1; i>=0; i--)
        {
            result.set(N%50, subArc.get(i));
            N++;
        }
        //startResult.add(last);

    }

    public EdgeStep findEdgeToSwap(ArrayList<Vertex> result, int resultCost){

        int minCost = Integer.MAX_VALUE;
        int positionStart = 0;
        int positionEnd = 0;

        for(int i=0; i<result.size(); i++){

            Vertex vertex = result.get(i);

            Vertex next = result.get( (i+1)%50 );
            Vertex next1 = result.get( (i+2)%50 );
            Vertex next2 = result.get( (i+3)%50 );

            for(int k=0; k<result.size()-3; k++)
            {
                int removeCost = calculateCost(vertex, next) + calculateCost(next1, next2);
                int addCost = calculateCost(next, next2) + calculateCost(next1, vertex);
                int stepCost = resultCost - removeCost + addCost;

                if(stepCost < minCost)
                {
                    minCost = stepCost;
                    positionStart = (i+1)%50;
                    positionEnd = (i+2 + k)%50;
                }

                next1 = result.get( (i+3 + k)%50 );
                next2 = result.get( (i+4 +k)%50 );

            }

        }
        return new EdgeStep(minCost, positionStart, positionEnd);


    }

    public VertexStep findVertexToSwap(ArrayList<Vertex> result, int resultCost){

        int minCost = Integer.MAX_VALUE;
        int position = 0;
        Vertex vertexToSwap = null;
        for(int i = 0; i<result.size(); i++){

            Vertex previous = result.get((i+49)%50);
            Vertex vertex = result.get(i);
            Vertex next = result.get((i+1)%50);
            int deleteCost = calculateCost(vertex, previous) + calculateCost(vertex, next);

            for(Vertex swapVertex : vertexList){
                if(validateVertex(result, swapVertex.id)){

                    int addCost = calculateCost(swapVertex, previous) + calculateCost(swapVertex, next);
                    int stepCost = resultCost - deleteCost + addCost;
                    if(stepCost < minCost){
                        minCost = stepCost;
                        position = i;
                        vertexToSwap = swapVertex;
                    }

                }

            }

        }
        return new VertexStep(position, minCost, vertexToSwap);

    }

    public void generateSolution(ArrayList<Vertex> result){

        Evaluator evaluator = new Evaluator();
        int currentCost = evaluator.evaluateSolution(result);
        int nextCost = currentCost;

        do{
            currentCost = nextCost;
            VertexStep vertexStep = findVertexToSwap(result, currentCost);
            EdgeStep edgeStep = findEdgeToSwap(result, currentCost);

            if(vertexStep.getCost() < edgeStep.getCost()){

                if(vertexStep.getCost() < currentCost){
                    result.set(vertexStep.getPosition(), vertexStep.getVertex());
                    nextCost = vertexStep.getCost();
                }

            }else if(edgeStep.getCost() <= vertexStep.getCost()){

                if(edgeStep.getCost() < currentCost){
                    reverseArc(edgeStep.getPositionStart(), edgeStep.getPositionEnd(), result);
                    nextCost = edgeStep.getCost();
                }
            }


        }while(nextCost < currentCost);


    }


    public ArrayList<Vertex> generateSolution(int startId){

        ArrayList<Vertex> result = greedyAlg.generateSolution(startId);

        generateSolution(result);

        return result;

    }

}
