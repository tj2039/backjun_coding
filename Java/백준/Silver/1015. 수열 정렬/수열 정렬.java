import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int val, idx;
        Pair(int v, int i) { val = v; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Pair[] arr = new Pair[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), i);
        }
        Arrays.sort(arr, (a, b) -> {
            if (a.val != b.val) return Integer.compare(a.val, b.val);
            return Integer.compare(a.idx, b.idx);
        });
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            P[arr[i].idx] = i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(P[0]);
        for (int i = 1; i < N; i++) {
            sb.append(' ').append(P[i]);
        }
        System.out.print(sb.toString());
    }
}
