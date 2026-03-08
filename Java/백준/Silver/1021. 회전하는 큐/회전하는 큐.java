import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= n; i++) dq.add(i);

        List<Integer> targets = new ArrayList<>(m);
        while (targets.size() < m) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens() && targets.size() < m) {
                targets.add(Integer.parseInt(st.nextToken()));
            }
        }

        int moves = 0;
        for (int target : targets) {
            int idx = dq.indexOf(target);
            int left = idx;
            int right = dq.size() - idx;
            if (left <= right) {
                for (int i = 0; i < left; i++) {
                    dq.addLast(dq.removeFirst());
                    moves++;
                }
            } else {
                for (int i = 0; i < right; i++) {
                    dq.addFirst(dq.removeLast());
                    moves++;
                }
            }
            dq.removeFirst();
        }
        System.out.print(moves);
    }
}
