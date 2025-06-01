class Solution {
    public String mergeAlternately(String word1, String word2) {
        final var builder = new StringBuilder();
        final int commonLength = Math.min(word1.length(), word2.length());

        for (int i = 0; i < commonLength; i++) {
            builder.append(word1.charAt(i));
            builder.append(word2.charAt(i));
        }
        builder.append(word1.substring(commonLength));
        builder.append(word2.substring(commonLength));

        return builder.toString();
    }
}