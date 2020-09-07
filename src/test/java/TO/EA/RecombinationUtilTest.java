package TO.EA;

import TO.EA.util.RecombinationUtil;
import TO.Model.Vertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RecombinationUtilTest {

    @Test
    public void findLength_shouldReturnLengthOfCommonSequences(){

        List<ArrayList<Vertex>> sequences = Arrays.asList(
                new ArrayList<>(Arrays.asList(new Vertex(1), new Vertex(3))),
                new ArrayList<>(Arrays.asList(new Vertex(5), new Vertex(7), new Vertex(8))),
                new ArrayList<>(Collections.singletonList(new Vertex(1)))
        );
        RecombinationUtil util = new RecombinationUtil();
        int response = util.findLength(new ArrayList<>(sequences));

        assertThat(response).isEqualTo(6);

    }

    @Test
    public void checkVertex_sequencesContainVertex_shouldReturnFalse(){
        List<ArrayList<Vertex>> sequences = Arrays.asList(
                new ArrayList<>(Arrays.asList(new Vertex(1), new Vertex(3))),
                new ArrayList<>(Arrays.asList(new Vertex(5), new Vertex(7), new Vertex(8))),
                new ArrayList<>(Collections.singletonList(new Vertex(1)))
        );
        Vertex vertex = new Vertex(5);

        RecombinationUtil util = new RecombinationUtil();
        boolean response = util.checkVertex(new ArrayList<>(sequences), vertex);

        assertThat(response).isEqualTo(false);

    }

    @Test
    public void checkVertex_sequencesDoNotContainVertex_shouldReturnTrue(){
        List<ArrayList<Vertex>> sequences = Arrays.asList(
                new ArrayList<>(Arrays.asList(new Vertex(1), new Vertex(3))),
                new ArrayList<>(Arrays.asList(new Vertex(5), new Vertex(7), new Vertex(8))),
                new ArrayList<>(Collections.singletonList(new Vertex(1)))
        );
        Vertex vertex = new Vertex(11);

        RecombinationUtil util = new RecombinationUtil();
        boolean response = util.checkVertex(new ArrayList<>(sequences), vertex);

        assertThat(response).isEqualTo(true);

    }

    @Test
    public void fileterVertexList_shouldReturnVerticesThatAreNotIncludedInCommonSequences(){
        List<ArrayList<Vertex>> sequences = Arrays.asList(
                new ArrayList<>(Arrays.asList(new Vertex(1), new Vertex(3))),
                new ArrayList<>(Arrays.asList(new Vertex(5), new Vertex(7), new Vertex(8))),
                new ArrayList<>(Collections.singletonList(new Vertex(1)))
        );
        List<Vertex> vertices = Arrays.asList(new Vertex(2), new Vertex(3), new Vertex(5), new Vertex(9));

        RecombinationUtil util = new RecombinationUtil();
        ArrayList<Vertex> response = util.filterVertexList(new ArrayList<>(sequences), new ArrayList<>(vertices));

        assertThat(response).hasSize(2);

    }


}
