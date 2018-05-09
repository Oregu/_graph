package org.oregu.graph;

public class Vertex {

    private final int id;
    private final String label;
    private Vertex next;

    Vertex(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int id() {
        return id;
    }

    public String label() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
