package TO.EA.selection;

import TO.Model.Vertex;

import java.util.ArrayList;

public interface PopulationSelectionStrategy {

    void selectNextPopulation(ArrayList<ArrayList<Vertex>> population, ArrayList<Vertex> child);
}
