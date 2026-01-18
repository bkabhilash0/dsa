package binary_search;

/**
 * <a href="https://leetcode.com/problems/koko-eating-bananas/description/">...</a>
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during that hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 */
public class KokoEatingBananas {
    private long getHoursForEatingKBananas(int[] piles, int speed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (long) Math.ceil((double) pile / speed);
//            System.out.println("Pile: " + pile + ", Speed: " + speed + ", Hours for this pile: " + (int) Math.ceil((double) pile / speed));
        }
        return hours;
    }

    private boolean isPossible(int[] piles, int speed, int h) {
        long hours = 0;
        for (int pile : piles) {
//            hours += (long) Math.ceil((double) pile / speed);
            hours +=  (pile + speed - 1)/ speed;
        }
        return hours <= h;
    }

    private int getMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            if (pile > max) {
                max = pile;
            }
        }
        return max;
    }

    // Brute Force Approach - Linear Search
    // Time Complexity: O(n * m) where n is the number of piles and m is the maximum pile size
    // Space Complexity: O(1)
    private int bruteForce(int[] piles, int h) {
        int maxPile = getMax(piles);
        for (int speed = 1; speed <= maxPile; speed++) {
            long bananaEatenHours = getHoursForEatingKBananas(piles, speed);
            if (bananaEatenHours <= h) {
                return speed;
            }
        }
        return -1;
    }

    // Optimal Approach - Binary Search
    // Time Complexity: O(n * log m) where n is the number of piles and m is the maximum pile size
    // Space Complexity: O(1)
    private int optimal(int[] piles, int h) {
        int start = 1;
        int end = getMax(piles);
        while(start <= end){
            int mid = (start + end) / 2;
//            long hoursNeeded = getHoursForEatingKBananas(piles, mid);
//            System.out.println("---------------------------------------");
            if(isPossible(piles, mid, h)){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        // At the end of the loop, start will be pointing to the minimum eating speed
        return start;
    }

    public int minEatingSpeed(int[] piles, int h) {
        return optimal(piles, h);
    }

    public static void main(String[] args) {
        KokoEatingBananas koko = new KokoEatingBananas();
        int[] piles = {805306368,805306368,805306368};
        int h = 1000000000;
        int result = koko.minEatingSpeed(piles, h);
        System.out.println("Minimum Eating Speed: " + result);
    }
}
