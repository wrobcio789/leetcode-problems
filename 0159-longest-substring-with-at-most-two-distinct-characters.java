import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        final var n = s.length();

        int result = 0;
        final var characters = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; i < n; i++) {
            j = Math.max(j, i);

            while (j < n && (characters.getOrDefault(s.charAt(j), 0) != 0 || characters.size() <= 1)) {
                characters.merge(s.charAt(j), 1, Integer::sum);
                j++;
            }

            final var charAtI = s.charAt(i);
            if (characters.get(charAtI) == 1) {
                characters.remove(charAtI);
            } else {
                characters.put(charAtI, characters.get(charAtI) - 1);
            }

            result = Math.max(result, j - i);
        }

        return result;
    }
}