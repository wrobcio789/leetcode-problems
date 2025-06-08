import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        final var n = s.length();

        int result = 0;
        final var characters = new CharactersBag();
        for (int i = 0, j = 0; i < n; i++) {
            j = Math.max(j, i);

            while (j < n && (characters.contains(s.charAt(j)) || characters.countDistinct() < k)) {
                characters.add(s.charAt(j));
                j++;
            }

            characters.remove(s.charAt(i));
            result = Math.max(result, j - i);
        }

        return result;
    }

    private static class CharactersBag {
        private final HashMap<Character, Integer> occurrences = new HashMap<>();

        private void add(char a) {
            occurrences.merge(a, 1, Integer::sum);
        }

        private void remove(char a) {
            if (occurrences.merge(a, -1, Integer::sum) <= 0) {
                occurrences.remove(a);
            }
        }

        private boolean contains(char a) {
            return occurrences.containsKey(a);
        }

        private int countDistinct() {
            return occurrences.size();
        }
    }
}