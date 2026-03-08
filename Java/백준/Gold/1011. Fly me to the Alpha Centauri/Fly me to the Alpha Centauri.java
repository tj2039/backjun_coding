import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = fs.nextInt();
        for (int i = 0; i < T; i++) {
            long x = fs.nextLong();
            long y = fs.nextLong();
            sb.append(minMoves(y - x)).append('\n');
        }
        System.out.print(sb.toString());
    }

    static long minMoves(long d) {
        long k = (long) Math.sqrt(d);
        long sq = k * k;
        if (d == sq) return 2 * k - 1;
        if (d <= sq + k) return 2 * k;
        return 2 * k + 1;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return Long.MIN_VALUE;
            }
            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long val = c - '0';
            while ((c = read()) > ' ') {
                val = val * 10 + (c - '0');
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
