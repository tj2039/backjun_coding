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
        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + c - '0';
                c = read();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int s = fs.nextInt();
        int N = fs.nextInt();
        int K = fs.nextInt();
        long R1 = fs.nextInt();
        long R2 = fs.nextInt();
        long C1 = fs.nextInt();
        long C2 = fs.nextInt();

        int start = (N - K) / 2;
        int end = start + K; // exclusive

        StringBuilder sb = new StringBuilder();
        for (long r = R1; r <= R2; r++) {
            for (long c = C1; c <= C2; c++) {
                boolean black = false;
                long rr = r, cc = c;
                for (int i = 0; i < s; i++) {
                    int dr = (int)(rr % N);
                    int dc = (int)(cc % N);
                    if (dr >= start && dr < end && dc >= start && dc < end) {
                        black = true;
                        break;
                    }
                    rr /= N;
                    cc /= N;
                }
                sb.append(black ? '1' : '0');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
