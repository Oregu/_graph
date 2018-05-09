package org.oregu.graph.algo;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import org.oregu.graph.Graph;
import org.oregu.graph.Vertex;

public class BreadthFirstSearch {
    private final Graph g;
    private Set<Integer> discovered;

    public BreadthFirstSearch(Graph g) {
        this.g = g;
        discovered = new HashSet<>(g.size());
    }

    public void run(Consumer<Integer> consumer) {
        Queue<Integer> stack = new ArrayDeque<>();
        Iterator<Integer> it = g.iterator();
        if (!it.hasNext()) {
            return;
        }

        Integer v = it.next();
        stack.add(v);
        discovered.add(v);

        while (!stack.isEmpty()) {
            v = stack.remove();
            consumer.accept(v);

            List<Integer> connected = g.edges(v);
            for (Integer vc : connected) {
                if (!discovered.contains(vc)) {
                    stack.add(vc);
                    discovered.add(vc);
                }
            }
        }
    }
}
