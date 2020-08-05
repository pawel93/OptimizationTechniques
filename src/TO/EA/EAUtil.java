package TO.EA;

import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.BitSet;

public class EAUtil {

    public boolean validateSequence(BitSet index, int start, int end){
        boolean result = false;
        int i = start;
        while(i != end){
            result = result || index.get(i);
            i = (i+1)%Const.SOLUTION_LENGTH;
        }

        return !result;
    }

    public void setIndices(BitSet indices, int start, int end){
        int i = start;
        while(i != end){
            indices.set(i);
            i = (i+1)%Const.SOLUTION_LENGTH;
        }

    }

    public ArrayList<Vertex> createSubList(ArrayList<Vertex> parent, int start, int end){
        ArrayList<Vertex> sub = new ArrayList<>();

        int i = start;
        while(i != end){
            sub.add(parent.get(i));
            i = (i+1)%Const.SOLUTION_LENGTH;
        }
        return sub;
    }

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
