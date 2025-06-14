class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;

        while (i < s.length()) {
            while (j < t.length() && t.charAt(j) != s.charAt(i)) {
                j++;
            }

            if(j == t.length()) {
                break;
            }

            i++;
            j++;
        }

        return i == s.length();
    }
}