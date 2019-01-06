package TO.Util;

import TO.Model.Edge;
import TO.Model.Vertex;

import java.util.ArrayList;

public class Evaluator
{

	public int calculateCost(Vertex vertexStart, Vertex vertexEnd)
	{
		int end = vertexEnd.getId();
		ArrayList<Edge> edges = vertexStart.edges;
		for(Edge edge : edges)
		{
			if(edge.getId() == end)
				return edge.getCost();
		}
		return 0;
	}

	public int evaluateSolution(ArrayList<Vertex> cycle1)
	{
		int size = cycle1.size();
		int cost = 0;
		for(int i=0; i<cycle1.size()-1; i++)
		{
			cost += calculateCost(cycle1.get(i), cycle1.get(i+1));
		}
		cost += calculateCost(cycle1.get(size-1), cycle1.get(0));

		return cost;
	}

}
