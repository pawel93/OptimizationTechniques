package TO.GreedyAlg;


import TO.Model.Edge;
import TO.Util.Evaluator;
import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

final class Step {
    private final int position;
    private final int cost;
    private final Vertex vertex;
    public Step(int position, int cost, Vertex vertex){
        this.position = position;
        this.cost = cost;
        this.vertex = vertex;
    }

    public int getPosition() {
        return position;
    }

    public int getCost() {
        return cost;
    }

    public Vertex getVertex(){
        return vertex;
    }
}


public class GUtil {

    public boolean validateVertex(ArrayList<Vertex> result, int id){
        for(Vertex vertex : result){
            if(vertex.id == id){
                return false;
            }
        }
        return true;
    }

    public int findClosestVertex(ArrayList<Vertex> result){
        Vertex last = result.get(result.size()-1);
        ArrayList<Edge> edges = last.edges;

        int lowestCost = Integer.MAX_VALUE;
        int closest = 0;

        for(Edge edge : edges){
            if(edge.cost < lowestCost && validateVertex(result, edge.id)){
                lowestCost = edge.cost;
                closest = edge.id;
            }
        }

        return closest;
    }

    public ArrayList<Integer> createCandidateList(ArrayList<Vertex> result, ArrayList<Edge> edges){
        ArrayList<Integer> candidates = new ArrayList<>();
        for(int i=0; i<edges.size() && candidates.size()<3; i++){

            if(validateVertex(result, edges.get(i).id)){
                candidates.add(edges.get(i).id);
            }

        }
        return candidates;

    }


    public int findRandomClosestVertex(ArrayList<Vertex> result, int n){
        Vertex last = result.get(result.size()-1);
        ArrayList<Edge> edges = last.edges;
        edges.sort(Comparator.comparing(edge -> edge.cost));

        ArrayList<Integer> candidates = new ArrayList<>();
        for(int i=0; i<edges.size() && candidates.size()<3; i++){

            if(validateVertex(result, edges.get(i).id)){
                candidates.add(edges.get(i).id);
            }

        }
        Random rnd = new Random();
        return candidates.get(rnd.nextInt(n));

    }


    public Step findBestPositionForVertex(ArrayList<Vertex> result, Vertex vertex){
        Evaluator evaluator = new Evaluator();
        int i = 1;
        int minCost = Integer.MAX_VALUE;
        int position = 0;
        while(i <= result.size()-1){
            result.add(i, vertex);
            int cost = evaluator.evaluateSolution(result);
            if(cost < minCost){
                position = i;
                minCost = cost;
            }
            result.remove(i);
            i += 1;
        }
        return new Step(position, minCost, vertex);
    }




}
