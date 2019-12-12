import lib.Graph;
import lib.GraphNode;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        graph.print();
        GraphNode start = null, finish = null;
        while (start == finish) {
            start = graph.getRandomNode();
            finish = graph.getRandomNode();
        }

        getShortestPath(start, finish);
    }

    private static void getShortestPath(GraphNode start, GraphNode finish) {
        HashMap<GraphNode, Integer> distances = new HashMap<>();
        SortedMap<Integer, GraphNode> toVisit = new TreeMap<>();
        for (GraphNode n : start.edges.keySet()) {
            toVisit.put(start.edges.get(n), n);
        }

        ArrayList<GraphNode> visited = new ArrayList<>();
        distances.put(start, 0);
        visited.add(start);

        while (toVisit.size() > 0){
            int nextSmallestDistance = toVisit.firstKey();
            GraphNode nextSmallestDistanceNode  = toVisit.remove(nextSmallestDistance);

            if (visited.contains(nextSmallestDistanceNode)) {
                continue;
            }

            if (nextSmallestDistanceNode == finish) {
                System.out.println(String.format("Smallest distance from %s to %s is: %d", start.label, finish.label, nextSmallestDistance));
                return;
            }

            visited.add(nextSmallestDistanceNode);

            distances.put(nextSmallestDistanceNode, nextSmallestDistance);

            for (GraphNode node : nextSmallestDistanceNode.edges.keySet()) {
                if (!visited.contains(node)){
                    int neighbourDistance = nextSmallestDistance + nextSmallestDistanceNode.edges.get(node);
                    toVisit.put(neighbourDistance, node);
                }
            }
        }
        System.out.println(String.format("No available path found from %s to %s", start.label, finish.label));
    }

    private static Graph buildGraph() {
        Graph g = new Graph();
        Random r = new Random();
        int nodeCount = r.nextInt(9) + 2;
        System.out.println("New graph contains " + nodeCount + " nodes.");
        for (int i = 0; i < nodeCount; i++) {
            g.insertNode(new GraphNode("node " + i));
        }
        int edgeCount = r.nextInt(20) + 1;
        System.out.println("New graph contains " + edgeCount + " edges.");

        for (int i = 0; i < edgeCount; i++) {
            String from = "", to = "";
            while (from.equals(to)) {
                from = "node " + r.nextInt(nodeCount);
                to = "node " + r.nextInt(nodeCount);
            }

            GraphNode gnFrom = g.getNode(from);
            GraphNode gnTo = g.getNode(to);
            g.insertEdge(gnFrom, gnTo, r.nextInt(20) + 1);

        }

        return g;
    }
}
