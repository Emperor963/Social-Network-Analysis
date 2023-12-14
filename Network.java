import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


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

            this.network.get(columns).add(new Connections(rows, 1.0));
        }
    }

    public void learnFeatures(){
        return;
    }

    public void node2vecWalk(){
        return;
    }

    /**
     * We will assume that the initial weights of all edges in the graph is 1.
     */
    public void probabilitySetter(int p, int q){

        return;
    }
}
