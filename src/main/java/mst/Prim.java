package mst;

import java.util.*;

public class Prim {
    public static MSTResult run(Graph g) {
        long ops = 0;
        long start = System.nanoTime();

        if (g.V() == 0) return new MSTResult(List.of(), 0, true, 0, 0);

        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        String startNode = g.nodes.get(0);
        visited.add(startNode);
        pq.addAll(g.neighbors(startNode));

        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.poll(); ops++;
            if (visited.contains(e.to)) continue;
            visited.add(e.to);
            mst.add(e);
            for (Edge ne : g.neighbors(e.to)) {
                if (!visited.contains(ne.to)) {
                    pq.add(ne); ops++;
                }
            }
        }

        boolean connected = visited.size() == g.V();
        int cost = mst.stream().mapToInt(ed -> ed.weight).sum();
        double ms = (System.nanoTime() - start) / 1_000_000.0;
        return new MSTResult(mst, cost, connected, ops, ms);
    }
}