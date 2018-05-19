package org.oregu.graph;

public class GraphBuilder {

    public static Graph create(int[][] edges) {
        Graph graph = new Graph();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                graph.link(i, edges[i][j]);
            }
        }
        return graph;
    }
}
