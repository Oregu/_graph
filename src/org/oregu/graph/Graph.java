package org.oregu.graph;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph implements Iterable<Integer> {
    private static final int MAX_V = 1000;

    private final LinkedList[] edges = new LinkedList[MAX_V];
    private final boolean directed;
    private int numVertexes;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public Graph() {
        this(false);
    }

    public void link(int a, int b) {
        if (edges[a] == null) {
            edges[a] = new LinkedList<Integer>();
        }
        edges[a].add(b);

        if (edges[b] == null) {
            edges[b] = new LinkedList<Integer>();
        }
        edges[b].add(a);
        numVertexes = Math.max(Math.max(a, b), numVertexes);
    }

    public int size() {
        return numVertexes;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new GraphIterator(this);
    }

    public List<Integer> edges(int v) {
        return Collections.unmodifiableList(edges[v]);
    }

    private class GraphIterator implements Iterator<Integer> {
        private int index;
        private final Graph graph;

        public GraphIterator(Graph graph) {
            this.graph = graph;
        }

        @Override
        public boolean hasNext() {
            return index < graph.numVertexes;
        }

        @Override
        public Integer next() {
            while (edges[index] == null) {
                index++;
            }
            return index;
        }
    }
}
