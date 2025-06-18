import java.util.ArrayList;
import java.util.List;

public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        final var wordCount = strs.size();
        final var builder = new StringBuilder();
        builder.append((char) wordCount);

        for (final var str : strs) {
            builder.append((char) str.length());
        }

        for (final var str : strs) {
            builder.append(str);
        }

        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        final var wordCount = s.charAt(0);

        final var result = new ArrayList<String>(wordCount);
        var lengthSoFar = wordCount + 1;
        for (int i = 0; i < wordCount; i++) {
            final var strLength = s.charAt(i + 1);

            final var string = s.substring(lengthSoFar, lengthSoFar + strLength);
            result.add(string);
            lengthSoFar += strLength;
        }

        return result;

    }
}