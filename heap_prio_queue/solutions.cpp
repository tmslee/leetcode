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

// 767 reorganize string 
string reorganizeString(const std::string& s) {
    const int n = static_cast<int>(s.size());
    std::vector<int> counts(26, 0);
    for(const char c : s) {
        ++counts[c-'a'];
    }
    auto cmp = [&](const int i1, const int i2)->bool{
        return counts[i1] < counts[i2];
    };
    std::priority_queue<int, vector<int>, decltype(cmp)> pq(cmp);
    for(int i=0; i<26; ++i) {
        if(counts[i] > 0) pq.push(i);
    }
    std::string ans;
    while(!pq.empty()) {
        if(pq.size() >= 2) {
            int i1 = pq.top();
            pq.pop();
            int i2 = pq.top();
            pq.pop();
            if(--counts[i1] > 0) pq.push(i1);
            if(--counts[i2] > 0) pq.push(i2);
            ans += 'a'+i1;
            ans += 'a'+i2;
        } else {
            int i =pq.top(); 
            pq.pop();
            if(!ans.empty() && ans.back() == 'a'+i) return "";
            ans += 'a'+i;
            if(--counts[i] > 0) pq.push(i);
        }
    }
    return ans;
}