import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> getFactors(int n) {
        if (n <= 2) {
            return Collections.emptyList();
        }

        final var allFactors = factors(n);
        final var bag = new ArrayList<List<Integer>>();
        backtrack(bag, allFactors, new LinkedList<>(), 1L, n);
        return bag;
    }

    private void backtrack(List<List<Integer>> bag, List<Integer> factors, LinkedList<Integer> accu, long product, int n) {
        if (product == n) {
            bag.add(new ArrayList<>(accu));
            return;
        }

        for (final var factor : factors) {
            if (!accu.isEmpty() && factor < accu.getLast()) {
                continue;
            }

            final var nextProduct = product * factor;
            if (nextProduct <= n) {
                accu.add(factor);
                backtrack(bag, factors, accu, nextProduct, n);
                accu.removeLast();
            } else {
                break;
            }
        }
    }

    private List<Integer> factors(int n) {
        final var result = new ArrayList<Integer>();

        if (n % 2 == 0) {
            result.add(2);
        }

        for (int i = 3; i <= n / 2; i++) {
            if (n % i == 0) {
                result.add(i);
            }
        }

        return result;
    }
}