// 295 find median from data stream
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

// 703 kth largest element in stream
class KthLargest {

std::priority_queue<int, std::vector<int>, std::greater<int>> pq_; 
const int k_;

public:
    explicit KthLargest(const int k, const std::vector<int>& nums) : k_(k) {
        for(const int n : nums) {
            pq_.push(n);
        }
        while(static_cast<int>(pq_.size()) > k_) {
            pq_.pop();
        }
    }
    
    [[nodiscard]] int add(int val) {
        pq_.push(val);
        if(static_cast<int>(pq_.size()) > k_) {
            pq_.pop();
        }
        return pq_.top();
    }
};