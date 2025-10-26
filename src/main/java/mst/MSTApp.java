package mst;

public class MSTApp {
    public static void main(String[] args) throws Exception {
        String inputPath = args.length > 0 ? args[0] : "data/input.json";
        String outputPath = args.length > 1 ? args[1] : "data/output.json";

        IOUtil.InputRoot in = IOUtil.readInputJson(inputPath);
        IOUtil.OutputRoot out = new IOUtil.OutputRoot();

        for (IOUtil.GraphSpec gs : in.graphs) {
            Graph g = IOUtil.toGraph(gs);
            MSTResult p = Prim.run(g);
            MSTResult k = Kruskal.run(g);

            IOUtil.OutputItem item = new IOUtil.OutputItem();
            item.graph_id = gs.id;
            item.input_stats.put("vertices", g.V());
            item.input_stats.put("edges", g.E());

            item.prim.mst_edges = IOUtil.toSpecs(p.mstEdges);
            item.prim.total_cost = p.totalCost;
            item.prim.operations_count = p.operationCount;
            item.prim.execution_time_ms = p.executionTimeMs;

            item.kruskal.mst_edges = IOUtil.toSpecs(k.mstEdges);
            item.kruskal.total_cost = k.totalCost;
            item.kruskal.operations_count = k.operationCount;
            item.kruskal.execution_time_ms = k.executionTimeMs;

            out.results.add(item);
        }

        IOUtil.writeOutputJson(outputPath, out);

        System.out.println("MST comparison summary:");
        for (IOUtil.OutputItem it : out.results) {
            System.out.printf(
                    "Graph %d | V=%d E=%d | cost: Prim=%d, Kruskal=%d | time(ms): P=%.3f K=%.3f%n",
                    it.graph_id,
                    it.input_stats.get("vertices"), it.input_stats.get("edges"),
                    it.prim.total_cost, it.kruskal.total_cost,
                    it.prim.execution_time_ms, it.kruskal.execution_time_ms
            );
        }
    }
}