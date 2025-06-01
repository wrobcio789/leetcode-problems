class Solution {
    public boolean isCircularSentence(String sentence) {
        final var sentenceLength = sentence.length();
        if(sentence.charAt(0) != sentence.charAt(sentenceLength - 1)) {
            return false;
        }

        for (int i = 1; i < sentenceLength - 1; i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                return false;
            }
        }

        return true;
    }
}