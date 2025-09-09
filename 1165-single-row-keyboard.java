class Solution {
    private static final int SIZE = 'z' - 'a' + 1;

    public int calculateTime(String keyboard, String word) {
        char[][] distances = new char[SIZE][SIZE];
        final var indices = new char[SIZE];
        calculateDistancesBetweenCharacters(keyboard, indices, distances);

        if (word.length() == 0) {
            return 0;
        }

        char previousChar = word.charAt(0);
        int result = indices[previousChar - 'a'];
        for (int i = 1; i < word.length(); i++) {
            final var character = word.charAt(i);
            result += distances[character - 'a'][previousChar - 'a'];
            previousChar = character;
        }

        return result;
    }

    private void calculateDistancesBetweenCharacters(String keyboard, char[] indices, char[][] distances) {
        for (char i = 0; i < keyboard.length(); i++) {
            final var character = keyboard.charAt(i);
            indices[character - 'a'] = i;
        }

        for (int i = 0; i < keyboard.length(); i++) {
            final var character = keyboard.charAt(i);
            final var characterIndex = indices[character - 'a'];
            for (int j = 0; j < keyboard.length(); j++) {
                final var neihgobur = keyboard.charAt(j);
                final var neihgoburIndex = indices[neihgobur - 'a'];

                distances[character - 'a'][neihgobur - 'a'] = (char) Math.abs(neihgoburIndex - characterIndex);
            }
        }
    }
}