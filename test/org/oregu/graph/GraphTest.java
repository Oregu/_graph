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
        Graph graph = GraphBuilder.create(
                new int[][] {{1,3}, {0,2,3}, {1,3,5}, {0,1,2,6}, {}, {2}, {3}});

        assertEquals(asList(1, 3), graph.edges(0));
        assertEquals(asList(0, 2, 3), graph.edges(1));
        assertEquals(asList(1, 3, 5), graph.edges(2));
        assertEquals(asList(0, 1, 2, 6), graph.edges(3));
        assertEquals(asList(), graph.edges(4));
        assertEquals(asList(2), graph.edges(5));
        assertEquals(asList(3), graph.edges(6));
    }

    @Test
    public void bfs() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,3}, {0,2,5}, {1,5}, {0,6}, {}, {1,2,6}, {3,5}});

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        List<Integer> bfsOrder = new LinkedList<>();
        bfs.run(bfsOrder::add);

        assertEquals(asList(0, 1, 3, 2, 5, 6), bfsOrder);
    }

    @Test
    public void dfs() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,3}, {0,2}, {1,5}, {0,6}, {}, {2}, {3}});

        DepthFirstSearch bfs = new DepthFirstSearch(graph);
        List<Integer> dfsOrder = new LinkedList<>();
        bfs.run(dfsOrder::add);

        assertEquals(asList(0, 1, 2, 5, 3, 6), dfsOrder);
    }

    @Test
    public void bipartite_yes() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,3}, {2}, {5}, {6}});

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertTrue(bp.test());
    }

    @Test
    public void bipartite_yes2() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,4}, {2}, {6}, {5}, {}, {6}});

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertTrue(bp.test());
    }

    @Test
    public void bipartite_no() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,3}, {2}, {5}, {1,6}});

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertFalse(bp.test());
    }

    @Test
    public void bipartite_no2() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,2}, {2,3}});

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertFalse(bp.test());
    }

    @Test
    public void bipartite_yes3() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1,2,3}, {2,3}, {3}});

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertFalse(bp.test());
    }

    @Test
    public void bipartite_big() {
        int[][] arr = new int[][] {
                {2,4},{2,3,4},{0,1},{1},{0,1},{7},{9},{5},{},{6},
                {12,14},{},{10},{},{10},{19},{18},{},{16},{15},
                {23},{23},{},{20,21},{},{},{27},{26},{},{},
                {34},{33,34},{},{31},{30,31},{38,39},{37,38,39},{36},{35,36},{35,36},
                {43},{},{},{40},{},{49},{47,48,49},{46,48,49},{46,47,49},{45,46,47,48}};
        Graph graph = GraphBuilder.create(arr);

        BipartiteDetector bp = new BipartiteDetector(graph);
        assertFalse(bp.test());
    }
}
