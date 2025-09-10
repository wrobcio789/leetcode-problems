import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final Character[] NUMS = {'0', '1', '6', '8', '9'};
    private static final Character[] MIRROR_NUMS = {'0', '1', '8'};
    private static final int[] REVERSE = new int[]{0, 1, 0, 0, 0, 0, 9, 0, 8, 6};

    public List<String> findStrobogrammatic(int n) {
        final var halfN = n / 2;

        final var perms = new ArrayList<String>();
        findPermutations(new StringBuilder(), perms, halfN);

        if (n % 2 == 1) {
            return perms.stream()
                    .flatMap(str ->
                            Arrays.stream(MIRROR_NUMS).map(mirrorNum -> str + mirrorNum + reverse(str)))
                    .filter(str -> str.length() == 1 || !str.startsWith("0"))
                    .toList();
        } else {
            return perms.stream()
                    .map(str -> str + reverse(str))
                    .filter(str -> str.length() == 1 || !str.startsWith("0"))
                    .toList();
        }
    }

    private String reverse(String str) {
        final var builder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            builder.append(REVERSE[str.charAt(i) - '0']);
        }

        return builder.toString();
    }

    private void findPermutations(StringBuilder builder, List<String> accu, int n) {
        final var length = builder.length();
        if (length == n) {
            final var answer = builder.toString();
            accu.add(answer);
            return;
        }

        for (final var digit : NUMS) {
            builder.append(digit);
            findPermutations(builder, accu, n);
            builder.deleteCharAt(length);
        }
    }
}