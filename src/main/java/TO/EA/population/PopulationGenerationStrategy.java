package TO.EA.population;

import TO.Model.Vertex;

import java.util.ArrayList;

public interface PopulationGenerationStrategy {

    ArrayList<ArrayList<Vertex>> generatePopulation();
}
