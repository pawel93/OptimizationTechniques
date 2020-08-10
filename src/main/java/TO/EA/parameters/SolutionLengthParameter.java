package TO.EA.parameters;

public class SolutionLengthParameter {

    private final int solutionLengthValue;

    public SolutionLengthParameter(int solutionLengthValue){
        this.solutionLengthValue = solutionLengthValue;
    }

    public SolutionLengthParameter(){
        this.solutionLengthValue = Const.SOLUTION_LENGTH;
    }

    public int getSolutionLengthValue(){
        return solutionLengthValue;
    }

}
