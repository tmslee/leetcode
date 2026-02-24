// find median from data stream
class MedianFinder {
public:
    using MinHeap = std::priority_queue<int, vector<int>, greater<int>>;
    using MaxHeap = std::priority_queue<int, vector<int>, less<int>>;

    MinHeap upper;
    MaxHeap lower;

    MedianFinder() = default;

    void addNum(int num) {
        lower.push(num);
        upper.push(lower.top());
        lower.pop();
        if(upper.size() > lower.size()+1) {
            lower.push(upper.top());
            upper.pop();
        }
    }
    
    double findMedian() {
        const auto tot = upper.size() + lower.size();
        if(tot%2 == 0) {
            return (upper.top() + lower.top())/2.0;
        }
        return upper.top();
    }
};