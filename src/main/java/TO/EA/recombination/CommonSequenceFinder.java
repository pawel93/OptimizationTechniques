package TO.EA.recombination;

import TO.EA.parameters.MaxCommonSequenceParameter;
import TO.EA.parameters.SolutionLengthParameter;
import TO.EA.util.CSFUtil;
import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.BitSet;

public class CommonSequenceFinder {

    private CSFUtil util;
    private final int SOLUTION_LENGTH;
    private final int MAX_COMMON_SEQUENCE;

    public CommonSequenceFinder(MaxCommonSequenceParameter maxCommonSequence, SolutionLengthParameter solutionLength){
        this.util = new CSFUtil(solutionLength);
        this.MAX_COMMON_SEQUENCE = maxCommonSequence.getMaxCommonSequenceValue();
        this.SOLUTION_LENGTH = solutionLength.getSolutionLengthValue();
    }

    public boolean compare(ArrayList<Vertex> parent, BitSet index, ArrayList<Vertex> sub, int start){
        int k = start;
        boolean result = true;
        for(int j=0; j<sub.size(); j++){

            if(parent.get(k).equals(sub.get(j)) && !index.get(k))
                result &= true;
            else
                result &= false;
            k = (k+1)%SOLUTION_LENGTH;
        }
        return result;
    }

    public boolean solutionContainsSequence(ArrayList<Vertex> parent, BitSet index, ArrayList<Vertex> sub){


        for(int i=0; i<parent.size(); i++){
            boolean result = compare(parent, index, sub, i);
            if(result){
                util.setIndices(index, i, (i+sub.size())%SOLUTION_LENGTH);
                return true;
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Vertex>> findCommonSequences(ArrayList<Vertex> parent1, ArrayList<Vertex> parent2){
        BitSet index1 = new BitSet(SOLUTION_LENGTH);
        BitSet index2 = new BitSet(SOLUTION_LENGTH);
        ArrayList<ArrayList<Vertex>> commonSequences = new ArrayList<>();

        for(int i=MAX_COMMON_SEQUENCE; i>0; i--){

            for(int j=0; j<parent1.size(); j++){

                if(util.validateSequence(index1, j, (j+i)%SOLUTION_LENGTH) ){

                    ArrayList<Vertex> sub = util.createSubList(parent1, j, (j+i)%SOLUTION_LENGTH);
                    if(solutionContainsSequence(parent2, index2, sub)){
                        commonSequences.add(sub);
                        util.setIndices(index1, j, (j+i)%SOLUTION_LENGTH);
                    }
                }
            }

        }
        return commonSequences;
    }
}
