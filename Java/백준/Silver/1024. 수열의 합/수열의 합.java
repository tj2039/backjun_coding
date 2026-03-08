import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        for (int len = L; len <= 100; len++) {
            long t = N - (long) len * (len - 1) / 2;
            if (t < 0) break;
            if (t % len == 0) {
                long start = t / len;
                if (start >= 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < len; i++) {
                        if (i > 0) sb.append(' ');
                        sb.append(start + i);
                    }
                    System.out.println(sb.toString());
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}
