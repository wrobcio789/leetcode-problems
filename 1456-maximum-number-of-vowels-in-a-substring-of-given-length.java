import java.util.List;

class Solution {
    private static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u');

    public int maxVowels(String str, int k) {

        int vowels = 0;
        for (int i = 0; i < k; i++) {
            vowels += map(str, i);
        }

        int maxVowels = vowels;
        for (int i = 0; i + k < str.length(); i++) {
            vowels += (map(str, i+k) - map(str, i));
            maxVowels = Math.max(maxVowels, vowels);
        }

        return maxVowels;

    }

    private int map(String s, int i) {
        return VOWELS.contains(s.charAt(i)) ? 1 : 0;
    }
}