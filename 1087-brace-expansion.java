import java.util.LinkedList;
import java.util.List;

class Solution {
    public String[] expand(String s) {
        final var bag = new LinkedList<String>();
        expand(bag, new StringBuilder(), s, 0);
        return bag.stream()
                .sorted()
                .toArray(String[]::new);
    }

    public void expand(List<String> bag, StringBuilder accu, String s, int index) {
        if (s.length() == index) {
            bag.add(accu.toString());
            return;
        }

        if (s.charAt(index) == '{') {
            int rightIndex = index + 1;
            while (s.charAt(rightIndex) != '}') {
                rightIndex++;
            }

            for (int i = index + 1; i < rightIndex; i += 2) {
                final var length = accu.length();
                expand(bag, accu.append(s.charAt(i)), s, rightIndex + 1);
                accu.deleteCharAt(length);
            }
        } else {
            final var length = accu.length();
            expand(bag, accu.append(s.charAt(index)), s, index + 1);
            accu.deleteCharAt(length);
        }
    }
}