package TO.EA;

import TO.EA.parameters.MaxCommonSequenceParameter;
import TO.EA.parameters.SolutionLengthParameter;
import TO.EA.recombination.CommonSequenceFinder;
import TO.Model.Vertex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CommonSequenceFinderTest {

    private MaxCommonSequenceParameter maxCommonSequenceParameter;
    private SolutionLengthParameter solutionLengthParameter;

    private CommonSequenceFinder finder;

    public CommonSequenceFinderTest(){
        maxCommonSequenceParameter = mock(MaxCommonSequenceParameter.class);
        solutionLengthParameter = mock(SolutionLengthParameter.class);
    }

    @Test
    public void findCommonSequence_commonSequencesAreInTheMiddle_shouldReturnCommonSequences(){
        List<Vertex> parent1 = Arrays.asList(
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        );
        List<Vertex> parent2 = Arrays.asList(
                new Vertex(5),
                new Vertex(2),
                new Vertex(3),
                new Vertex(8),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        );

        Mockito.when(maxCommonSequenceParameter.getMaxCommonSequenceValue()).thenReturn(3);
        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(7);
        CommonSequenceFinder finder = new CommonSequenceFinder(maxCommonSequenceParameter, solutionLengthParameter);

        ArrayList<ArrayList<Vertex>> sequences = finder.findCommonSequences(new ArrayList<>(parent1), new ArrayList<>(parent2));

        assertThat(sequences).hasSize(2);
//        assertThat(sequences)
//                .extracting(list -> list.get(0))
//                .hasSize(2);
//        assertThat(sequences)
//                .extracting(list -> list.get(1))
//                .hasSize(3);

    }

    @Test
    public void findCommonSequence_commonSequenceIsOnEdge_shouldReturnCommonSequence(){

        List<Vertex> parent1 = Arrays.asList(
                new Vertex(3),
                new Vertex(4),
                new Vertex(10),
                new Vertex(11),
                new Vertex(12),
                new Vertex(1),
                new Vertex(2)
        );
        List<Vertex> parent2 = Arrays.asList(
                new Vertex(3),
                new Vertex(4),
                new Vertex(4),
                new Vertex(3),
                new Vertex(2),
                new Vertex(1),
                new Vertex(2)
        );

        Mockito.when(maxCommonSequenceParameter.getMaxCommonSequenceValue()).thenReturn(4);
        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(7);
        CommonSequenceFinder finder = new CommonSequenceFinder(maxCommonSequenceParameter, solutionLengthParameter);
        ArrayList<ArrayList<Vertex>> sequences = finder.findCommonSequences(new ArrayList<>(parent1), new ArrayList<>(parent2));

        assertThat(sequences).hasSize(1);
        assertThat(sequences.get(0))
                .satisfies(list -> assertThat(list).hasSize(4));

    }


}
