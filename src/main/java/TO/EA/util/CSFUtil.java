package TO.EA.util;

import TO.EA.parameters.SolutionLengthParameter;
import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.BitSet;

public class CSFUtil {

    private int SOLUTION_LENGTH;

    public CSFUtil(SolutionLengthParameter solutionLength){
        this.SOLUTION_LENGTH = solutionLength.getSolutionLengthValue();
    }

    public boolean validateSequence(BitSet index, int start, int end){
        boolean result = false;
        int i = start;
        while(i != end){
            result = result || index.get(i);
            i = (i+1)%SOLUTION_LENGTH;
        }

        return !result;
    }

    public void setIndices(BitSet indices, int start, int end){
        int i = start;
        while(i != end){
            indices.set(i);
            i = (i+1)%SOLUTION_LENGTH;
        }

    }

    public ArrayList<Vertex> createSubList(ArrayList<Vertex> parent, int start, int end){
        ArrayList<Vertex> sub = new ArrayList<>();

        int i = start;
        while(i != end){
            sub.add(parent.get(i));
            i = (i+1)%SOLUTION_LENGTH;
        }
        return sub;
    }

}
