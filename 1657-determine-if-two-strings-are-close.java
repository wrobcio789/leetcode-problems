import java.util.*;

class Solution {
    private static final int ALPHABET_SIZE = 'z' - 'a' + 1;

    public boolean closeStrings(String word1, String word2) {
        int[] occurrences1 = new int[ALPHABET_SIZE];
        int[] occurrences2 = new int[ALPHABET_SIZE];

        int presenceMask1 = fillAndReturnPresenceMask(occurrences1, word1);
        int presenceMask2 = fillAndReturnPresenceMask(occurrences2, word2);

        if (presenceMask1 != presenceMask2) {
            return false;
        }

        Arrays.sort(occurrences1);
        Arrays.sort(occurrences2);
        return Arrays.equals(occurrences1, occurrences2);
    }

    private int fillAndReturnPresenceMask(int[] occurrences, String word) {
        int presenceMask = 0;
        for (final var character : word.toCharArray()) {
            presenceMask |= (1 << (character - 'a'));
            occurrences[character - 'a']++;
        }
        return presenceMask;
    }
}