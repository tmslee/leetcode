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