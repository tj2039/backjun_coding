import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }
        String next() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return null;
            }
            StringBuilder sb = new StringBuilder();
            do {
                sb.append((char)c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }
    }

    static int N;
    static int[][] price;
    static int[][][] dp;

    static int dfs(int mask, int last, int lastPrice) {
        int val = dp[mask][last][lastPrice];
        if (val != -1) return val;
        int best = 1;
        for (int nxt = 0; nxt < N; nxt++) {
            if ((mask & (1 << nxt)) != 0) continue;
            int p = price[last][nxt];
            if (p >= lastPrice) {
                int cand = 1 + dfs(mask | (1 << nxt), nxt, p);
                if (cand > best) best = cand;
            }
        }
        return dp[mask][last][lastPrice] = best;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String s = fs.next();
        if (s == null) return;
        N = Integer.parseInt(s);
        price = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = fs.next();
            for (int j = 0; j < N; j++) {
                price[i][j] = line.charAt(j) - '0';
            }
        }
        dp = new int[1 << N][N][10];
        for (int mask = 0; mask < (1 << N); mask++) {
            for (int last = 0; last < N; last++) {
                Arrays.fill(dp[mask][last], -1);
            }
        }
        int ans = dfs(1, 0, 0);
        System.out.print(ans);
    }
}
