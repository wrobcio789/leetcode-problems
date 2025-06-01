import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class SmallestInfiniteSet {
    int index = 1;
    private final PriorityQueue<Integer> heap = new UniqueHeap<>();

    public SmallestInfiniteSet() {

    }

    public int popSmallest() {
        if (!heap.isEmpty()) {
            return heap.poll();
        }
        return index++;
    }

    public void addBack(int num) {
        if (num < index) {
            heap.add(num);
        }
    }

    private static class UniqueHeap<T> extends PriorityQueue<T> {
        private final Set<T> elements = new HashSet<>();

        @Override
        public boolean offer(T e) {
            if (elements.contains(e)) return false;
            boolean added = super.offer(e);
            if (added) elements.add(e);
            return added;
        }

        @Override
        public T poll() {
            T e = super.poll();
            if (e != null) {
                elements.remove(e);
            }
            return e;
        }

        @Override
        public boolean remove(Object o) {
            boolean removed = super.remove(o);
            if (removed) elements.remove(o);
            return removed;
        }

        @Override
        public void clear() {
            super.clear();
            elements.clear();
        }
    }
}
