import java.util.Arrays;

class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        final var n = s.length();

        if (k > n) {
            return 0;
        }

        final var occurs = new int['z' - 'a' + 1];
        for (int i = 0; i < k; i++) {
            final var letter = s.charAt(i);
            occurs[letter - 'a']++;
        }

        int result = areCharactersUnique(occurs) ? 1 : 0;
        for (int i = k; i < n; i++) {

            final var letter = s.charAt(i);
            occurs[letter - 'a']++;

            final var letterToRemove = s.charAt(i - k);
            occurs[letterToRemove - 'a']--;

            if (areCharactersUnique(occurs)) {
                result++;
            }
        }

        return result;
    }

    private boolean areCharactersUnique(int[] occurs) {
        return Arrays.stream(occurs).max().orElse(0) <= 1;
    }
}