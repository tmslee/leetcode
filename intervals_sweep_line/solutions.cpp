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