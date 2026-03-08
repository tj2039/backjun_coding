import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        int[] ns = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            int n = fs.nextInt();
            ns[i] = n;
            if (n > max) max = n;
        }

        int[] zero = new int[max + 2];
        int[] one = new int[max + 2];
        zero[0] = 1;
        one[1] = 1;
        for (int i = 2; i <= max; i++) {
            zero[i] = zero[i - 1] + zero[i - 2];
            one[i] = one[i - 1] + one[i - 2];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = ns[i];
            sb.append(zero[n]).append(' ').append(one[n]);
            if (i + 1 < t) sb.append('\n');
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
