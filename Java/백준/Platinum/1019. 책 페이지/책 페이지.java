import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine().trim());
        long[] cnt = new long[10];
        for (long factor = 1; factor <= N; factor *= 10) {
            long lower = N % factor;
            long cur = (N / factor) % 10;
            long higher = N / (factor * 10);
            if (higher != 0) {
                cnt[0] += (higher - 1) * factor;
                if (cur == 0) cnt[0] += lower + 1;
                else cnt[0] += factor;
            }
            for (int d = 1; d <= 9; d++) {
                cnt[d] += higher * factor;
                if (cur > d) cnt[d] += factor;
                else if (cur == d) cnt[d] += lower + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i > 0) sb.append(' ');
            sb.append(cnt[i]);
        }
        System.out.println(sb.toString());
    }
}
