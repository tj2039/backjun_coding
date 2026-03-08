import java.io.*;

public class Main {
    static final int[] w = {6,2,5,5,4,5,6,3,7,5};
    static boolean[][] dp;
    static char[] cur;
    static int N;
    static int maxSum;

    static boolean can(int pos, int sum) {
        return sum >= 0 && sum <= maxSum && dp[pos][sum];
    }

    static String buildSuffix(int pos, int rem) {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < N; i++) {
            for (int d = 0; d <= 9; d++) {
                int wt = w[d];
                if (rem >= wt && dp[i+1][rem-wt]) {
                    sb.append(d);
                    rem -= wt;
                    break;
                }
            }
        }
        return sb.toString();
    }

    static String findNext(int target) {
        int[] prefixWeight = new int[N+1];
        for (int i = 0; i < N; i++) {
            prefixWeight[i+1] = prefixWeight[i] + w[cur[i]-'0'];
        }
        for (int pos = N-1; pos >= 0; pos--) {
            int prefix = prefixWeight[pos];
            int curDigit = cur[pos]-'0';
            for (int d = curDigit + 1; d <= 9; d++) {
                int rem = target - (prefix + w[d]);
                if (rem < 0 || rem > maxSum) continue;
                if (!dp[pos+1][rem]) continue;
                StringBuilder sb = new StringBuilder();
                sb.append(cur, 0, pos);
                sb.append(d);
                sb.append(buildSuffix(pos+1, rem));
                return sb.toString();
            }
        }
        return null;
    }

    static String buildMin(int target) {
        return buildSuffix(0, target);
    }

    static long parseValue(String s) {
        return Long.parseLong(s);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) return;
        s = s.trim();
        cur = s.toCharArray();
        N = cur.length;
        maxSum = 7 * N;

        int target = 0;
        for (char ch : cur) target += w[ch-'0'];

        dp = new boolean[N+1][maxSum+1];
        dp[N][0] = true;
        for (int pos = N-1; pos >= 0; pos--) {
            for (int sum = 0; sum <= maxSum; sum++) {
                boolean ok = false;
                for (int d = 0; d <= 9; d++) {
                    int wt = w[d];
                    if (sum >= wt && dp[pos+1][sum-wt]) { ok = true; break; }
                }
                dp[pos][sum] = ok;
            }
        }

        long curVal = parseValue(s);
        String cand = findNext(target);
        long ans;
        if (cand != null) {
            ans = parseValue(cand) - curVal;
        } else {
            String minAll = buildMin(target);
            long M = 1;
            for (int i = 0; i < N; i++) M *= 10;
            ans = (M - curVal) + parseValue(minAll);
        }
        System.out.println(ans);
    }
}
