class Solution {
    public int compress(char[] chars) {
        int writingIndex = 0;

        for (int i = 0; i < chars.length;) {
            int j = i;
            while (j + 1 < chars.length && chars[j + 1] == chars[i]) {
                j++;
            }
            int count = j - i + 1;

            chars[writingIndex++] = chars[i];
            if (count > 1) {
                int decodeIndex = 0;
                char[] digits = new char[4];
                while (count > 0) {
                    digits[decodeIndex++] = (char) ('0' + count%10);
                    count /= 10;
                }

                while(decodeIndex-- > 0) {
                    chars[writingIndex++] = digits[decodeIndex];
                }
            }

            i = j + 1;
        }

        return writingIndex;
    }
}