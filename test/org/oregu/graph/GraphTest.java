package org.oregu.graph;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.oregu.graph.algo.BipartiteDetector;
import org.oregu.graph.algo.BreadthFirstSearch;
import org.oregu.graph.algo.DepthFirstSearch;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        assertEquals(asList(1, 3), graph.edges(0));
        assertEquals(asList(0, 2, 3), graph.edges(1));
        assertEquals(asList(1, 3, 5), graph.edges(2));
        assertEquals(asList(0, 1, 2, 6), graph.edges(3));
    }

    @Test
    public void bfs() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 3);
        graph.link(1, 2);
        graph.link(1, 5);
        graph.link(2, 5);
        graph.link(5, 6);
        graph.link(3, 6);

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        List<Integer> bfsOrder = new LinkedList<>();
        bfs.run(bfsOrder::add);

        assertEquals(asList(0, 1, 3, 2, 5, 6), bfsOrder);
    }

    @Test
    public void dfs() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 3);
        graph.link(1, 2);
        graph.link(2, 5);
        graph.link(3, 6);

        DepthFirstSearch bfs = new DepthFirstSearch(graph);
        List<Integer> dfsOrder = new LinkedList<>();
        bfs.run(dfsOrder::add);

        assertEquals(asList(0, 1, 2, 5, 3, 6), dfsOrder);
    }

    @Test
    public void bipartite_yes() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 3);
        graph.link(1, 2);
        graph.link(2, 5);
        graph.link(3, 6);

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertTrue(bp.test());
    }

    @Test
    public void bipartite_yes2() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 4);
        graph.link(1, 2);
        graph.link(2, 6);
        graph.link(3, 5);
        graph.link(5, 6);

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertTrue(bp.test());
    }

    @Test
    public void bipartite_no() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 3);
        graph.link(1, 2);
        graph.link(2, 5);
        graph.link(3, 1);
        graph.link(3, 6);

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertFalse(bp.test());
    }

    @Test
    public void bipartite_no2() {
        Graph graph = new Graph();
        graph.link(0, 1);
        graph.link(0, 2);
        graph.link(1, 3);
        graph.link(2, 3);
        graph.link(1, 2);

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertFalse(bp.test());
    }
}
