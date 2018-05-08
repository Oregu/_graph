package org.oregu.graph;

public class Graph {
    private static final int MAX_V = 1000;

    private final Vertex[] vertexes = new Vertex[MAX_V];
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public Graph() {
        this(false);
    }

    public void link(String a, String b) {
        for (Vertex vertex : vertexes) {
            if (vertex.getLabel().equals(a)) {
                Vertex current = vertex;
                while (current.getNext() != null) {
                    // If already linked
                    if (current.getNext().getLabel().equals(b)) {
                        return;
                    }
                    current = current.getNext();
                }
                Vertex bVertex = new Vertex(b);
                current.setNext(bVertex);

                while (current.getNext() != null) {
                    if (current.getNext().getLabel().equals(a)) {
                        return;
                    }
                    current = current.getNext();
                }
            }
        }
    }
}
