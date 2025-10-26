package mst;

import java.util.*;

public class Graph {
    public final List<String> nodes;
    public final List<Edge> edges;
    private final Map<String, List<Edge>> adj;

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = List.copyOf(nodes);
        this.edges = List.copyOf(edges);
        this.adj = new HashMap<>();
        for (String v : this.nodes) adj.put(v, new ArrayList<>());
        for (Edge e : this.edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight)); // неориентированный граф
        }
    }

    public List<Edge> neighbors(String v) {
        return adj.getOrDefault(v, List.of());
    }

    public int V() { return nodes.size(); }
    public int E() { return edges.size(); }
}