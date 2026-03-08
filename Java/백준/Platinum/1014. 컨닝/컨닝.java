import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[] avail;
    static List<Integer>[] validMasks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            avail = new int[R];
            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                int mask = 0;
                for (int j = 0; j < C; j++) {
                    if (line.charAt(j) == '.') mask |= (1 << j);
                }
                avail[i] = mask;
            }

            int maxMask = 1 << C;
            validMasks = new ArrayList[R];
            for (int i = 0; i < R; i++) {
                validMasks[i] = new ArrayList<>();
                for (int mask = 0; mask < maxMask; mask++) {
                    if ((mask & avail[i]) != mask) continue;
                    if ((mask & (mask << 1)) != 0) continue;
                    validMasks[i].add(mask);
                }
            }

            int[][] dp = new int[R + 1][maxMask];
            for (int i = 0; i < R; i++) {
                for (int mask : validMasks[i]) {
                    int cnt = Integer.bitCount(mask);
                    for (int prev = 0; prev < maxMask; prev++) {
                        if (dp[i][prev] < 0) continue;
                        if ((mask & (prev << 1)) != 0) continue;
                        if ((mask & (prev >> 1)) != 0) continue;
                        dp[i + 1][mask] = Math.max(dp[i + 1][mask], dp[i][prev] + cnt);
                    }
                }
            }
            int ans = 0;
            for (int mask = 0; mask < maxMask; mask++) {
                ans = Math.max(ans, dp[R][mask]);
            }
            out.append(ans).append('\n');
        }
        System.out.print(out.toString());
    }
}
