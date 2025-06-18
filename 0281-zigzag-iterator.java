import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZigzagIterator {
    private final List<VectorReader> readers;
    int vectorIndex;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.readers = Stream.of(new VectorReader(v1), new VectorReader(v2))
                .filter(VectorReader::hasNext)
                .collect(Collectors.toCollection(ArrayList::new));
        this.vectorIndex = 0;

    }

    public int next() {
        final var reader = readers.get(vectorIndex);
        final var result = reader.read();

        if (!reader.hasNext()) {
            readers.remove(vectorIndex);
        } else {
            vectorIndex++;
        }

        if (readers.size() != 0) {
            vectorIndex %= readers.size();
        }

        return result;
    }

    public boolean hasNext() {
        return !readers.isEmpty();
    }

    private class VectorReader {
        private final List<Integer> vector;
        private int index;

        public VectorReader(List<Integer> vector) {
            this.vector = vector;
            this.index = 0;
        }

        public int read() {
            return vector.get(index++);
        }

        public boolean hasNext() {
            return index < vector.size();
        }
    }
}