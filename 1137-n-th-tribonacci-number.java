class Solution {
    public int tribonacci(int n) {
        int a = 0, b = 1, c = 1;
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        int result = a + b + c;
        for (int i = 3; i < n; i++) {
            final var oldResult = result;
            a = b;
            b = c;
            c = oldResult;
            result = a + b + c;
        }

        return result;
    }
}