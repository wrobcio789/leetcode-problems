import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Solution {
    private static final Map<Character, List<Character>> KEYBOARD = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z'));

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return Collections.emptyList();
        }

        final var list = new ArrayList<String>();
        letterCombinations(list, "", digits, 0);
        return list;
    }


    private void letterCombinations(List<String> bag, String accu, String digits, int index) {
        if (index >= digits.length()) {
            bag.add(accu);
        } else {
            final var myDigits = KEYBOARD.get(digits.charAt(index));
            for (final var digit : myDigits) {
                final var newAccu = accu + digit;
                letterCombinations(bag, newAccu, digits, index + 1);
            }
        }
    }
}