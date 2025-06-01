import java.util.List;

class Solution {
    private static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String reverseVowels(String s) {
        final var chars = s.toCharArray();

        int leftIndex = 0;
        int rightIndex = chars.length - 1;

        while (true) {
            while (leftIndex < rightIndex && !VOWELS.contains(chars[leftIndex])) {
                leftIndex++;
            }

            while (rightIndex > leftIndex && !VOWELS.contains(chars[rightIndex])) {
                rightIndex--;
            }

            if (leftIndex >= rightIndex) {
                break;
            }
            swap(chars, leftIndex++, rightIndex--);
        }

        return String.valueOf(chars);
    }

    private void swap(char[] chars, int left, int right) {
        final var buffer = chars[left];
        chars[left] = chars[right];
        chars[right] = buffer;
    }
}