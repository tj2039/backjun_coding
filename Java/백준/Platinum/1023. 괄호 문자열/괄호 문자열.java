import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        BigInteger K = new BigInteger(st.nextToken());

        BigInteger[] pow2 = new BigInteger[N + 1];
        pow2[0] = BigInteger.ONE;
        for (int i = 1; i <= N; i++) pow2[i] = pow2[i - 1].shiftLeft(1);

        BigInteger[][] dp = new BigInteger[N + 1][N + 2];
        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], BigInteger.ZERO);
        dp[0][0] = BigInteger.ONE;
        for (int len = 1; len <= N; len++) {
            for (int bal = 0; bal <= N; bal++) {
                BigInteger val = dp[len - 1][bal + 1];
                if (bal > 0) val = val.add(dp[len - 1][bal - 1]);
                dp[len][bal] = val;
            }
        }

        BigInteger totalInvalid = pow2[N].subtract(dp[N][0]);
        if (K.compareTo(totalInvalid) >= 0) {
            System.out.print(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int bal = 0;
        boolean invalidPrefix = false;
        for (int i = 0; i < N; i++) {
            int rem = N - i - 1;
            if (invalidPrefix) {
                BigInteger openCount = pow2[rem];
                if (K.compareTo(openCount) < 0) {
                    sb.append('(');
                } else {
                    sb.append(')');
                    K = K.subtract(openCount);
                }
                continue;
            }

            BigInteger invalidOpen = pow2[rem].subtract(dp[rem][bal + 1]);
            if (invalidOpen.signum() < 0) invalidOpen = BigInteger.ZERO;

            if (K.compareTo(invalidOpen) < 0) {
                sb.append('(');
                bal++;
            } else {
                sb.append(')');
                K = K.subtract(invalidOpen);
                if (bal == 0) {
                    invalidPrefix = true;
                } else {
                    bal--;
                }
            }
        }

        System.out.print(sb.toString());
    }
}
