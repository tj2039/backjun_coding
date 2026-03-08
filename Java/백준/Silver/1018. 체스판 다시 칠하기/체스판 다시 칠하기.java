import java.io.*;
import java.util.*;

public class Main {
    static char[][] board;
    static int n, m;

    static int repaint(int sr, int sc, char first) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char expected = ((i + j) % 2 == 0) ? first : (first == 'W' ? 'B' : 'W');
                if (board[sr + i][sc + j] != expected) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < m; j++) board[i][j] = line.charAt(j);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + 8 <= n; i++) {
            for (int j = 0; j + 8 <= m; j++) {
                ans = Math.min(ans, repaint(i, j, 'W'));
                ans = Math.min(ans, repaint(i, j, 'B'));
            }
        }
        System.out.println(ans);
    }
}
