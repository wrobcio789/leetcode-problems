import java.util.Objects;

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        final var sN = s.length();
        final var tN = t.length();

        if (Math.abs(sN - tN) > 1) {
            return false;
        }

        return sN > tN ? isOneEditDistanceBalanced(t, s) : isOneEditDistanceBalanced(s, t);
    }

    private boolean isOneEditDistanceBalanced(String s, String t) {
        final var n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            final var sChar = s.charAt(i);
            final var tChar = t.charAt(i);

            if (sChar != tChar) {
                if (s.length() == t.length()) {
                    return Objects.equals(s.substring(i + 1), t.substring(i + 1));
                } else { //s is shorter
                    return Objects.equals(s.substring(i), t.substring(i + 1));
                }
            }
        }

        return s.length() + 1 == t.length();
    }
}