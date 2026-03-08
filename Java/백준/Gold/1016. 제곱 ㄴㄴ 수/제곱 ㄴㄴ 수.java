import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        int n = (int)(max - min + 1);
        boolean[] marked = new boolean[n];

        int limit = (int)Math.sqrt(max);
        boolean[] sieve = new boolean[limit + 1];
        Arrays.fill(sieve, true);
        if (limit >= 0) sieve[0] = false;
        if (limit >= 1) sieve[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= limit; j += i) sieve[j] = false;
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (!sieve[i]) continue;
            long sq = 1L * i * i;
            long start = (min + sq - 1) / sq * sq;
            for (long v = start; v <= max; v += sq) {
                marked[(int)(v - min)] = true;
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!marked[i]) cnt++;
        }
        System.out.println(cnt);
    }
}
