package org.oregu.graph.algo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

import org.oregu.graph.Graph;

public class DepthFirstSearch {
    private final Graph g;
    private Set<Integer> visited;

    public DepthFirstSearch(Graph g) {
        this.g = g;
        visited = new HashSet<>();
    }

    public void run(Consumer<Integer> consumer) {
        Iterator<Integer> it = g.iterator();

        if (!it.hasNext()) {
            // Empty graph
            return;
        }

        Integer start = it.next();
        search(start, consumer);
    }

    private void search(Integer start, Consumer<Integer> consumer) {
        consumer.accept(start);
        visited.add(start);

        for (Integer v : g.edges(start)) {
            if (!visited.contains(v)) {
                search(v, consumer);
            }
        }
    }
}
