class Solution {
    public String removeStars(String str) {
        final var resultArray = str.toCharArray();

        int writingIndex = 0;
        for (int readingIndex = 0; readingIndex < str.length(); readingIndex++) {
            if(str.charAt(readingIndex) == '*') {
                writingIndex--;
            } else {
                resultArray[writingIndex] = resultArray[readingIndex];
                writingIndex++;
            }
        }

        return String.valueOf(resultArray).substring(0, writingIndex);
    }
}