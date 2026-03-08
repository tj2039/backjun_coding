import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long[] h = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) h[i] = Long.parseLong(st.nextToken());

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;

            // to the right
            boolean has = false;
            long maxNum = 0, maxDen = 1;
            for (int j = i + 1; j < n; j++) {
                long num = h[j] - h[i];
                long den = j - i; // > 0
                if (!has || num * maxDen > maxNum * den) {
                    cnt++;
                    has = true;
                    maxNum = num;
                    maxDen = den;
                }
            }

            // to the left
            has = false;
            maxNum = 0;
            maxDen = 1;
            for (int j = i - 1; j >= 0; j--) {
                long num = h[j] - h[i];
                long den = i - j; // > 0
                if (!has || num * maxDen > maxNum * den) {
                    cnt++;
                    has = true;
                    maxNum = num;
                    maxDen = den;
                }
            }

            if (cnt > answer) answer = cnt;
        }

        System.out.print(answer);
    }
}
