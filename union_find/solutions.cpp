// 547 number of provinces
class UnionFind {
    std::vector<int> roots;
    std::vector<int> sizes;
    int num_components;
public:
    explicit UnionFind(int n) : roots(n), sizes(n, 1), num_components(n) {
        std::iota(roots.begin(), roots.end(), 0);
    }

    int find(int x) {
        if(roots[x] != x) {
            roots[x] = find(roots[x]);
        }
        return roots[x];
    }

    void unionize(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx == ry) return;
        if (sizes[rx] > sizes[ry]) std::swap(rx, ry);
        roots[rx] = ry;
        sizes[ry] += sizes[rx];
        --num_components;
    }

    [[nodiscard]] int components() const {return num_components;}
};

int findCircleNum(const std::vector<std::vector<int>>& isConnected) {
    const int n = std::ssize(isConnected);
    UnionFind uf(n);
    for(int i=0; i<n; ++i) {
        for(int j=i+1; j<n; ++j) {
            if(isConnected[i][j]) {
                uf.unionize(i, j);
            }
        }
    }
    return uf.components();
}

// 684 redundant connection
class Solution {

class UnionFind {
    std::vector<int> roots;
    std::vector<int> sizes;

public:
    explicit UnionFind(const int n) : roots(n), sizes(n, 1) {
        std::iota(roots.begin(), roots.end(), 0);
    }

    [[nodiscard]] int find(int x) {
        if(roots[x] != x) {
            roots[x] = find(roots[x]);
        }
        return roots[x];
    }

    [[nodiscard]] bool unionize(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if(rx == ry) return false;
        if(sizes[rx] < sizes[ry]) std::swap(rx, ry);
        roots[ry] = rx;
        sizes[rx] += sizes[ry];
        return true;
    }
};

public:
    std::vector<int> findRedundantConnection(const std::vector<std::vector<int>>& edges) {
        UnionFind uf(edges.size());
        for(const auto& edge : edges) {
            if(!uf.unionize(edge[0]-1, edge[1]-1)) {
                return edge;
            }
        } 
        return {-1, -1};
    }
};

// dfs cycle detection solution
vector<int> findRedundantConnection(vector<vector<int>>& edges) {
    const int n = static_cast<int>(edges.size());
    std::vector<std::vector<int>> adj(n+1);

    for(const auto& edge : edges) {
        const int from = edge[0];
        const int to = edge[1];

        std::vector<bool> seen(n+1, false);

        auto has_path = [&](this auto& self, const int from, const int to) -> bool {
            if(from == to) return true;
            seen[from] = true;
            for(const auto nei : adj[from]) {
                if(!seen[nei] && self(nei, to)) {
                    return true;
                }
            }
            return false;
        };

        if(!adj[from].empty() && !adj[to].empty() && has_path(from, to)) {
            return edge;
        }
        
        adj[from].push_back(to);
        adj[to].push_back(from);
    }
    
    return {-1, -1};
}

//bfs cycle detection
std::vector<int> findRedundantConnection(const std::vector<std::vector<int>>& edges) {
    const int n = static_cast<int>(edges.size());
    std::vector<std::vector<int>> adj(n + 1);

    for (const auto& edge : edges) {
        if (!adj[edge[0]].empty() && !adj[edge[1]].empty()) {
            // BFS to check if already connected
            std::vector<bool> visited(n + 1, false);
            std::queue<int> q;
            q.push(edge[0]);
            visited[edge[0]] = true;
            bool found = false;

            while (!q.empty() && !found) {
                const int curr = q.front();
                q.pop();
                if (curr == edge[1]) {
                    found = true;
                    break;
                }
                for (const int nei : adj[curr]) {
                    if (!visited[nei]) {
                        visited[nei] = true;
                        q.push(nei);
                    }
                }
            }

            if (found) return edge;
        }

        adj[edge[0]].push_back(edge[1]);
        adj[edge[1]].push_back(edge[0]);
    }

    return {-1, -1};
}