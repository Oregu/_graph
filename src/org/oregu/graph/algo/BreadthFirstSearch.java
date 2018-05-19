package org.oregu.graph.algo;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

import org.oregu.graph.Graph;

public class BreadthFirstSearch implements IAlgorithm {
    private final Graph g;
    private final Set<Integer> discovered;

    public BreadthFirstSearch(Graph g) {
        this.g = g;
        discovered = new HashSet<>(g.size());
    }

    public void run(Function<Integer, Boolean> function) {
        Queue<Integer> stack = new ArrayDeque<>();
        Iterator<Integer> it = g.iterator();
        if (!it.hasNext()) {
            return;
        }

        // TODO doesn't support multi-component graphs!

        Integer v = it.next();
        stack.add(v);
        discovered.add(v);

        while (!stack.isEmpty()) {
            v = stack.remove();
            if (!function.apply(v)) {
                return;
            }

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
