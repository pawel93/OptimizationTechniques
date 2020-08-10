package TO.EA;

import TO.EA.parameters.SolutionLengthParameter;
import TO.EA.recombination.CommonSequenceFinder;
import TO.EA.recombination.Recombination;
import TO.Model.Vertex;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class RecombinationTest {

    private SolutionLengthParameter solutionLengthParameter;
    private CommonSequenceFinder commonSequenceFinder;

    public RecombinationTest(){
        solutionLengthParameter = mock(SolutionLengthParameter.class);
        commonSequenceFinder = mock(CommonSequenceFinder.class);
    }

    @Test
    public void padWithRandomVertices_shouldPadCommonSequences(){

        ArrayList<ArrayList<Vertex>> sequences = new ArrayList<>(

                Arrays.asList(
                        new ArrayList<>(Arrays.asList(new Vertex(1), new Vertex(3))),
                        new ArrayList<>(Arrays.asList(new Vertex(5), new Vertex(7), new Vertex(8))),
                        new ArrayList<>(Collections.singletonList(new Vertex(9)))
                )
        );

        List<Vertex> vertices = Arrays.asList(
                new Vertex(1),
                new Vertex(3),
                new Vertex(5),
                new Vertex(7),
                new Vertex(8),
                new Vertex(9),
                new Vertex(10),
                new Vertex(12),
                new Vertex(14),
                new Vertex(17)
        );
        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);
        Recombination recombination = new Recombination(new ArrayList<>(vertices), commonSequenceFinder, solutionLengthParameter);
        recombination.padWithRandomVertices(sequences);

        assertThat(sequences).hasSize(7);

    }

    @Test
    public void recombine_shouldRecombineTwoParents(){
        List<Vertex> parent1 = Arrays.asList(
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(10),
                new Vertex(12),
                new Vertex(16),
                new Vertex(14),
                new Vertex(5),
                new Vertex(7),
                new Vertex(11)
        );

        List<Vertex> parent2 = Arrays.asList(
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(21),
                new Vertex(19),
                new Vertex(12),
                new Vertex(15),
                new Vertex(5),
                new Vertex(7),
                new Vertex(16)
        );
        List<Vertex> vertices = Arrays.asList(
                new Vertex(11),
                new Vertex(12),
                new Vertex(13),
                new Vertex(14),
                new Vertex(15),
                new Vertex(16)
        );

        ArrayList<ArrayList<Vertex>> sequences = new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList(new Vertex(5), new Vertex(7))),
                        new ArrayList<>(Arrays.asList(new Vertex(1), new Vertex(2), new Vertex(3)))
                )
        );

        Mockito.when(commonSequenceFinder.findCommonSequences(new ArrayList<>(parent1), new ArrayList<>(parent2))).thenReturn(sequences);
        Mockito.when(solutionLengthParameter.getSolutionLengthValue()).thenReturn(10);

        Recombination recombination = new Recombination(new ArrayList<>(vertices), commonSequenceFinder, solutionLengthParameter);
        ArrayList<Vertex> response = recombination.recombine(new ArrayList<>(parent1), new ArrayList<>(parent2));

        assertThat(response).hasSize(10);
        assertThat(response).containsAll(sequences.get(0));
        assertThat(response).containsAll(sequences.get(1));


    }


}
