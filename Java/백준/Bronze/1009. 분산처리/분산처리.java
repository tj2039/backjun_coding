import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = fs.nextInt();
        for (int t = 0; t < T; t++) {
            int a = fs.nextInt();
            long b = fs.nextLong();
            sb.append(compute(a, b)).append('\n');
        }
        System.out.print(sb.toString());
    }

    static int compute(int a, long b) {
        int d = a % 10;
        if (b == 0) return 1;
        switch (d) {
            case 0: return 10;
            case 1: return 1;
            case 2: {
                int[] t = {2, 4, 8, 6};
                return t[(int) ((b - 1) % 4)];
            }
            case 3: {
                int[] t = {3, 9, 7, 1};
                return t[(int) ((b - 1) % 4)];
            }
            case 4: return (b % 2 == 1) ? 4 : 6;
            case 5: return 5;
            case 6: return 6;
            case 7: {
                int[] t = {7, 9, 3, 1};
                return t[(int) ((b - 1) % 4)];
            }
            case 8: {
                int[] t = {8, 4, 2, 6};
                return t[(int) ((b - 1) % 4)];
            }
            case 9: return (b % 2 == 1) ? 9 : 1;
            default: return 1;
        }
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
            int sign = 1;
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
