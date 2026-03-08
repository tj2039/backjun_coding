import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream in) { this.in = in; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    static int n;
    static int[] x;
    static int[] y;
    static long totalX, totalY;
    static double best;

    static void dfs(int idx, int cnt, long selX, long selY) {
        if (cnt == n / 2) {
            long dx = totalX - 2L * selX;
            long dy = totalY - 2L * selY;
            double dist = Math.hypot(dx, dy);
            if (dist < best) best = dist;
            return;
        }
        if (idx == n) return;
        if (n - idx < (n / 2 - cnt)) return;

        dfs(idx + 1, cnt + 1, selX + x[idx], selY + y[idx]);
        dfs(idx + 1, cnt, selX, selY);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int T = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            n = fs.nextInt();
            x = new int[n];
            y = new int[n];
            totalX = totalY = 0;
            for (int i = 0; i < n; i++) {
                int xi = fs.nextInt();
                int yi = fs.nextInt();
                x[i] = xi;
                y[i] = yi;
                totalX += xi;
                totalY += yi;
            }
            best = Double.POSITIVE_INFINITY;
            dfs(0, 0, 0, 0);
            sb.append(String.format(java.util.Locale.US, "%.6f\n", best));
        }
        System.out.print(sb.toString());
    }
}
