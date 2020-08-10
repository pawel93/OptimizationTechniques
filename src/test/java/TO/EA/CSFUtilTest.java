package TO.EA;

import TO.EA.parameters.SolutionLengthParameter;
import TO.EA.util.CSFUtil;
import TO.Model.Vertex;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

public class CSFUtilTest {

    SolutionLengthParameter solutionLengthParameter;

    public CSFUtilTest(){
        solutionLengthParameter = mock(SolutionLengthParameter.class);
    }

    @Test
    public void validateSequence_selectedSequenceIsInTheMiddleAndIsUnused_shoudReturnTrue(){
        BitSet bitSet = new BitSet(10);
        int start = 2;
        int end = 7;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        boolean response = util.validateSequence(bitSet, start, end);

        assertThat(response).isEqualTo(true);
    }

    @Test
    public void validateSequence_selectedSequenceIsOnEdgesAndIsUnused_shouldReturnTrue(){
        BitSet bitSet = new BitSet(10);
        int start = 8;
        int end = 3;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        boolean response = util.validateSequence(bitSet, start, end);

        assertThat(response).isEqualTo(true);

    }

    @Test
    public void validateSequence_selectedSequenceIsInTheMiddleAndIsUsed_shouldReturnFalse(){
        BitSet bitSet = new BitSet(10);
        bitSet.set(3);
        int start = 2;
        int end = 7;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        boolean response = util.validateSequence(bitSet, start, end);

        assertThat(response).isEqualTo(false);

    }

    @Test
    public void validateSequence_selectedSequenceIsOnEdgesAndIsUsed_shouldReturnFalse(){
        BitSet bitSet = new BitSet(10);
        bitSet.set(9);
        int start = 8;
        int end = 3;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        boolean response = util.validateSequence(bitSet, start, end);

        assertThat(response).isEqualTo(false);

    }

    @Test
    public void setIndices_selectedIndicesAreInTheMiddle_shouldSetIndices(){
        BitSet bitSet = new BitSet(10);
        int start = 2;
        int end = 5;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        util.setIndices(bitSet, start, end);

        assertThat(bitSet.get(1)).isFalse();
        assertThat(bitSet.get(2)).isTrue();
        assertThat(bitSet.get(3)).isTrue();
        assertThat(bitSet.get(4)).isTrue();
        assertThat(bitSet.get(5)).isFalse();
    }

    @Test
    public void setIndices_selectedIndicesAreOnTheEdges_shouldSetIndices(){
        BitSet bitSet = new BitSet(10);
        int start = 8;
        int end = 2;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        util.setIndices(bitSet, start, end);

        assertThat(bitSet.get(7)).isFalse();
        assertThat(bitSet.get(8)).isTrue();
        assertThat(bitSet.get(9)).isTrue();
        assertThat(bitSet.get(0)).isTrue();
        assertThat(bitSet.get(1)).isTrue();
        assertThat(bitSet.get(2)).isFalse();

    }

    @Test
    public void createSubList_subListIsInTheMiddle_shouldReturnSubList(){
        List<Vertex> vertices = Arrays.asList(
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6));
        int start = 2;
        int end = 5;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(6);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        ArrayList<Vertex> response = util.createSubList(new ArrayList<>(vertices), start, end);

        assertThat(response).hasSize(3);
    }

    @Test
    public void createSubList_subListIsOnEdges_shouldReturnSubList(){
        List<Vertex> vertices = Arrays.asList(
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6));
        int start = 5;
        int end = 2;

        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(6);
        CSFUtil util = new CSFUtil(solutionLengthParameter);
        ArrayList<Vertex> response = util.createSubList(new ArrayList<>(vertices), start, end);

        assertThat(response).hasSize(3);

    }

}
