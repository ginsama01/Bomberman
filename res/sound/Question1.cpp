#include<bits/stdc++.h>

using namespace std;


int n, m, l[1001][1001];
string a, b;

int xuli(int i, int j) {
    if (l[i][j] != -1) {
        return f[i][j];
    }
    if (i == n && j == m) {
        return 1;
    }
    if (i == n) {
        return 0;
    }
    if (j == m && isupper(a[i])) {
        return 0;
    }
    int so = 0;
    if (j < m && a[i] == b[j]) {
        so = max(so, xuli(i + 1, j + 1));
    }
    if (!isupper(a[i])) {
        so = max(so, xuli(i + 1, j));
        if (j < m && char(a[i] - 32) == b[j]) {
            so = max(so, xuli(i + 1, j + 1));
        }
    }
    f[i][j] = so;
    return so;
}

int main() {
    int q;
    cin >> q;
    for (int i = 1; i <= q; ++i)
    {
        cin >> a >> b;
        n = a.size();
        m = b.size();
        for (int u = 0; u <= n; ++u)
            for (int v = 0; v <= m; ++v)
             f[u][v] = -1;
        if (xuli(0,0)) cout << "YES" << '\n';
        else cout << "NO" << '\n';
    }
    return 0;
}
