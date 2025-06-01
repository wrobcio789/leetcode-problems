#include <climits>

class Solution {
public:
    int minimizeSet(int div1, int div2, int n1, int n2) {
        long long left = n1 + n2, right = INT_MAX;
        const long long divGcd = lcm(div1, div2);

        while(left < right) {
            const long long mid = (left + right) / 2;

            int potentialNumsInArr1 = mid - mid/div1;
            int potentialNumsInArr2 = mid - mid/div2;
            int totalNumsExcludedDivisible = mid - mid/divGcd;

            if(potentialNumsInArr1 >= n1 && potentialNumsInArr2 >= n2 && totalNumsExcludedDivisible >= n1 + n2) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

private:
    long long lcm(int a, int b) {
        return (long long)(a) * (b / gcd(a, b));
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a%b);
    }
};