class Solution {
    public String longestCommonPrefix(String[] strs) {
        final int n = strs.length;

        String result = strs[0];
        for (int i = 1; i < n; i++) {
            result = commonPrefix(result, strs[i]);
        }

        return result;
    }

    private String commonPrefix(String a, String b) {
        final int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.substring(0, i);
            }
        }
        return a.substring(0, minLength);
    }
}