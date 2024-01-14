import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Network {

    //DATA STRUCTURE TO STORE THE EDGES OF THE SOCIAL NETWORK
    HashMap<Integer, ArrayList<Connections>> network; //The key to this data structure is a particular column, the object stored is an arraylist of connections
                                                      // where each connection object contains the value of the node, and the corresponding weight
    Scanner scnr;

    //Connections class
    private class Connections{
        public int node;
        public double weight;

        public Connections(int node, double weight){
            this.node = node;
            this.weight = weight;
        }
    }


    public Network(int columns, int rows, Scanner scnr){
        this.network = new HashMap();
        this.scnr = scnr;
    }

    public void readMTXFile(){
        while(scnr.hasNextLine()){
            String[] line = scnr.nextLine().split(" ");
            int rows = Integer.parseInt(line[0].trim());
            int columns = Integer.parseInt(line[1].trim());

            if(!network.containsKey(columns)) this.network.put(columns, new ArrayList<Connections>());
            if(!network.containsKey(rows)) this.network.put(rows, new ArrayList<Connections>());

            this.network.get(columns).add(new Connections(rows, 1.0));
            this.network.get(rows).add(new Connections(columns, 1.0));
        }
    }

    public void learnFeatures(){
        return;
    }

    public Integer[] randomWalk2(int startNode, int length, double p, double q){
        Integer[] walk = new Integer[length];
        walk[0] = startNode;
        probabilitySetter(startNode, p, q);

        walk[1] = pmfSelector(startNode);

        for(int i = 1; i < length; i++){
            int current = walk[i-1];
            walk[i+1] = pmfSelector(walk[i]);

            probabilityCleaner(current);
            probabilitySetter(walk[i], p, q);
        }

        probabilityCleaner(start);
        return walk;
    }

    /**
     * We will assume that the initial weights of all edges in the graph is 1.
     * This method sets the probabilities in the context of a specific node.
     * @param start : start node of random walk
     * @param p : value controlling homophilic exploration
     * @param q : value controlling structurally equivalent exploration
     */
    public void probabilitySetter(int start, double p, double q){

        ArrayList<Connections> connections = network.get(start);

        for(Connections connection : connections){
            connection.weight = 1/p;

            ArrayList<Connections> mutuals = network.get(connection.node);
            for(Connections mutual : mutuals){
                mutual.weight = 1/q;
                if(mutual.node == start) mutual.node = 1;
            }

        }

        return;
    }

    /**
     * To clean the probabilities after each random walk from a particular start node
     * @param start : node where random walk starts
     */
    public void probabilityCleaner(int start){

        ArrayList<Connections> connections = network.get(start);

        for(Connections connection : connections){
            connection.weight = 1.0;

            ArrayList<Connections> mutuals = network.get(connection.node);
            for(Connections mutual : mutuals){
                mutual.weight = 1.0;
                if(mutual.node == start) mutual.node = 1.0;
            }

        }

        return;
    }

    /**
     * Given a node, it selects a neighbor of the node based on the pmf
     * @param node
     * @return
     */
    private int pmfSelector(int node){

    }
}
