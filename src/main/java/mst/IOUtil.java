package mst;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class IOUtil {
    public static class EdgeSpec {
        public String from;
        public String to;
        public int weight;
    }
    public static class GraphSpec {
        public int id;
        public List<String> nodes;
        public List<EdgeSpec> edges;
    }
    public static class InputRoot {
        public List<GraphSpec> graphs;
    }

    public static class AlgoResult {
        public List<EdgeSpec> mst_edges;
        public int total_cost;
        public long operations_count;
        public double execution_time_ms;
    }
    public static class OutputItem {
        public int graph_id;
        public Map<String, Integer> input_stats = new HashMap<>();
        public AlgoResult prim = new AlgoResult();
        public AlgoResult kruskal = new AlgoResult();
    }
    public static class OutputRoot {
        public List<OutputItem> results = new ArrayList<>();
    }

    public static InputRoot readInputJson(String path) throws IOException {
        ObjectMapper om = new ObjectMapper();
        try (InputStream in = Files.newInputStream(Path.of(path))) {
            return om.readValue(in, InputRoot.class);
        }
    }

    public static void writeOutputJson(String path, OutputRoot out) throws IOException {
        ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try (OutputStream o = Files.newOutputStream(Path.of(path))) {
            om.writeValue(o, out);
        }
    }

    public static Graph toGraph(GraphSpec gs) {
        List<Edge> es = new ArrayList<>();
        for (EdgeSpec e : gs.edges) es.add(new Edge(e.from, e.to, e.weight));
        return new Graph(gs.nodes, es);
    }

    public static List<EdgeSpec> toSpecs(List<Edge> edges) {
        List<EdgeSpec> out = new ArrayList<>();
        for (Edge e : edges) {
            EdgeSpec s = new EdgeSpec();
            s.from = e.from; s.to = e.to; s.weight = e.weight;
            out.add(s);
        }
        return out;
    }
}