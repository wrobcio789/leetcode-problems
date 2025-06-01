

class MyCircularDeque {
private:
    int* elements;
    unsigned int capacity;
    unsigned int size = 0;
    int frontIndex = 1;
    int lastIndex = 0;

public:
    MyCircularDeque(int k) {
        elements = new int[k];
        capacity = k;
    }

    ~MyCircularDeque() {
        delete[] elements;
    }
    
    bool insertFront(int value) {
        if (isFull()) {
            return false;
        }
        
        frontIndex = (frontIndex + capacity - 1) % capacity;
        elements[frontIndex] = value;
        size++;
        return true;
    }
    
    bool insertLast(int value) {
        if (isFull()) {
            return false;
        }

        lastIndex = (lastIndex + 1) % capacity;
        elements[lastIndex] = value;
        size++;
        return true;
    }
    
    bool deleteFront() {
        if (isEmpty()) {
            return false;
        }

        frontIndex = (frontIndex + 1) % capacity;
        size--;
        return true;
    }
    
    bool deleteLast() {
        if (isEmpty()) {
            return false;
        }

        lastIndex = (lastIndex + capacity - 1) % capacity;
        size--;
        return true;
    }

    
    int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return elements[frontIndex];        
    }
    
    int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return elements[lastIndex];
    }
    
    bool isEmpty() {
        return size == 0;
    }
    
    bool isFull() {
        return size == capacity;
    }
};