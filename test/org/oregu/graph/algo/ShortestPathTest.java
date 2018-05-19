package org.oregu.graph.algo;

import org.junit.Test;
import org.oregu.graph.Graph;
import org.oregu.graph.GraphBuilder;

import static org.junit.Assert.assertEquals;

public class ShortestPathTest {

    @Test
    public void shortestPath_simple() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1}, {2}, {3}, {}});

        ShortestPath sp = new ShortestPath(graph, 0);
        assertEquals(0, sp.run(0));
        assertEquals(1, sp.run(1));
        assertEquals(2, sp.run(2));
        assertEquals(3, sp.run(3));
    }

    @Test
    public void shortestPath_withATwist() {
        Graph graph = GraphBuilder.create(
                new int[][] {{1}, {2,5}, {3,4}, {5}});

        ShortestPath sp = new ShortestPath(graph, 0);
        assertEquals(0, sp.run(0));
        assertEquals(1, sp.run(1));
        assertEquals(2, sp.run(2));
        assertEquals(3, sp.run(3));
        assertEquals(3, sp.run(4));
        assertEquals(2, sp.run(5));
    }
}
