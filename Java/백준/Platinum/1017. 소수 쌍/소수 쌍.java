import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isPrime;

    static boolean dfs(int a, boolean[] visited, int[] matchB, List<List<Integer>> adj) {
        for (int b : adj.get(a)) {
            if (visited[b]) continue;
            visited[b] = true;
            if (matchB[b] == -1 || dfs(matchB[b], visited, matchB, adj)) {
                matchB[b] = a;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int maxVal = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxVal = Math.max(maxVal, arr[i]);
        }

        int maxSum = maxVal * 2;
        isPrime = new boolean[maxSum + 1];
        Arrays.fill(isPrime, true);
        if (maxSum >= 0) {
            isPrime[0] = false;
        }
        if (maxSum >= 1) {
            isPrime[1] = false;
        }
        for (int i = 2; i * i <= maxSum; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= maxSum; j += i) {
                isPrime[j] = false;
            }
        }

        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        for (int v : arr) {
            if ((v & 1) == 1) odds.add(v);
            else evens.add(v);
        }
        if (odds.size() != evens.size()) {
            System.out.println(-1);
            return;
        }

        int first = arr[0];
        boolean firstOdd = (first & 1) == 1;
        List<Integer> candidates = firstOdd ? new ArrayList<>(evens) : new ArrayList<>(odds);
        List<Integer> answers = new ArrayList<>();

        for (int cand : candidates) {
            if (!isPrime[first + cand]) continue; // first pair must be prime

            List<Integer> A; // left side
            List<Integer> B; // right side

            if (firstOdd) {
                // A: odds excluding first (which is odds.get(0))
                A = new ArrayList<>(odds.subList(1, odds.size()));
                B = new ArrayList<>();
                boolean removed = false;
                for (int v : evens) {
                    if (!removed && v == cand) {
                        removed = true;
                        continue;
                    }
                    B.add(v);
                }
                if (!removed) continue;
            } else {
                // first even -> A: evens excluding first (which is evens.get(0))
                A = new ArrayList<>(evens.subList(1, evens.size()));
                B = new ArrayList<>();
                boolean removed = false;
                for (int v : odds) {
                    if (!removed && v == cand) {
                        removed = true;
                        continue;
                    }
                    B.add(v);
                }
                if (!removed) continue;
            }

            int nA = A.size();
            int nB = B.size();
            if (nA != nB) continue;

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < nA; i++) adj.add(new ArrayList<>());
            for (int i = 0; i < nA; i++) {
                int x = A.get(i);
                for (int j = 0; j < nB; j++) {
                    int y = B.get(j);
                    if (isPrime[x + y]) adj.get(i).add(j);
                }
            }

            int[] matchB = new int[nB];
            Arrays.fill(matchB, -1);
            int matchCnt = 0;
            for (int i = 0; i < nA; i++) {
                boolean[] visited = new boolean[nB];
                if (dfs(i, visited, matchB, adj)) matchCnt++;
            }
            if (matchCnt == nA) answers.add(cand);
        }

        if (answers.isEmpty()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(answers);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(answers.get(i));
        }
        System.out.println(sb.toString());
    }
}
