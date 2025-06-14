class Solution {
    public int countLetters(String s) {
        int result = 0;

        int count = 0;
        for(int i = 0, lastChar = 0; i<s.length(); i++) {
            if(s.charAt(i) == lastChar) {
                count++;
            } else {
                result += (count + 1) * (count) / 2;
                lastChar = s.charAt(i);
                count = 1;
            }
        }

        result += (count + 1) * (count) / 2;

        return result;
    }
}