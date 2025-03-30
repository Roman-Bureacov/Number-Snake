package edu.uw.app.Model;

/**
 *  A functional interface specifically for specifying the method to total
 *  up values in a path.
 *  <br>
 *  Takes two values, the first being the total and the second being the next value.
 *  The algorithm returns the result of appending the next to the total.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
@FunctionalInterface
interface PathTotalingAlgorithm {
    /**
     * The specified algorithm for totaling the values.
     * @param pTotal the previous total
     * @param pNext the next value to append into the total.
     * @return the result of the algorithm of appending next into total.
     */
    int combine(int pTotal, int pNext);
}
