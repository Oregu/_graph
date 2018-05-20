package org.oregu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.sun.javafx.geom.Edge;
import sun.tools.jconsole.inspector.Utils;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

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
        link(a, b, 1);
    }

    public void link(int a, int b, int weight) {
        if (edges[a] != null && edges[a].contains(new EdgeInfo(b, 1))) {
            return;
        }

        if (edges[a] == null) {
            edges[a] = new LinkedList<EdgeInfo>();
        }
        edges[a].add(new EdgeInfo(b, weight));

        if (edges[b] == null) {
            edges[b] = new LinkedList<EdgeInfo>();
        }
        edges[b].add(new EdgeInfo(a, weight));
        numVertexes = Math.max(Math.max(a, b), numVertexes);
    }

    public int size() {
        return numVertexes+1;
    }

    public int weight(int a, int b) {
        LinkedList<EdgeInfo> es = edges[a];
        if (es != null) {
            for (EdgeInfo edge : es) {
                if (edge.otherVertex == b) {
                    return edge.weight;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new GraphIterator(this);
    }

    public List<Integer> edges(int v) {
        if (edges[v] == null) {
            return emptyList();
        }

        List<Integer> es = new ArrayList<>(edges[v].size());
        for (int i = 0; i < edges[v].size(); i++) {
            es.add(((EdgeInfo)edges[v].get(i)).otherVertex);
        }
        return unmodifiableList(es);
    }

    private class EdgeInfo {
        private final int otherVertex;
        private final int weight;

        public EdgeInfo(int v, int w) {
            otherVertex = v;
            weight = w;
        }

        @Override
        public int hashCode() {
            return otherVertex;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof EdgeInfo && otherVertex == ((EdgeInfo) obj).otherVertex;
        }
    }

    private class GraphIterator implements Iterator<Integer> {
        private int index = -1;
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
            while (edges[++index] == null);
            return index;
        }
    }
}
