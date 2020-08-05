package TO.Model;

public class Edge
{
	public int id;
	public int cost;
	
	public Edge(int id, int cost)
	{
		this.id = id;
		this.cost = cost;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getCost()
	{
		return cost;
	}
	//////////////////////////////////////////////////////
		
	@Override
	public boolean equals(Object object)
	{
		Edge obj = (Edge) object;
		if(obj.id == this.id)
			return true;
		else
			return false;
	}

}
