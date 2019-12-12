package lib;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    private ArrayList<GraphNode> nodes;
    private Random r;

    public Graph() {
        nodes = new ArrayList<>();
        r = new Random();
    }

    public void insertNode(GraphNode newNode) {
        GraphNode gn = getNode(newNode.label);
        if (gn == null)
            nodes.add(newNode);
    }

    public void insertEdge(GraphNode from, GraphNode to, int weight) {
        from.edges.put(to, weight);
        to.edges.put(from, weight);
    }

    public GraphNode getNode(String label) {

        for (GraphNode gn : nodes) {
            if (gn.label.equals(label)) return gn;
        }
        return null;
    }

    public void print() {
        for (GraphNode gn : nodes) {

            System.out.println(gn.label+ ": ");
            for (GraphNode adjGn : gn.edges.keySet()) {
                System.out.println(" - " + adjGn.label + " - " + gn.edges.get(adjGn));
            }
        }
    }

    public GraphNode getRandomNode() {
        return nodes.get(r.nextInt(nodes.size()));
    }
}
