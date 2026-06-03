import java.util.*;

class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        boolean[] visited = new boolean[n];
        visited[0] = true;

        int farthest = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == n - 1) {
                return true;
            }

            int start = Math.max(curr + minJump, farthest + 1);
            int end = Math.min(curr + maxJump, n - 1);

            for (int next = start; next <= end; next++) {
                if (s.charAt(next) == '0' && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }

            farthest = Math.max(farthest, end);
        }

        return false;
    }
}