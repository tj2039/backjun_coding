import java.io.*;
import java.util.*;

class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1<<16];
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
            while ((c = read()) <= 32) {
                if (c == -1) return -1;
            }
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int res = c - '0';
            while ((c = read()) > 32) {
                res = res * 10 + c - '0';
            }
            return res * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = fs.nextInt();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int t = 0; t < T; t++) {
            int M = fs.nextInt();
            int N = fs.nextInt();
            int K = fs.nextInt();

            boolean[][] map = new boolean[N][M];
            boolean[][] vis = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                int x = fs.nextInt();
                int y = fs.nextInt();
                map[y][x] = true;
            }

            int cnt = 0;
            int[] qy = new int[N*M];
            int[] qx = new int[N*M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] && !vis[i][j]) {
                        cnt++;
                        int s = 0, e = 0;
                        qy[e] = i;
                        qx[e++] = j;
                        vis[i][j] = true;
                        while (s < e) {
                            int cy = qy[s];
                            int cx = qx[s++];
                            for (int d = 0; d < 4; d++) {
                                int ny = cy + dy[d];
                                int nx = cx + dx[d];
                                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                                if (!map[ny][nx] || vis[ny][nx]) continue;
                                vis[ny][nx] = true;
                                qy[e] = ny;
                                qx[e++] = nx;
                            }
                        }
                    }
                }
            }

            sb.append(cnt).append('\n');
        }

        System.out.print(sb.toString());
    }
}
