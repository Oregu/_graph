package org.oregu.graph.algo;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.oregu.graph.Graph;

/**
 * Test is graph is bipartite.
 */
public class BipartiteDetector implements IDetector {
    private final Graph g;
    private final Map<Integer, Boolean> colors;

    public BipartiteDetector(Graph g) {
        this.g = g;
        this.colors = new HashMap<>(g.size());
    }

    // TODO solve with BFS.java, instead of reimplementing it.
    // Pass parent vertex to validate colorization.

    @Override
    public boolean test() {
        // Bipartite graph property is that is can be colored with two colors.
        // Run a bfs on it to make sure colorization possible.
        // Boolean is used for color values.

        if (g.size() < 2) {
            return false;
        }
        if (g.size() == 2) {
            return true;
        }

        Queue<Integer> stack = new ArrayDeque<>();

        for (Integer v : g) {
            if (colors.containsKey(v)) {
                continue;
            }

            stack.add(v);
            colors.put(v, false);

            while (!stack.isEmpty()) {
                v = stack.remove();

                List<Integer> connected = g.edges(v);
                for (Integer vc : connected) {
                    if (!colors.containsKey(vc)) {
                        stack.add(vc);
                        colors.put(vc, !colors.get(v));
                    } else if (colors.get(vc) == colors.get(v)) {
                        return false; // Saw a connected vertex with same color.
                    }
                }
            }
        }
        return true;
    }
}
