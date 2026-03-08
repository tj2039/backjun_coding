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

    static final int INF = 1_000_000_000;

    static int solve(int[] a, int[] b, int w, int n, int wrapMask) {
        int[] dpPrev = {INF, INF, INF, INF};
        dpPrev[wrapMask] = 0;
        int prevTop = a[n - 1];
        int prevBot = b[n - 1];

        for (int i = 0; i < n; i++) {
            int curTop = a[i];
            int curBot = b[i];
            int[] dpCur = {INF, INF, INF, INF};

            for (int p = 0; p < 4; p++) {
                int base = dpPrev[p];
                if (base >= INF) continue;
                if ((p & 1) != 0 && prevTop + curTop > w) continue;
                if ((p & 2) != 0 && prevBot + curBot > w) continue;

                int remMask = (~p) & 3;
                for (int t = 0; t < 4; t++) {
                    if ((t & p) != 0) continue;
                    if ((t & ~remMask) != 0) continue;

                    int coverMask = remMask & (~t);
                    int extra = Integer.bitCount(p);
                    if (coverMask == 3) {
                        extra += (curTop + curBot <= w) ? 1 : 2;
                    } else {
                        extra += Integer.bitCount(coverMask);
                    }
                    if (dpCur[t] > base + extra) {
                        dpCur[t] = base + extra;
                    }
                }
            }

            dpPrev = dpCur;
            prevTop = curTop;
            prevBot = curBot;
        }

        return dpPrev[wrapMask];
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int T = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = fs.nextInt();
            int w = fs.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) a[i] = fs.nextInt();
            for (int i = 0; i < n; i++) b[i] = fs.nextInt();
            int ans = INF;
            for (int mask = 0; mask < 4; mask++) {
                int cand = solve(a, b, w, n, mask);
                if (cand < ans) ans = cand;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }
}
