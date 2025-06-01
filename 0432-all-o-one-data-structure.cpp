#include <string>
#include <iostream>
#include <unordered_map>
#include <unordered_set>


class DoubleLinkedListNode {
public:
    DoubleLinkedListNode* previous = nullptr;
    DoubleLinkedListNode* next = nullptr;
    std::unordered_set<std::string> keys;
    int count = 0;

    void addKey(std::string key) {
        keys.insert(key);
    }

    void removeKey(std::string key) {
        keys.erase(key);
    }

    int keyCount() {
        return keys.size();
    }

    std::string anyKey() {
        return *keys.begin();
    }

};

class DoubleLinkedList {
    DoubleLinkedListNode* first = nullptr;
    DoubleLinkedListNode* last = nullptr;

public:
    DoubleLinkedList() {}

    ~DoubleLinkedList() {
        auto node = first;
        while(node != nullptr) {
            auto nextNode = node->next;
            delete node;
            node = nextNode;
        }
    }

    void remove(DoubleLinkedListNode* node) {
        if(node -> previous != nullptr) {
            node->previous->next = node->next;
        }

        if(node -> next != nullptr) {
            node->next->previous = node->previous;
        }
        
        if(node == first) {
            first = node->next;
        }

        if(node == last) {
            last = node -> previous;
        }

        delete node;
    }

    DoubleLinkedListNode* insertAfter(DoubleLinkedListNode* node) {
        auto* newNode = new DoubleLinkedListNode;
        newNode->next = node->next;
        newNode->previous = node;
        if(node->next != nullptr) {
            node->next->previous = newNode;
        }
        node->next = newNode;

        if(node == last) {
            last = newNode;
        }

        return newNode;
    }

    
    DoubleLinkedListNode* insertBefore(DoubleLinkedListNode* node) {
        auto* newNode = new DoubleLinkedListNode;
        newNode->next = node;
        newNode->previous = node->previous;
        if(node->previous != nullptr) {
            node->previous->next = newNode;
        }
        node->previous = newNode;

        if(node == first) {
            first = newNode;
        }

        return newNode;
    }

    DoubleLinkedListNode* insertFirst() {
        auto* newNode = new DoubleLinkedListNode;
        
        newNode->next = first;
        if(first != nullptr) {
            first->previous = newNode;
        }

        first = newNode;
        if (last == nullptr) {
            last = newNode;
        }

        return newNode;
    }

    DoubleLinkedListNode* getFirst() {
        return first;
    }

    DoubleLinkedListNode* getLast() {
        return last;
    }

    void printStruct() {
        auto node = first;
        while(node != nullptr) {
            auto nextNode = node->next;
            std::cout<<"address: " << node << ", prev: " << node->previous << ", next:" << node->next << ", count" << node->count << ", keys:";
            for(const auto& key : node->keys) {
                std::cout << key << ' ';
            }
            std::cout << std::endl;
            node = nextNode;
        }
    }

};


class AllOne {
private:
    DoubleLinkedList nodesOrderedByCount;
    std::unordered_map<std::string, DoubleLinkedListNode*> keyToNodeMap;

public:
    AllOne() {
        
    }
    
    void inc(std::string key) {
        if(keyToNodeMap.count(key) != 0) {
            auto node = keyToNodeMap[key];
            auto nextNode = getNextNode(node);
            nextNode->addKey(key);
            keyToNodeMap[key] = nextNode;
            removeKey(node, key);
        } else {
            auto targetNode = isFirstNodeForCountOne() ? nodesOrderedByCount.getFirst() : nodesOrderedByCount.insertFirst();
            targetNode->count = 1;
            targetNode->addKey(key);
            keyToNodeMap[key] = targetNode;
        }
    }
    
    void dec(std::string key) {
        if(keyToNodeMap.count(key) != 0) {
            auto node = keyToNodeMap[key];
            if(node->count > 1) {
                auto previous = getPreviousNode(node);
                previous->addKey(key);
                keyToNodeMap[key] = previous;
            }else {
                keyToNodeMap.erase(key);
            }

            removeKey(node, key);
        }
    }
    
    std::string getMaxKey() {
        auto node = nodesOrderedByCount.getLast();
        return node == nullptr ? "" : node->anyKey();
    }
    
    std::string getMinKey() {
        auto node = nodesOrderedByCount.getFirst();
        return node == nullptr ? "" : node->anyKey();
    }

private:
    DoubleLinkedListNode* getNextNode(DoubleLinkedListNode* node) {
        auto currentCount = node->count;
        auto next = node->next;
        if(next == nullptr || next->count != currentCount + 1) {
            auto result = nodesOrderedByCount.insertAfter(node);
            result->count = currentCount + 1;
            return result;
        }
        return next;
    }

    DoubleLinkedListNode* getPreviousNode(DoubleLinkedListNode* node) {
        auto currentCount = node->count;
        auto previous = node->previous;
        if(previous == nullptr || previous->count != currentCount - 1) {
            auto result = nodesOrderedByCount.insertBefore(node);
            result->count = currentCount - 1;
            return result;
        }
        return previous;
    }

    bool isFirstNodeForCountOne() {
        auto firstNode = nodesOrderedByCount.getFirst();
        return firstNode != nullptr && firstNode->count == 1;
    }

    void removeKey(DoubleLinkedListNode* node, std::string key) {
        node->removeKey(key);
        if(node->keyCount() == 0) {
            nodesOrderedByCount.remove(node);
        } 
    }

    void printStruct() {
        nodesOrderedByCount.printStruct();
        for(const auto& elem : keyToNodeMap){
            std::cout << "[" << elem.first << ": " << elem.second << "] ";
        }
        std::cout<<std::endl;
    }

};