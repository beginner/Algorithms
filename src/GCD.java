import java.util.ArrayList;
import java.util.List;

public class GCD {

    public static void main(String[] args) {
       // System.out.println(gcd(3768, 1701));
        System.out.println(primeFactors(100));
    }

    // Euclidean Algorithm - https://www.youtube.com/watch?v=JUzYl1TYMcU
    // Time -> O(logN)
    private static int gcd(int a, int b) {
        if (a > b) return gcd(b, a);

        while (true) {
            int remainder = b % a;
            if (remainder == 0) break;
            b = a;
            a = remainder;
        }

        return a;

    }

    // Sieve of Eratosthenes - https://www.youtube.com/watch?v=klcIklsWzrY
    private static List<Integer> primeFactors(int n) {
        List<Integer> result = new ArrayList<>();

        boolean[] composite = new boolean[n + 1];

        for (int i = 2; i * i <= n; i++) {
            if (!composite[i]) {
                for (int j = i * i ; j <= n; j += i) {
                    composite[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!composite[i]) result.add(i);
        }

        return result;
    }
}
