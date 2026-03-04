// 43. multiply string
std::string multiply(const std::string& num1, const std::string& num2) {        
    const int s1 = std::ssize(num1);
    const int s2 = std::ssize(num2);
    std::vector<int> ans(s1+s2, 0);

    for(int i=s1-1; i>=0; --i) {
        for(int j=s2-1; j>=0; --j) {
            const int prod = (num1[i]-'0')*(num2[j]-'0');
            const int p1 = i+j;
            const int p2 = i+j+1;

            const int sum = prod + ans[p2];
            ans[p2] = sum%10;
            ans[p1] += sum/10;
        }
    }

    std::string result;
    for(const int digit : ans) {
        if(result.empty() && digit == 0) continue;
        result += static_cast<char>(digit + '0');
    }
    return result.empty() ? "0" : result;
}

//  48 rotate image
void rotate(vector<vector<int>>& matrix) {
    const int n = static_cast<int>(matrix.size());
    const bool is_odd = n%2;

    // do the quadrants then the center lines if odd
    auto rotate_cell = [&](const int r, const int c) -> void {
        int prev = matrix[r][c];
        int cr = r;
        int cc = c;
        for(int i=0; i<4; ++i){
            const int nr = cc;
            const int nc = n-1-cr; 
            const int tmp = matrix[nr][nc];
            matrix[nr][nc] = prev;
            prev = tmp;
            cr = nr;
            cc = nc;
        }
    };

    for(int r=0; r<n/2; ++r) {
        for(int c=0; c<n/2; ++c) {
            rotate_cell(r, c);
        }
    }

    if(is_odd) {
        for(int r=0; r<n/2; ++r) {
            rotate_cell(r, n/2);
        }
    }
}
// TRANSPOSE AND REVERSE SOLUTION
void rotate(std::vector<std::vector<int>>& matrix) {
    const int n = static_cast<int>(matrix.size());

    // Transpose
    for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
            std::swap(matrix[i][j], matrix[j][i]);
        }
    }

    // Reverse each row
    for (auto& row : matrix) {
        std::reverse(row.begin(), row.end());
    }
}

// 50 pow(x,n)
double myPow(const double x, const int n) {
    long long exp = std::abs(static_cast<long long>(n));
    double base = x;
    double res = 1.0;
    // say exp = 13. in binary, 1101 if we say each bit represnet powers:
    //   1   1   0    1
    //  x^8 x^4 x^2  x^1
    // result = product of all valid bits x^(8+4+1) = x^13
    while(exp > 0) {
        if(exp%2 == 1) { //essentially checking if bit is 1
            res *= base;
        }
        base *= base;
        exp /= 2; //essentially bitshifting to right
    }
    return (n < 0) ? 1.0/res : res;
}

//resurvie solution
double myPow(const double x, const int n) {
    long long exp = std::abs(static_cast<long long>(n));

    auto helper = [&](this auto& self, const long long pow) -> double {
        if(pow == 0) return 1.0;
        if(pow%2 == 1) return x*self(pow-1);
        const double half = self(pow/2);
        return half*half;
    };

    return (n < 0) ? 1.0/helper(exp) : helper(exp);
}