import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String predictPartyVictory(String senate) {
        final Queue<Character> queue = new LinkedList<>();

        int rs = 0, ds = 0;
        for(int i = 0; i<senate.length(); i++) {
            queue.add(senate.charAt(i));
            if(senate.charAt(i) == 'R') {
                rs++;
            } else {
                ds++;
            }
        }

        int skippedD = 0, skippedR = 0;
        while(ds > 0 && rs > 0) {
            final var senator = queue.poll();
            if (senator == 'R') {
                rs--;
                if(skippedR > 0) {
                    skippedR--;
                } else {
                    rs++;
                    queue.add('R');
                    skippedD++;
                }
            } else {
                ds--;
                if(skippedD > 0) {
                    skippedD--;
                } else {
                    ds++;
                    queue.add('D');
                    skippedR++;
                }
            }
        }

        if(rs > 0) {
            return "Radiant";
        } else {
            return "Dire";
        }
    }
}