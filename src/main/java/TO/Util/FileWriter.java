package TO.Util;

import java.io.*;
import java.util.ArrayList;

class Coordinates{
	int x;
	int y;
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

public class FileWriter {

	
	
	public static ArrayList<Coordinates> readVertexCoordinates()throws IOException
	{
		
		ArrayList<Coordinates> cor = new ArrayList<>();
		int counter = 0;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("data/kroA100.tsp"));
			String line;
			while((line = br.readLine()) != null)
			{
				counter++;
				if(counter >= 7 && counter < 107)
				{
					String[] tab = line.split(" ");
					Coordinates c = new Coordinates( Integer.valueOf(tab[1]), Integer.valueOf(tab[2]));
					cor.add(c);
				}
					
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return cor;
				
	}
	
	public static void exportToFile(String solution, String fileName)throws IOException
	{
		ArrayList<Coordinates> cor = readVertexCoordinates();
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");

		String[] tab = solution.split(", ");
		
		for(int i = 0; i<50; i++)
		{
			int num = Integer.valueOf(tab[i]);
			Coordinates c = cor.get(num);
			writer.println(num + " " + c.x + " " + c.y);
		}
		writer.close();
	}
	
	

}
