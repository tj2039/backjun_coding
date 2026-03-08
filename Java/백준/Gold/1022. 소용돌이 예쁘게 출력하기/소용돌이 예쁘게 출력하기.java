import java.io.*;
import java.util.*;

public class Main {
    static long value(int r, int c) {
        int layer = Math.max(Math.abs(r), Math.abs(c));
        long base = (long) (2L * layer + 1) * (2L * layer + 1);
        if (r == layer) {
            return base - (layer - c);
        } else if (c == -layer) {
            return base - (2L * layer + (layer - r));
        } else if (r == -layer) {
            return base - (4L * layer + (c + layer));
        } else {
            // c == layer, r < layer
            return base - (6L * layer + (r + layer));
        }
    }

    static int digits(long x) {
        int d = 0;
        while (x > 0) {
            d++;
            x /= 10;
        }
        return d == 0 ? 1 : d;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;
        long[][] arr = new long[rows][cols];
        long max = 0;

        for (int i = 0; i < rows; i++) {
            int r = r1 + i;
            for (int j = 0; j < cols; j++) {
                long v = value(r, c1 + j);
                arr[i][j] = v;
                if (v > max) max = v;
            }
        }

        int width = digits(max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String s = Long.toString(arr[i][j]);
                for (int k = 0; k < width - s.length(); k++) sb.append(' ');
                sb.append(s);
                if (j + 1 < cols) sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
