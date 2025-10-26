package mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MSTTest {
    private Graph sampleConnected() {
        List<String> nodes = List.of("A","B","C","D");
        List<Edge> edges = List.of(
                new Edge("A","B",1),
                new Edge("B","C",2),
                new Edge("C","D",3),
                new Edge("A","C",4),
                new Edge("B","D",5)
        );
        return new Graph(nodes, edges);
    }

    private Graph sampleDisconnected() {
        List<String> nodes = List.of("A","B","C","D");
        List<Edge> edges = List.of(
                new Edge("A","B",1),
                new Edge("C","D",2)
        );
        return new Graph(nodes, edges);
    }

    @Test
    void correctness_cost_equal() {
        Graph g = sampleConnected();
        MSTResult p = Prim.run(g);
        MSTResult k = Kruskal.run(g);
        assertEquals(p.totalCost, k.totalCost);
    }

    @Test
    void size_is_v_minus_1_if_connected() {
        Graph g = sampleConnected();
        MSTResult p = Prim.run(g);
        MSTResult k = Kruskal.run(g);
        assertEquals(g.V()-1, p.mstEdges.size());
        assertEquals(g.V()-1, k.mstEdges.size());
        assertTrue(p.connected);
        assertTrue(k.connected);
    }

    @Test
    void disconnected_graph_handled() {
        Graph g = sampleDisconnected();
        MSTResult p = Prim.run(g);
        MSTResult k = Kruskal.run(g);
        assertFalse(p.connected);
        assertFalse(k.connected);
        assertTrue(p.mstEdges.size() < g.V()-1);
        assertTrue(k.mstEdges.size() < g.V()-1);
    }

    @Test
    void non_negative_metrics() {
        Graph g = sampleConnected();
        MSTResult p = Prim.run(g);
        MSTResult k = Kruskal.run(g);
        assertTrue(p.executionTimeMs >= 0);
        assertTrue(k.executionTimeMs >= 0);
        assertTrue(p.operationCount >= 0);
        assertTrue(k.operationCount >= 0);
    }
}