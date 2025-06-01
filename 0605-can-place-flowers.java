class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int space = 0;
        for (int i = 0; i < flowerbed.length; ) {
            if(flowerbed[i] == 0) {
                if (i + 1 >= flowerbed.length || flowerbed[i + 1] == 0) {
                    space++;
                    i += 2;
                } else {
                    i += 3;
                }
            } else {
                i += 2;
            }
        }

        return space >= n;
    }
}