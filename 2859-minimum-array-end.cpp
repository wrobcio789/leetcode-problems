class Solution {
public:
    long long minEnd(int n, int x) {
        long long result = x;
        n--;

        for (int shift = 0; n > 0; shift++) {
            long long mask = 1LL << shift;

            if ((x & mask) == 0) {
                result |= (n & 1LL) << shift;
                n >>= 1;
            }
        }

        return result;
    }
};