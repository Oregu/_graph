package org.oregu.graph.algo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.oregu.graph.Graph;

public class ShortestPath {

    private final Graph g;
    private final int from;

    private final int[] paths;

    public ShortestPath(Graph g, int from) {
        this.g = g;
        this.from = from;

        this.paths = new int[g.size()];
        Arrays.fill(paths, Integer.MAX_VALUE);
    }

    public int run(int to) {
        if (paths[from] == 0) {
            return paths[to];
        }

        paths[from] = 0;

        for (Integer v : g) {
            List<Integer> connected = g.edges(v);
            for (Integer vc : connected) {
                if (paths[vc] > paths[v] + 1) {
                    paths[vc] = paths[v] + 1;
                }
            }
        }

        return paths[to];
    }
}
