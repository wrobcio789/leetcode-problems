class Solution {
    public int getIndex(ArrayReader reader) {
        int a = 0, b = reader.length() - 1;

        while (a < b) {
            final var mid = b - (b - a) / 2;

            int elems = b - a + 1;
            final var isElemsEven = elems % 2 == 0;

            int result = isElemsEven ? reader.compareSub(a, mid - 1, mid, b) : reader.compareSub(a, mid - 1, mid + 1, b);

            if (result == 0) {
                return mid;
            } else if (result == -1) {
                a = mid;
            } else {
                b = isElemsEven ? mid - 1 : mid;
            }
        }

        return a;
    }
}