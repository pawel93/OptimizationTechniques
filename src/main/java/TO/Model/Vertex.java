package TO.Model;


import java.util.ArrayList;

public class Vertex
{
    public int id;
    public ArrayList<Edge> edges;

    public Vertex(int id)
    {
        this.id = id;
    }


    public void setEdges(ArrayList<Edge> edges)
    {
        this.edges = edges;
    }

    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

    public int getId()
    {
        return id;
    }

    /////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object object)
    {
        Vertex obj = (Vertex) object;
        if(obj.id == id)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
