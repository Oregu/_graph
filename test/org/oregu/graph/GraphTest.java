package org.oregu.graph;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.oregu.graph.algo.BreadthFirstSearch;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GraphTest {

    @Test
    public void createGraph() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 3);
        graph.link(1, 2);
        graph.link(1, 3);
        graph.link(2, 3);
        graph.link(2, 5);
        graph.link(5, 6);
        graph.link(3, 6);

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        List<Integer> bfsOrder = new LinkedList<>();
        bfs.run(bfsOrder::add);

        assertEquals(asList(0, 1, 3, 2, 6, 5), bfsOrder);
    }
}
