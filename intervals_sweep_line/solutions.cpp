// 56 merge intervals
std::vector<std::vector<int>> merge(std::vector<std::vector<int>>& intervals) {
    if(intervals.empty()) return {};
    std::sort(intervals.begin(), intervals.end());

    std::vector<std::vector<int>> ans;
    ans.push_back(intervals[0]);
    
    for(int i=1; i<std::ssize(intervals); ++i){
        auto& last = ans.back();
        if(intervals[i][0] > last[1]) {
            ans.push_back(intervals[i]);
        } else {
            last[1] = std::max(last[1], intervals[i][1]);
        }
    }
    return ans;
}

// 57 insert interval
std::vector<std::vector<int>> insert(const std::vector<std::vector<int>>& intervals, std::vector<int>& newInterval) {
    std::vector<std::vector<int>> ans;
    bool inserted = false;
    const int n = static_cast<int>(intervals.size());
    int i = 0;
    while(i < n && intervals[i][1] < newInterval[0]) {
        ans.push_back(intervals[i]);
        ++i;
    }
    while(i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = std::min(newInterval[0], intervals[i][0]);
        newInterval[1] = std::max(newInterval[1], intervals[i][1]);
        ++i;
    }
    ans.push_back(newInterval);
    while(i < n) {
        ans.push_back(intervals[i]);
        ++i;
    }
    return ans;
}