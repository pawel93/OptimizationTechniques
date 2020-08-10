package TO.EA.recombination;

import TO.EA.parameters.Const;
import TO.EA.parameters.MaxCommonSequenceParameter;
import TO.EA.parameters.SolutionLengthParameter;
import TO.EA.util.RecombinationUtil;
import TO.Model.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Recombination {

    private RecombinationUtil util;
    private ArrayList<Vertex> vertexList;
    private CommonSequenceFinder finder;
    private int SOLUTION_LENGTH;

    public Recombination(ArrayList<Vertex> vertexList,
                         CommonSequenceFinder commonSequenceFinder,
                         SolutionLengthParameter solutionLengthParameter){
        this.util = new RecombinationUtil();
        this.vertexList = vertexList;
        this.finder = commonSequenceFinder;
        this.SOLUTION_LENGTH = solutionLengthParameter.getSolutionLengthValue();
    }

    public void padWithRandomVertices(ArrayList<ArrayList<Vertex>> sequences){
        int missing = SOLUTION_LENGTH - util.findLength(sequences);
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

        ArrayList<ArrayList<Vertex>> seqences = finder.findCommonSequences(parent1, parent2);
        padWithRandomVertices(seqences);
        Collections.shuffle(seqences);

        ArrayList<Vertex> child = new ArrayList<>();
        for(ArrayList<Vertex> seq: seqences){
            child.addAll(seq);
        }
        return child;

    }



}
