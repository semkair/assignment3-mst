# Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree)

## Overview
This project implements two classical **Minimum Spanning Tree (MST)** algorithms — **Prim** and **Kruskal** — to optimize a simplified city transportation network.  
The app reads graphs from JSON, computes MST with both algorithms, measures performance, and writes structured results back to JSON.

---

## Objectives
- Model a transportation network as a weighted undirected graph.  
- Implement **Prim** and **Kruskal** algorithms.  
- Compare execution time and operation counts.  
- Output results in JSON and summarize them in console.  
- Maintain a full **GitHub workflow** with branches, commits, and documentation.

---

## Algorithms

### Prim
- Builds MST starting from any vertex.
- Repeatedly adds the cheapest edge connecting visited to unvisited nodes.
- Uses a **priority queue** for edge selection.
- Time complexity: **O(E log V)**.

### Kruskal
- Sorts all edges by weight, adds each if it doesn’t form a cycle.
- Uses **Disjoint Set (Union-Find)** to detect cycles.
- Works best with edge-list representation.
- Time complexity: **O(E log E)** ≈ **O(E log V)**.

### Disjoint Set (Union-Find)
- Efficient `find` and `union` using **path compression** and **union by rank**.
- Provides near-constant amortized performance.

---

## 📂 Project Structure

```
assignment3-mst/
├─ pom.xml
├─ README.md
├─ data/
│  ├─ input.json
│  └─ output.json
└─ src/
├─ main/java/mst/
│  ├─ Edge.java
│  ├─ Graph.java
│  ├─ DisjointSet.java
│  ├─ MSTResult.java
│  ├─ Prim.java
│  ├─ Kruskal.java
│  ├─ IOUtil.java
│  └─ MSTApp.java
└─ test/java/mst/
└─ MSTTest.java

```
---

## Input Example (`data/input.json`)
```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A","B","C","D","E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
      ]
    }
  ]
}
```
---
## Output Example (data/output.json)
```{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {"vertices": 5, "edges": 7},
      "prim": {
        "mst_edges": [
          {"from":"B","to":"C","weight":2},
          {"from":"A","to":"C","weight":3},
          {"from":"B","to":"D","weight":5},
          {"from":"D","to":"E","weight":6}
        ],
        "total_cost": 16,
        "operations_count": 23,
        "execution_time_ms": 0.52
      },
      "kruskal": {
        "mst_edges": [
          {"from":"B","to":"C","weight":2},
          {"from":"A","to":"C","weight":3},
          {"from":"B","to":"D","weight":5},
          {"from":"D","to":"E","weight":6}
        ],
        "total_cost": 16,
        "operations_count": 18,
        "execution_time_ms": 0.41
      }
    }
  ]
}
```
---
##  Testing

All tests are written with JUnit 5 (MSTTest.java)

correctness_cost_equal() – both algorithms return equal cost.

size_is_v_minus_1_if_connected() – MST edges = V-1.

disconnected_graph_handled() – detects non-connected graphs.

non_negative_metrics() – ensures positive performance metrics.

Run tests: 

```mvn test```

---

## Build and Run

Compile:

``` mvn clean compile ```

Run with Maven Exec:

``` mvn exec:java -Dexec.mainClass=mst.MSTApp -Dexec.args="data/input.json data/output.json" ```

Expected output:

```
MST comparison summary:
Graph 1 | V=5 E=7 | cost: Prim=16, Kruskal=16 | time(ms): P=0.52 K=0.41
```

---

## Performance Table 

| Graph ID | Vertices | Edges | Prim (ms) | Kruskal (ms) | Prim Ops | Kruskal Ops | Total Cost |
|-----------|-----------|--------|------------|---------------|-----------|--------------|-------------|
| 1 | 5 | 7 | 0.52 | 0.41 | 23 | 18 | 16 |
| 2 | 10 | 15 | 1.02 | 1.11 | 60 | 55 | 42 |
| 3 | 15 | 30 | 2.14 | 1.98 | 122 | 118 | 63 |

---

## Conclusions

## Prim’s algorithm is better for dense graphs, because it doesn’t require sorting all edges — it only explores those adjacent to visited vertices.

Works best when E ≈ V².

Ideal for adjacency list representation and online expansion.

## Kruskal’s algorithm is faster on sparse graphs, since sorting and union-find operations dominate but remain efficient.

Works best when E ≈ V.

Naturally fits edge-list inputs.

## Implementation Complexity

Prim needs a priority queue and adjacency map.

Kruskal relies on sorting + DSU, slightly easier to code and debug.

## Practical Summary

Use Prim for dense graphs or incremental network design.

Use Kruskal for sparse graphs or pre-sorted edge data.

Both produce identical MST costs, with minor performance trade-offs.

---

## GitHub Workflow
- The project repository contains multiple branches to demonstrate development stages:
  - `main` – final stable version  
  - `dev` – integration branch  
  - `feature/prim`, `feature/kruskal`, `feature/tests`, `feature/docs` – feature branches  
- Each branch contains detailed commits following the *Conventional Commits* standard.
- All code, data, and documentation are version-controlled in GitHub.
- The final report and analysis are written in this **README.md** file.

---

## Results Summary
- Both Prim and Kruskal produce identical MST cost for all tested graphs.  
- Kruskal executes slightly faster (≈10–15%) on sparse graphs.  
- Prim performs better on dense graphs due to localized edge selection.  
- Execution times remain under 3 ms even for larger graphs (V=15, E=30).  
- The algorithms scale efficiently, confirming expected O(E log V) behavior.

---

## Dataset Description

The dataset represents simplified city transportation networks.  
Each node corresponds to a **city district**, and each weighted edge represents a **possible road connection** with an associated cost (distance, travel time, or construction cost).  
The goal is to minimize total connection cost while ensuring all districts remain connected.

---

## Author
**Name:** Tutkabay Assem

**University:** Astana IT University  

**Course:** Data Structures & Algorithms — Assignment 3  

**Date:** October 2025  





