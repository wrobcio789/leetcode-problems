import java.util.Arrays;

class Solution {
    public boolean canPermutePalindrome(String s) {
        final var count = new int['z' - 'a' + 1];
        s.chars().forEach(character -> count[character - 'a']++);

        return Arrays.stream(count)
                .filter(num -> num%2 == 1)
                .count() <= 1;
    }
}