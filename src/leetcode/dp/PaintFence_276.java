package leetcode.dp;

import java.util.Arrays;

/**
 * Medium
 * Problem : https://leetcode.com/problems/paint-fence/description/?envType=weekly-question&envId=2024-02-08
 * Explanation - https://leetcode.com/problems/paint-fence/solutions/178010/the-only-solution-you-need-to-read/?envType=weekly-question&envId=2024-02-08
 */
public class PaintFence_276 {

    int[] memo;

    public static void main(String[] args) {
        PaintFence_276 problem = new PaintFence_276();
        System.out.println(problem.numWays(3, 2));
    }

    public int numWays(int n, int k) {
        //  return recurse(n, k);
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return recurse_memoization(n, k);
    }

    // Time -> O( 2 ^ N )
    private int recurse(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;

        int different = (k - 1) * recurse(n - 1, k);
        int same = (k - 1) * recurse(n - 2, k);
        return same + different;
    }

    private int recurse_memoization(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;
        if (memo[n] != -1) return memo[n];
        int different = (k - 1) * recurse(n - 1, k);
        int same = (k - 1) * recurse(n - 2, k);
        memo[n] = same + different;
        return memo[n];
    }

    private int iterative_dp(int n, int k) {
        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = k * k;

        for (int i = 3; i <= n; i++) {
            dp[i] = (k - 1) * (dp[i-1] + dp[i-2]);
        }

        return dp[n];
    }

}
