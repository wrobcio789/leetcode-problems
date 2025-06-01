#include <string>

static const int DIGITS_SIZE = 3;

class Solution {
public:
    int takeCharacters(const std::string& s, int k) {
        const int length = s.size();
        int leftChars[DIGITS_SIZE] = {}, rightChars[DIGITS_SIZE] = {};

        if (k == 0) {
            return 0;
        }

        int left = 0, right = 0;
        while (left < length && !hasEnough(leftChars, rightChars, k)) {
            leftChars[s[left] - 'a']++;
            left++;
        }

        if (!hasEnough(leftChars, rightChars, k)) {
            return -1;
        }

        int result = left;
        while (left > 0) {
            const int leftChar = s[left - 1];
            leftChars[leftChar - 'a']--;

            while (right < length - left && !hasEnough(leftChars, rightChars, k)) {
                rightChars[s[length - right - 1] - 'a']++;
                right++;
            }

            if (hasEnough(leftChars, rightChars, k)) {
                result = std::min(result, left + right - 1);
            }

            left--;
        }

        return result;

    }

private:
    bool hasEnough(int* leftChars, int* rightChars, int k) {
        for (int i = 0; i < DIGITS_SIZE; i++) {
            if (leftChars[i] + rightChars[i] < k) {
                return false;
            }
        }

        return true;
    }
};