class Solution {
    public int shortestWay(String source, String target) {
        if (target.isEmpty()) {
            return 0;
        }

        int result = 1;
        for (int i = 0, sourceIndex = 0; i < target.length(); i++) {
            final var charToFind = target.charAt(i);

            final var origin = sourceIndex;
            while (sourceIndex < source.length() && source.charAt(sourceIndex) != charToFind) {
                sourceIndex++;
            }

            if (sourceIndex < source.length()) {
                sourceIndex++;
            } else {
                result++; //Word completed, we start again

                sourceIndex = 0;
                while (sourceIndex < origin && source.charAt(sourceIndex) != charToFind) {
                    sourceIndex++;
                }

                if (sourceIndex < origin) {
                    sourceIndex++;
                } else {
                    return -1;
                }
            }
        }

        return result;
    }
}