import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {

    private final int size;
    private final Queue<Integer> values = new LinkedList<>();
    int sumSoFar = 0;

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        sumSoFar += val;
        values.add(val);

        if (values.size() > size) {
            sumSoFar -= values.poll();
        }

        return (double) sumSoFar / values.size();
    }
}