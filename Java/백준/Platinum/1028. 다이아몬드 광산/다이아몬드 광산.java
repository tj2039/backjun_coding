import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
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
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int val = c - '0';
            while ((c = read()) > ' ') {
                val = val * 10 + c - '0';
            }
            return val * sign;
        }
        String next() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return null;
            }
            StringBuilder sb = new StringBuilder();
            do {
                sb.append((char) c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int R = fs.nextInt();
        int C = fs.nextInt();
        char[][] a = new char[R][];
        for (int i = 0; i < R; i++) {
            a[i] = fs.next().toCharArray();
        }

        int[][] dl = new int[R][C];
        int[][] dr = new int[R][C];
        int[][] ul = new int[R][C];
        int[][] ur = new int[R][C];

        for (int r = R - 1; r >= 0; r--) {
            for (int c = 0; c < C; c++) {
                if (a[r][c] == '1') {
                    dl[r][c] = 1 + ((r + 1 < R && c - 1 >= 0) ? dl[r + 1][c - 1] : 0);
                    dr[r][c] = 1 + ((r + 1 < R && c + 1 < C) ? dr[r + 1][c + 1] : 0);
                } else {
                    dl[r][c] = dr[r][c] = 0;
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (a[r][c] == '1') {
                    ul[r][c] = 1 + ((r - 1 >= 0 && c - 1 >= 0) ? ul[r - 1][c - 1] : 0);
                    ur[r][c] = 1 + ((r - 1 >= 0 && c + 1 < C) ? ur[r - 1][c + 1] : 0);
                } else {
                    ul[r][c] = ur[r][c] = 0;
                }
            }
        }

        int ans = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (a[r][c] != '1') continue;
                int maxSize = Math.min(dr[r][c], dl[r][c]);
                for (int k = Math.min(maxSize, 375); k > ans; k--) {
                    int bottom = r + 2 * (k - 1);
                    if (bottom >= R) continue;
                    if (ur[bottom][c] >= k && ul[bottom][c] >= k) {
                        ans = k;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
