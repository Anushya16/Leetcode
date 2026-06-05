import java.util.*;

class Solution {

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        String s = String.valueOf(n);
        Long[][][][][][] dp = new Long[s.length() + 1][2][2][11][11][2];

        return dfs(0, 1, 0, 10, 10, s, dp)[1];
    }

    private long[] dfs(int pos, int tight, int started,
                       int prev2, int prev1,
                       String s,
                       Long[][][][][][] dp) {

        if (pos == s.length()) {
            return new long[]{1, 0};
        }

        if (dp[pos][tight][started][prev2][prev1][0] != null) {
            return new long[]{
                dp[pos][tight][started][prev2][prev1][0],
                dp[pos][tight][started][prev2][prev1][1]
            };
        }

        int limit = (tight == 1) ? s.charAt(pos) - '0' : 9;

        long totalCount = 0;
        long totalWave = 0;

        for (int d = 0; d <= limit; d++) {
            int newTight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {
                long[] res = dfs(pos + 1, newTight, 0, 10, 10, s, dp);
                totalCount += res[0];
                totalWave += res[1];
            } else if (started == 0) {
                long[] res = dfs(pos + 1, newTight, 1, 10, d, s, dp);
                totalCount += res[0];
                totalWave += res[1];
            } else {
                int add = 0;

                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                long[] res = dfs(pos + 1, newTight, 1, prev1, d, s, dp);

                totalCount += res[0];
                totalWave += res[1] + (long) add * res[0];
            }
        }

        dp[pos][tight][started][prev2][prev1][0] = totalCount;
        dp[pos][tight][started][prev2][prev1][1] = totalWave;

        return new long[]{totalCount, totalWave};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.totalWaviness(120, 130));   // 3
        System.out.println(sol.totalWaviness(198, 202));   // 3
        System.out.println(sol.totalWaviness(4848, 4848)); // 2
    }
}