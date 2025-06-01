#include <vector>

template<int N>
class PrimeMemory {
private:
    int primes[N];
    int size;

public:
    constexpr PrimeMemory() : primes() {
        size = 0;
        for (int i = 2; i <= N; i++){
            if (isPrime(i)) {
                primes[size++] = i;
            }
        }
    }

    int findBiggestPrimeSmallerThanK(int k) const {
        k--;
        int left = 0;
        int right = size;

        int result = 0;
        while (true) {
            const int mid = (left + right)/2;
            const int prevLeft = left;

            if(primes[mid] > k) {
                right = mid;
            } else {
                result = primes[mid];
                left = mid;
            }

            if (mid == prevLeft) {
                break;
            }
        }

        return result;
    }

private:
    constexpr int isPrime(int num) {
        int k = 2;
        while(k * k <= num) {
            if (num % k == 0) {
                return false;
            }
            k++;
        }

        return true;
    }
};

class Solution {
    static constexpr int LIMIT = 1000;
    static constexpr PrimeMemory<LIMIT> PRIMES = PrimeMemory<LIMIT>();

public:
    bool primeSubOperation(std::vector<int>& nums) {
        int previousNum = 0;
        for(int num : nums) {
            int base = num - previousNum;
            int prime = PRIMES.findBiggestPrimeSmallerThanK(base);

            int numCandidate = num - prime;
            if(numCandidate <= previousNum) {
                return false;
            }

            previousNum = numCandidate;
        }

        return true;
    }
};