// walls and gates
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