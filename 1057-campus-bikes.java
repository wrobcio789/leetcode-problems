import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        final var n = workers.length;
        final var m = bikes.length;

        final var distances = new BikeDistancePair[n][m];

        for (int i = 0; i < n; i++) {
            final var worker = workers[i];

            for (int j = 0; j < m; j++) {
                final var bike = bikes[j];
                distances[i][j] = new BikeDistancePair(j, distance(worker, bike));
            }

            Arrays.sort(distances[i], Comparator.comparingInt(BikeDistancePair::distance)
                    .thenComparing(BikeDistancePair::bikeIndex));
        }


        final var queue = new PriorityQueue<>(Comparator.comparingInt(WorkerBikeTuple::distance)
                .thenComparing(WorkerBikeTuple::worker)
                .thenComparing(WorkerBikeTuple::bike));

        for (int i = 0; i < n; i++) {
            final var pair = distances[i][0];
            queue.add(new WorkerBikeTuple(i, pair.bikeIndex(), pair.distance(), 0));
        }


        final var assingment = new int[n];
        final var bikesTaken = new boolean[m];

        while (!queue.isEmpty()) {
            final var tuple = queue.poll();

            if (bikesTaken[tuple.bike()]) {
                final var nextPair = distances[tuple.worker()][tuple.pairIndex() + 1];
                queue.add(new WorkerBikeTuple(tuple.worker(), nextPair.bikeIndex(), nextPair.distance(), tuple.pairIndex() + 1));
                continue;
            }

            bikesTaken[tuple.bike] = true;
            assingment[tuple.worker()] = tuple.bike();
        }

        return assingment;
    }

    private static int distance(int[] worker, int[] bike) {
        return Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
    }

    private record BikeDistancePair(int bikeIndex, int distance) {
    }

    private record WorkerBikeTuple(int worker, int bike, int distance, int pairIndex) {
    }
}