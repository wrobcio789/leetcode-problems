import java.util.Objects;

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!Objects.equals(str1 + str2, str2 + str1)) {
            return "";
        }
        final var gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);

    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}