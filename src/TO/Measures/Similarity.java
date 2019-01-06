package TO.Measures;

import TO.Model.Vertex;

import java.util.ArrayList;

public class Similarity {


    public int compareVertices(ArrayList<Vertex> first, ArrayList<Vertex> seconds)
    {
        int counter =0;
        for(int i=0; i<first.size(); i++)
        {
            int id = first.get(i).getId();
            for(int j =0; j<seconds.size(); j++)
            {
                if(seconds.get(j).getId() == id)
                    counter++;
            }
        }
        return counter;


    }

    public int compareEdges(ArrayList<Vertex> first, ArrayList<Vertex> seconds)
    {
        int counter =0;
        for(int i=0; i<first.size(); i++)
        {
            int id = first.get(i).getId();
            int id1 = first.get((i+1)%50).getId();
            for(int j =0; j<seconds.size(); j++)
            {
                if(seconds.get(j).getId() == id && seconds.get((j+1)%50).getId() == id1)
                    counter++;
            }
        }
        return counter;
    }




}
