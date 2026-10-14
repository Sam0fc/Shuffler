package me.xemor.shuffler.game.crafting;

/**
 * Where an item's weight came from: weights[i] of it is from the primitive (raw material) prims[i], by Material ordinal.
 * units[i] is how many units of that primitive one unit of the item costs when it is made that way, and factors[i]
 * is the bulk gathering discount already included in the weight of that origin. jobs[i] is how many furnace smelts one
 * unit of the item takes when made that way, and depths[i] the longest chain of smelts that must follow one another.
 * The largest few origins are kept sorted (top*) because every recipe using the item needs them.
 */
final class Lineage {

    /** Most origins of one ingredient tried per recipe. */
    static final int MAX_ORIGINS = 5;

    final int[] prims;
    final double[] weights;
    final double[] units;
    final double[] factors;
    final double[] jobs;
    final double[] depths;
    final double total;
    final int topCount;
    final int[] topPosition = new int[MAX_ORIGINS];
    final double[] topFraction = new double[MAX_ORIGINS];

    Lineage(int[] prims, double[] weights) {
        this(prims, weights, new double[prims.length], ones(prims.length));
    }

    Lineage(int[] prims, double[] weights, double[] units, double[] factors) {
        this(prims, weights, units, factors, new double[prims.length], new double[prims.length]);
    }

    Lineage(int[] prims, double[] weights, double[] units, double[] factors, double[] jobs, double[] depths) {
        this.prims = prims;
        this.jobs = jobs;
        this.depths = depths;
        this.weights = weights;
        this.units = units;
        this.factors = factors;
        double sum = 0;
        for (double weight : weights) sum += weight;
        this.total = sum;
        int count = Math.min(MAX_ORIGINS, prims.length);
        boolean[] taken = new boolean[prims.length];
        for (int rank = 0; rank < count; rank++) {
            int best = -1;
            for (int i = 0; i < prims.length; i++) {
                if (taken[i]) continue;
                if (best == -1 || weights[i] > weights[best] || (weights[i] == weights[best] && prims[i] < prims[best])) best = i;
            }
            taken[best] = true;
            topPosition[rank] = best;
            topFraction[rank] = weights[best] / sum;
        }
        this.topCount = count;
    }

    private static double[] ones(int length) {
        double[] ones = new double[length];
        java.util.Arrays.fill(ones, 1);
        return ones;
    }

    double topFactor(int rank) {
        return factors[topPosition[rank]];
    }

    double topUnits(int rank) {
        return units[topPosition[rank]];
    }

    double topJobs(int rank) {
        return jobs[topPosition[rank]];
    }

    double topDepth(int rank) {
        return depths[topPosition[rank]];
    }

    int topPrimitive(int rank) {
        return prims[topPosition[rank]];
    }

    /**
     * @return the largest change to any origin's weight from {@code before} to {@code after}, relative to {@code after}'s total
     */
    static double difference(Lineage before, Lineage after, double[] scratch) {
        if (before == null) return Double.POSITIVE_INFINITY;
        for (int i = 0; i < before.prims.length; i++) scratch[before.prims[i]] = before.weights[i];
        double largest = 0;
        for (int i = 0; i < after.prims.length; i++) {
            largest = Math.max(largest, Math.abs(after.weights[i] - scratch[after.prims[i]]));
            scratch[after.prims[i]] = 0;
        }
        for (int i = 0; i < before.prims.length; i++) {
            largest = Math.max(largest, scratch[before.prims[i]]);
            scratch[before.prims[i]] = 0;
        }
        return largest / after.total;
    }
}
