package TO.EA.util;

import TO.Model.Vertex;

import java.util.ArrayList;

public class RecombinationUtil {

    public int findLength(ArrayList<ArrayList<Vertex>> sequences){
        int size = 0;
        for(ArrayList<Vertex> seq: sequences){
            size += seq.size();
        }
        return size;
    }

    public boolean checkVertex(ArrayList<ArrayList<Vertex>> sequences, Vertex vertex){
        boolean result = true;
        for(ArrayList<Vertex> seq: sequences){
            result &= !seq.contains(vertex);
        }
        return result;
    }

    public ArrayList<Vertex> filterVertexList(ArrayList<ArrayList<Vertex>> sequences, ArrayList<Vertex> vertexList){
        ArrayList<Vertex> filtered = new ArrayList<>();

        for(Vertex vertex: vertexList){
            if(checkVertex(sequences, vertex)){
                filtered.add(vertex);
            }
        }
        return filtered;
    }

}
