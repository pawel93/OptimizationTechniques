package TO.Util;

import TO.Model.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Report {

    private static final int NUM_STARTS = 100;
    private String fileName;

    public Report(String fileName){
        this.fileName = fileName;
    }

    public String resultToString(ArrayList<Vertex> result){
        StringBuilder builder = new StringBuilder();
        for(Vertex vertex: result){
            builder.append(vertex.getId()).append(", ");
        }

        return builder.toString();
    }

    public void writeResultsToFile(ArrayList<Integer> costs, ArrayList<String> solutions, PrintWriter writer){
        for(int i=0; i<solutions.size(); i++){
            writer.println(solutions.get(i));
            writer.println("koszt " + costs.get(i));
            writer.println("-----------");
        }

    }

    public void costStats(ArrayList<Integer> costs, ArrayList<String> solutions, PrintWriter writer){
        double sum = 0.0;

        for(Integer cost: costs){
            sum += cost;
        }
        double avg = sum / costs.size();
        int min = Collections.min(costs);
        int max = Collections.max(costs);
        String best = solutions.get(costs.indexOf(min));
        String worst = solutions.get(costs.indexOf(max));

        writer.println(best);
        writer.println("koszt min " + min);
        writer.println("------------");

        writer.println(worst);
        writer.println("koszt max " + max);
        writer.println("------------");

        writer.println("koszt avg " + avg);

    }

    public void timeStats(ArrayList<Long> times, PrintWriter writer){
        double sum = 0.0;
        for(Long time: times){
            sum += time;
        }
        double avg = sum / times.size();
        long min = Collections.min(times);
        long max = Collections.max(times);

        writer.println("avg time " + avg);
        writer.println("min time" + min);
        writer.println("max time " + max);

    }


    public void createReport(Algorithm alg){
        Evaluator evaluator = new Evaluator();
        PrintWriter printWriter = null;
        try{
            printWriter = new PrintWriter(fileName, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> costs = new ArrayList<>();
        ArrayList<Long> times = new ArrayList<>();
        ArrayList<String> solutions = new ArrayList<>();

        for(int i=0; i<NUM_STARTS; i++){
            long startTime = System.currentTimeMillis();
            ArrayList<Vertex> result = alg.generateSolution(i);
            long elapsed = System.currentTimeMillis() - startTime;

            solutions.add(resultToString(result));

            int solutionCost = evaluator.evaluateSolution(result);
            costs.add(solutionCost);
            times.add(elapsed);
            System.out.println(i);
        }

        writeResultsToFile(costs, solutions, printWriter);
        costStats(costs, solutions, printWriter);
        timeStats(times, printWriter);

        printWriter.close();
    }





}
