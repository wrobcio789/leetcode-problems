class Solution {
    public String reverseWords(String s) {
        final var words = s.trim().split("\\s+");
        final var builder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            builder.append(words[i]);
            if (i > 0) {
                builder.append(' ');
            }
        }

        return builder.toString();
    }
}