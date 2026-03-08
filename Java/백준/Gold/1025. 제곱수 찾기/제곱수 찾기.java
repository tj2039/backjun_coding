import java.io.*;
import java.util.*;

public class Main {
    static boolean isSquare(long x) {
        long r = (long) Math.sqrt(x);
        return r * r == x;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] a = new char[N][];
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine().toCharArray();
        }

        int ans = -1;
        // check single digit sequences as step (0,0) not allowed
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int d = a[i][j] - '0';
                if (isSquare(d)) ans = Math.max(ans, d);
            }
        }

        for (int dr = -N + 1; dr <= N - 1; dr++) {
            for (int dc = -M + 1; dc <= M - 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        int rr = r;
                        int cc = c;
                        long num = 0;
                        while (0 <= rr && rr < N && 0 <= cc && cc < M) {
                            num = num * 10 + (a[rr][cc] - '0');
                            if (isSquare(num)) {
                                ans = Math.max(ans, (int) num);
                            }
                            rr += dr;
                            cc += dc;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
