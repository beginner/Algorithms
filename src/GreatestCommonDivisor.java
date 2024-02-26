import java.util.*;

public class GreatestCommonDivisor {

    public static void main(String[] args) {
      //  int[] nums = new int[]{2, 3, 6};
        int nums[] = {4, 3, 12, 8};
        GreatestCommonDivisor problem = new GreatestCommonDivisor();
        System.out.println(problem.canTraverseAllPairs(nums));
    }

    HashMap<Integer, Integer> dsu = new HashMap<>();
    public boolean canTraverseAllPairs(int[] nums) {
        if (nums.length == 1) return true;

        for (int n : nums) {
            if (n == 1) return false;
            Set<Integer> factors = primeFactors(n);
            for (int f : factors) {
                 if (!dsu.containsKey(f)) dsu.put(f, f);
               // dsu.put(f, f);
                union(f, n);
            }
        }

        Set<Integer> components = new HashSet<>();
        for (int n : nums) {
            components.add(find(n));
        }
        return components.size() == 1;
    }

    private void union(int p, int q) {
        int first = find(p);
        int second = find(q);
        dsu.put(first, second);
    }

    private int find(int num) {
        if (dsu.getOrDefault(num, num) == num) {
            return dsu.getOrDefault(num, num);
        }
        final int parent = find(dsu.getOrDefault(num, num));
        dsu.put(num, parent);
        return parent;
    }

    private Set<Integer> primeFactors(int n) {
        Set<Integer> factors = new HashSet<>();
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        if (n > 2) factors.add(n);
        return factors;
    }
}
