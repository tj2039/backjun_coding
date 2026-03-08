import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int T = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int k = fs.nextInt();
            int[] time = new int[n + 1];
            for (int i = 1; i <= n; i++) time[i] = fs.nextInt();

            int[] indeg = new int[n + 1];
            int[] head = new int[n + 1];
            Arrays.fill(head, -1);
            int[] to = new int[k];
            int[] next = new int[k];
            int edge = 0;
            for (int i = 0; i < k; i++) {
                int x = fs.nextInt();
                int y = fs.nextInt();
                to[edge] = y;
                next[edge] = head[x];
                head[x] = edge++;
                indeg[y]++;
            }
            int w = fs.nextInt();

            long[] dp = new long[n + 1];
            int[] q = new int[n];
            int front = 0, rear = 0;
            for (int i = 1; i <= n; i++) {
                if (indeg[i] == 0) {
                    dp[i] = time[i];
                    q[rear++] = i;
                }
            }

            while (front < rear) {
                int v = q[front++];
                for (int e = head[v]; e != -1; e = next[e]) {
                    int u = to[e];
                    long cand = dp[v] + time[u];
                    if (cand > dp[u]) dp[u] = cand;
                    if (--indeg[u] == 0) q[rear++] = u;
                }
            }

            sb.append(dp[w]);
            if (tc + 1 < T) sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1) return -1;
                c = read();
            }
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
