package mst;

import java.util.List;

public class MSTResult {
    public final List<Edge> mstEdges;
    public final int totalCost;
    public final boolean connected;
    public final long operationCount;
    public final double executionTimeMs;

    public MSTResult(List<Edge> mstEdges, int totalCost, boolean connected, long operationCount, double executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.connected = connected;
        this.operationCount = operationCount;
        this.executionTimeMs = executionTimeMs;
    }
}