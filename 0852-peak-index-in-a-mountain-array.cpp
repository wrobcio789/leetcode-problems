#include <vector>

class Solution {
public:
    int peakIndexInMountainArray(std::vector<int>& arr) {
        const int n = arr.size();

        int left = 0, right = n - 1;
        while (left < right) {
            const int mid = (right + left)/2;

            if(arr[mid-1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid-1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
        
    }
};