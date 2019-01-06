package TO.EA;

import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Random;

public class Recombination {

    private EAUtil util;
    private ArrayList<Vertex> vertexList;

    public Recombination(ArrayList<Vertex> vertexList){
        this.util = new EAUtil();
        this.vertexList = vertexList;
    }

    public boolean compare(ArrayList<Vertex> parent, BitSet index, ArrayList<Vertex> sub, int start){
        int k = start;
        boolean result = true;
        for(int j=0; j<sub.size(); j++){

            if(parent.get(k).equals(sub.get(j)) && !index.get(k))
                result &= true;
            else
                result &= false;
            k = (k+1)%Const.SOLUTION_LENGTH;
        }
        return result;
    }

    public boolean solutionContainsSequence(ArrayList<Vertex> parent, BitSet index, ArrayList<Vertex> sub){


        for(int i=0; i<parent.size(); i++){
            boolean result = compare(parent, index, sub, i);
            if(result){
                util.setIndices(index, i, (i+sub.size())%Const.SOLUTION_LENGTH);
                return true;
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Vertex>> findCommonSequences(ArrayList<Vertex> parent1, ArrayList<Vertex> parent2){
        BitSet index1 = new BitSet(Const.SOLUTION_LENGTH);
        BitSet index2 = new BitSet(Const.SOLUTION_LENGTH);
        ArrayList<ArrayList<Vertex>> commonSequences = new ArrayList<>();

        for(int i=Const.MAX_SEQUENCE; i>0; i--){

            for(int j=0; j<parent1.size(); j++){

                if(util.validateSequence(index1, j, (j+i)%Const.SOLUTION_LENGTH) ){

                    ArrayList<Vertex> sub = util.createSubList(parent1, j, (j+i)%Const.SOLUTION_LENGTH);
                    if(solutionContainsSequence(parent2, index2, sub)){
                        commonSequences.add(sub);
                        util.setIndices(index1, j, (j+i)%Const.SOLUTION_LENGTH);
                    }
                }
            }

        }
        return commonSequences;
    }

    public void padWithRandomVertices(ArrayList<ArrayList<Vertex>> sequences){
        int missing = Const.SOLUTION_LENGTH - util.findLength(sequences);
        Random random = new Random();
        ArrayList<Vertex> filtered = util.filterVertexList(sequences, vertexList);

        for(int i=0; i<missing; i++){

            int n = random.nextInt(filtered.size());
            ArrayList<Vertex> arr = new ArrayList<>();
            arr.add(filtered.get(n));

            sequences.add(arr);
            filtered.remove(filtered.get(n));
        }

    }

    public ArrayList<Vertex> recombine(ArrayList<Vertex> parent1, ArrayList<Vertex> parent2){

        ArrayList<ArrayList<Vertex>> seqences = findCommonSequences(parent1, parent2);
        padWithRandomVertices(seqences);
        Collections.shuffle(seqences);
        ArrayList<Vertex> child = new ArrayList<>();
        for(ArrayList<Vertex> seq: seqences){
            child.addAll(seq);
        }
        return child;

    }



}
