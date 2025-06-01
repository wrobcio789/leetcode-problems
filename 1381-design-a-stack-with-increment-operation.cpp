#include <vector>

class CustomStack {
private:
    std::vector<int> array;
    int maxSize;

public:
    CustomStack(int maxSize) {
        this->maxSize = maxSize;
        array.reserve(maxSize);    
    }
    
    void push(int x) {
        if(array.size() < maxSize) {
            array.push_back(x);
        }
    }
    
    int pop() {
        if(array.size() == 0) {
            return -1;
        }

        int result = array.back();
        array.pop_back();
        return result;

    }
    
    void increment(int k, int val) {
        for(int i = 0; i < min(k, array.size()); i++) {
            array[i] += val;
        }
    }

private:
    int min(int a, int b) {
        return a < b ? a : b;
    }
};
