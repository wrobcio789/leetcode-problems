class StringIterator {
    private String string;
    private int readingIndex = 0;
    private int repeat = 0;
    private int forwardIndex = 0;

    public StringIterator(String compressedString) {
        this.string = compressedString;
        goForward();
    }

    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        
        final var result = string.charAt(readingIndex);

        repeat--;
        if (repeat == 0) {
            readingIndex = forwardIndex;
            goForward();
        }

        return result;
    }

    public boolean hasNext() {
        return readingIndex < string.length();
    }

    private void goForward() {
        forwardIndex++;
        int beginIndex = forwardIndex;
        while (forwardIndex < string.length() && isNum(string.charAt(forwardIndex))) {
            forwardIndex++;
        }

        if (beginIndex < string.length()) {
            this.repeat = Integer.parseInt(string.substring(beginIndex, forwardIndex));
        }
    }

    private static boolean isNum(char character) {
        return character >= '0' && character <= '9';
    }
}