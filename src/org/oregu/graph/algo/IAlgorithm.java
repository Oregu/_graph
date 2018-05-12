package org.oregu.graph.algo;

import java.util.function.Function;

public interface IAlgorithm {

    /**
     * Run particular algorithm on {@link org.oregu.graph.Graph}.
     * Algorithm can decide to short-circuit it's execution, if consumer function returns false.
     *
     * @param function Function to be invoked during algorithm execution.
     *                 Return false to short-circuit execution (up to algorithm implementation).
     */
    void run(Function<Integer, Boolean> function);
}
