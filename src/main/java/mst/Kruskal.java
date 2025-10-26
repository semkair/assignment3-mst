package mst;

import java.util.*;

public class Kruskal {
    public static MSTResult run(Graph g) {
        long ops = 0;
        long start = System.nanoTime();

        List<Edge> edges = new ArrayList<>(g.edges);
        Collections.sort(edges); // сортировка по весу
        ops += edges.size(); // грубый учет сравнений

        DisjointSet ds = new DisjointSet();
        ds.makeSet(g.nodes);

        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {
            if (mst.size() == g.V() - 1) break;
            if (ds.union(e.from, e.to)) {
                mst.add(e);
            }
        }
        ops += ds.unions + ds.finds + ds.compressions;

        boolean connected = mst.size() == Math.max(0, g.V() - 1);
        int cost = mst.stream().mapToInt(ed -> ed.weight).sum();
        double ms = (System.nanoTime() - start) / 1_000_000.0;
        return new MSTResult(mst, cost, connected, ops, ms);
    }
}