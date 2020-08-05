package TO.Measures;

import TO.Util.Evaluator;
import TO.GreedyAlg.NearestNeihbor;
import TO.LS.LocalSearch;
import TO.Model.Vertex;
import TO.Util.XmlReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Comparison {

    private static final int NUM_SOLUTIONS = 1000;
    private static final int SOLUTION_LENGTH = 50;


    public Comparison(){

    }

    public void compareSolutions(ArrayList<ArrayList<Vertex>> solutions, ArrayList<Integer> costs)
    {
        ArrayList<Double> vertexSimilarity = new ArrayList<>();
        ArrayList<Double> edgeSimilarity = new ArrayList<>();

        Similarity similarity = new Similarity();
        int sumV = 0;
        int sumE = 0;
        for(int i=0; i<solutions.size(); i++)
        {
            ArrayList<Vertex> first = solutions.get(i);
            for(int j=0; j<solutions.size(); j++)
            {
                if(j != i)
                {
                    int temp = similarity.compareVertices(first, solutions.get(j));
                    int temp1 = similarity.compareEdges(first, solutions.get(j));
                    sumV += temp;
                    sumE += temp1;
                }

            }
            vertexSimilarity.add(sumV/999.0);
            edgeSimilarity.add(sumE/999.0);
            sumV = 0;
            sumE = 0;
        }
        writeDataToFile(costs, vertexSimilarity, "verticesSimilarity.txt");
        writeDataToFile(costs, edgeSimilarity, "edgesSimilarity.txt");

    }

    public void compareWithBestSolution(ArrayList<ArrayList<Vertex>> solutions, ArrayList<Integer> costs)
    {
        Similarity similarity = new Similarity();
        ArrayList<Double> vbestSimilarity = new ArrayList<>();
        ArrayList<Double> ebestSimilarity = new ArrayList<>();

        int bestCost = Collections.min(costs);
        int indexOfBest = costs.indexOf(bestCost);
        ArrayList<Vertex> bestSolution = solutions.get(indexOfBest);

        ArrayList<Integer> solutionsCost = new ArrayList<>();

        for(int i=0; i<solutions.size(); i++)
        {
            if(i != indexOfBest)
            {
                double temp = similarity.compareVertices(bestSolution, solutions.get(i));
                double temp1 = similarity.compareEdges(bestSolution, solutions.get(i));
                vbestSimilarity.add(temp);
                ebestSimilarity.add(temp1);
                solutionsCost.add(costs.get(i));
            }
        }

        writeDataToFile(solutionsCost, vbestSimilarity, "verticesBestSimilarity.txt");
        writeDataToFile(solutionsCost, ebestSimilarity, "edgesBestSimilarity.txt");

    }

    public void writeDataToFile(ArrayList<Integer> costs, ArrayList<Double> similarity, String fileName){
        try {
            PrintWriter printWriter = new PrintWriter(fileName, "UTF-8");
            for(int i = 0; i<costs.size(); i++){
                String sim = String.valueOf(similarity.get(i));
                printWriter.println(costs.get(i) + "\t" + sim.replace(".", ","));
            }
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Integer> calculateCosts(ArrayList<ArrayList<Vertex>> solutions){
        Evaluator evaluator = new Evaluator();
        ArrayList<Integer> solutionsCost = new ArrayList<>();

        for(ArrayList<Vertex> solution: solutions){
            solutionsCost.add(evaluator.evaluateSolution(solution));
        }

        return solutionsCost;
    }



    public ArrayList<Vertex> generateRandomSolution(ArrayList<Vertex> vertexList){
        Random rnd = new Random();
        ArrayList<Vertex> result = new ArrayList<>();
        ArrayList<Vertex> filtered = new ArrayList<>(vertexList);

        int numVertices = vertexList.size();

        for(int i=0; i<SOLUTION_LENGTH; i++){
            int n = rnd.nextInt(numVertices);
            result.add(filtered.get(n));
            filtered.remove(n);
            numVertices -= 1;
        }

        return result;
    }

    public ArrayList<ArrayList<Vertex>> createSolutions(ArrayList<Vertex> vertexList){
        ArrayList<ArrayList<Vertex>> solutions = new ArrayList<>();
        NearestNeihbor nearestNeihbor = new NearestNeihbor(vertexList);
        LocalSearch localSearch = new LocalSearch(vertexList, nearestNeihbor);

        for(int i =0; i<NUM_SOLUTIONS; i++){
            ArrayList<Vertex> randomSolution = generateRandomSolution(vertexList);
            localSearch.generateSolution(randomSolution);
            solutions.add(randomSolution);
            if(i%100 == 0){
                System.out.println(i);
            }

        }
        return solutions;
    }


    public static void main(String[] args)throws IOException, ParserConfigurationException, SAXException {

        Comparison comparison = new Comparison();
        ArrayList<Vertex> vertexList = XmlReader.getData();

        ArrayList<ArrayList<Vertex>> solutions = comparison.createSolutions(vertexList);
        ArrayList<Integer> costs = comparison.calculateCosts(solutions);

        comparison.compareSolutions(solutions, costs);
        comparison.compareWithBestSolution(solutions, costs);



    }

}
