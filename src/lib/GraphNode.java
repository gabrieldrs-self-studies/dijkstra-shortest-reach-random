package lib;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphNode {
    public String label;
    // GraphNode is the other end of the edge, Integer is the edge weight
    public HashMap<GraphNode, Integer> edges;

    public GraphNode(String label) {
        this.label = label;
        this.edges = new HashMap<>();
    }
}
