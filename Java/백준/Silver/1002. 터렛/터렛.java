import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            long dx = x1 - x2;
            long dy = y1 - y2;
            long d2 = dx * dx + dy * dy;

            long sumR = r1 + r2;
            long diffR = Math.abs(r1 - r2);
            long sumR2 = sumR * sumR;
            long diffR2 = diffR * diffR;

            if (d2 == 0 && r1 == r2) sb.append(-1);
            else if (d2 > sumR2 || d2 < diffR2) sb.append(0);
            else if (d2 == sumR2 || d2 == diffR2) sb.append(1);
            else sb.append(2);
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
