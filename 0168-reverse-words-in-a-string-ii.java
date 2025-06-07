class Solution {
    public void reverseWords(char[] s) {
        reverseString(s);
        reverseEachWord(s);
    }

    private void reverseString(char[] s) {
        final var n = s.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            swap(s, i, j);
        }
    }

    private void reverseEachWord(char[] s) {
        final var n = s.length;

        int index = 0;
        while (index < n) {

            final var wordBegin = index;
            while (index < n && s[index] != ' ') {
                index++;
            }
            final var wordEnd = index;

            for (int i = wordBegin, j = wordEnd - 1; i < j; i++, j--) {
                swap(s, i, j);
            }

            index++; //Jump to beginning of next word
        }
    }

    private void swap(char[] s, int i, int j) {
        final var tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}