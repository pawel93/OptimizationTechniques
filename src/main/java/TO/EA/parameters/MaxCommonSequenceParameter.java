package TO.EA.parameters;

public class MaxCommonSequenceParameter {

    private final int maxCommonSequenceValue;

    public MaxCommonSequenceParameter(int maxCommonSequenceValue){
        this.maxCommonSequenceValue = maxCommonSequenceValue;
    }

    public MaxCommonSequenceParameter(){
        this.maxCommonSequenceValue = Const.MAX_SEQUENCE;
    }

    public int getMaxCommonSequenceValue(){
        return maxCommonSequenceValue;
    }
}
