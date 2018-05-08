package org.oregu.graph;

import org.junit.Test;

public class GraphTest {

    @Test
    public void createGraph() {
        Graph graph = new Graph();
        graph.link("A", "B");
        graph.link("B", "C");
        graph.link("B", "D");
        graph.link("A", "D");
        graph.link("C", "D");
    }
}
