import java.util.List;

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        final var firstArray = arrays.get(0);

        int minSoFar = firstArray.get(0);
        int maxSoFar = firstArray.get(firstArray.size() - 1);
        int result = 0;

        for (int i = 1; i < arrays.size(); i++) {
            final var array = arrays.get(i);
            final var min = array.get(0);
            final var max = array.get(array.size() - 1);

            if (maxSoFar - min > result) {
                result = maxSoFar - min;
            }

            if (max - minSoFar > result) {
                result = max - minSoFar;
            }

            if (min < minSoFar) {
                minSoFar = min;
            }

            if (max > maxSoFar) {
                maxSoFar = max;
            }
        }

        return result;

    }
}