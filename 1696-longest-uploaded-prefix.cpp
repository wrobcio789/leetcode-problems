#include <vector>

class LUPrefix {
    std::vector<bool> isUploaded;
    int prefixIndex = 0;

public:
    LUPrefix(int n) : isUploaded(n, false) {}
    
    void upload(int video) {
        isUploaded[video - 1] = true;
        while(prefixIndex < isUploaded.size() && isUploaded[prefixIndex]) {
            prefixIndex++;
        }
    }
    
    int longest() {
        return prefixIndex;
    }
};
