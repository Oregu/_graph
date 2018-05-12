package org.oregu.graph.algo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import org.oregu.graph.Graph;

public class DepthFirstSearch implements IAlgorithm {
    private final Graph g;
    private Set<Integer> visited;

    public DepthFirstSearch(Graph g) {
        this.g = g;
        visited = new HashSet<>();
    }

    public void run(Function<Integer, Boolean> function) {
        Iterator<Integer> it = g.iterator();

        if (!it.hasNext()) {
            return; // Empty graph
        }

        Integer start = it.next();
        search(start, function);
    }

    private void search(Integer start, Function<Integer, Boolean> consumer) {
        visited.add(start);
        if (!consumer.apply(start)) {
            return;
        }

        for (Integer v : g.edges(start)) {
            if (!visited.contains(v)) {
                search(v, consumer);
            }
        }
    }
}
