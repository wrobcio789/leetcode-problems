import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        final var sameStringsMap = new HashMap<String, List<String>>();

        for (final var string : strings) {
            final var builder = new StringBuilder(string.length());
            final var offset = string.charAt(0) - 'a';

            for (int i = 0; i < string.length(); i++) {
                builder.append(normalize(string.charAt(i), offset));
            }

            final var normalizedString = builder.toString();
            sameStringsMap.putIfAbsent(normalizedString, new ArrayList<>());

            final var list = sameStringsMap.get(normalizedString);
            list.add(string);
        }

        return sameStringsMap
                .values()
                .stream()
                .toList();
    }

    private char normalize(char value, int offset) {
        value -= offset;
        if (value < 'a') {
            value += ('z' - 'a' + 1);
        }

        return value;
    }
}