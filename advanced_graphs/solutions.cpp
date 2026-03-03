// 286 walls and gates
void wallsAndGates(std::vector<std::vector<int>>& rooms) {
    if(rooms.empty()) return;

    static constexpr auto kEmpty = std::numeric_limits<int>::max();
    static constexpr std::array<std::pair<int,int>, 4> offsets = {{
        {1,0}, {-1,0}, {0,1}, {0,-1}
    }};

    const int rows = std::ssize(rooms);
    const int cols = std::ssize(rooms[0]);

    auto is_valid = [&](int r, int c) -> bool {
        if(r<0 || c<0 || r>=rows || c>=cols) return false;
        return rooms[r][c] == INT_MAX;
    };

    using Cell = std::pair<int,int>;
    std::queue<Cell> q;

    for(int r=0; r<rows; ++r) {
        for(int c=0; c<cols; ++c) {
            if(rooms[r][c] == 0) {
                q.push({r,c});
            }
        }
    }

    while(!q.empty()) {
        const auto [r, c] = q.front(); q.pop();
        for(const auto& [offsetr, offsetc] : offsets) {
            const int nr = r + offsetr;
            const int nc = c + offsetc;
            if(nr>=0 && nc>=0 && nr<rows && nc<cols && rooms[nr][nc] == kEmpty) {
                rooms[nr][nc] = rooms[r][c] + 1;
                q.push({nr, nc});
            }
        }

    }
}

// 332 reconstruct itinerary
std::vector<std::string> findItinerary(const std::vector<std::vector<std::string>>& tickets) {
    // question promises that an eulerian path exists we just need to return it:
    // we add the current node AFTER exhaustive traversal (while consuming edges) of children 
    // i.e. once we hit a dead end, we return to the parent node and continue
    using Pq = std::priority_queue<std::string, std::vector<std::string>, std::greater<std::string>>;
    std::unordered_map<std::string, Pq> connections;
    for(const auto& ticket : tickets) {
        connections[ticket[0]].push(ticket[1]);
    }

    std::vector<std::string> ans;
    auto traverse = [&](this auto& self, const std::string& curr) -> void {
        auto& children = connections[curr];
        while(!children.empty()) {
            auto child = children.top();
            children.pop();
            self(child);
        }
        ans.push_back(curr);
    };
    traverse("JFK");
    std::reverse(ans.begin(), ans.end());
    return ans;
}

// ITERATIVE SOLUTION
std::vector<std::string> findItinerary(const std::vector<std::vector<std::string>>& tickets) {
    // question promises that an eulerian path exists we just need to return it:
    // we add the current node AFTER exhaustive traversal (while consuming edges) of children 
    // i.e. once we hit a dead end, we return to the parent node and continue
    using Pq = std::priority_queue<std::string, std::vector<std::string>, std::greater<std::string>>;
    std::unordered_map<std::string, Pq> connections;
    for(const auto& ticket : tickets) {
        connections[ticket[0]].push(ticket[1]);
    }

    std::vector<std::string> ans;
    std::stack<std::string> st;
    st.push("JFK");
    while(!st.empty()) {
        auto& children = connections[st.top()];
        if(!children.empty()) {
            auto child = children.top();
            children.pop();
            st.push(std::move(child));
        } else {
            ans.push_back(st.top());
            st.pop();
        }
    }

    std::reverse(ans.begin(), ans.end());
    return ans;
}

// 743 network delay time
int networkDelayTime(const std::vector<std::vector<int>>& times, const int n, const int k) {
    using P = std::pair<int,int>;
    
    std::vector<std::vector<P>> graph(n);
    for(const auto& time : times) {
        graph[time[0]-1].emplace_back(time[1]-1, time[2]);
    }

    const int max_val = std::numeric_limits<int>::max();
    std::vector<int> dists(n, max_val);

    dists[k-1] = 0;
    using PQ = std::priority_queue<P, std::vector<P>, std::greater<P>>;

    PQ pq;
    pq.push({0, k-1});
    while(!pq.empty()) {
        const auto [curr_dist, curr_node] = pq.top();
        pq.pop();
        if(curr_dist > dists[curr_node]) continue;

        for(const auto [dest, distance] : graph[curr_node]) {
            const int new_dist = curr_dist + distance;
            if(new_dist < dists[dest]) {
                dists[dest] = new_dist;
                pq.push({new_dist, dest});
            }
        }
    }

    const int ans = *std::ranges::max_element(dists);
    return ans == max_val ? -1 : ans;
}