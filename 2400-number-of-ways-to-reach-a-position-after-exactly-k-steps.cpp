#include <cstdlib>

static const int MOD = 1e9 + 7;

class Solution {
public:
    int numberOfWays(int startPos, int endPos, int k) {
        const int n = std::abs(endPos - startPos);
        if (n > k) {
            return 0;
        }

        int leftSteps = k - n;
        if (leftSteps % 2 == 1) {
            return 0;
        }

        const int forwardSteps = n + leftSteps/2;
        const int backwardSteps = leftSteps/2;

        return ((factorialModulo(forwardSteps + backwardSteps) * inverseFactorialModulo(forwardSteps) % MOD)
                * inverseFactorialModulo(backwardSteps)) % MOD;
    }

private:
    long long inverseFactorialModulo(int k) {
        long long factorial = factorialModulo(k);
        return inverseModulo(factorial);
    }

    long long factorialModulo(int k) {
        long long result = 1;
        for (int i = 2; i <= k; i++) {
            result *= i;
            result %= MOD;
        }

        return result;
    }

    long long inverseModulo(long long k) {
        return modExponentiation(k, MOD-2, MOD); // Using Fermat's little theorem
    }

    long long modExponentiation(long long base, long long exp, long long mod) {
        long long result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp & 1 == 1) {
                result = (result * base) % mod;
            }

            exp = exp >> 1; 
            base = (base * base) % mod;
        }
        return result;
    }
};