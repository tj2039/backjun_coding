import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            long x1 = fs.nextInt();
            long y1 = fs.nextInt();
            long x2 = fs.nextInt();
            long y2 = fs.nextInt();
            int n = fs.nextInt();
            int count = 0;
            for (int i = 0; i < n; i++) {
                long cx = fs.nextInt();
                long cy = fs.nextInt();
                long r = fs.nextInt();
                boolean s = inside(x1, y1, cx, cy, r);
                boolean e = inside(x2, y2, cx, cy, r);
                if (s != e) count++;
            }
            sb.append(count);
            if (tc + 1 < t) sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    static boolean inside(long x, long y, long cx, long cy, long r) {
        long dx = x - cx;
        long dy = y - cy;
        return dx * dx + dy * dy < r * r;
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
