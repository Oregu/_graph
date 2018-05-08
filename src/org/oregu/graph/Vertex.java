package org.oregu.graph;

public class Vertex {

    private final String label;
    private Vertex next;

    public Vertex(String label) {
        this.label = label;
    }

    public Vertex getNext() {
        return next;
    }

    void setNext(Vertex other) {
        this.next = other;
    }

    public String getLabel() {
        return label;
    }
}
