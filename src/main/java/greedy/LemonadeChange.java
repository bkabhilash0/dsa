package greedy;

/**
 * <a href="https://leetcode.com/problems/lemonade-change/description/">Lemonade Change</a>
 * At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills).
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer so that the net transaction is that the customer pays $5.
 * Note that you do not have any change in hand at first.
 * Given an integer array bills where bills[i] is the bill the ith customer pays,
 * return true if you can provide every customer with the correct change, or false otherwise.
 */
public class LemonadeChange {
    // Time Complex: O(n) where n is the number of customers
    // Space Complex: O(1) since we are using only a constant amount of space to keep track of the number of $5 and $10 bills we have in hand
    private boolean optimal(int[] bills) {
        int fives = 0;
        int tens = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                if (fives > 0) {
                    fives--;
                    tens++;
                } else {
                    return false;
                }
            } else {
                if (fives > 0 && tens > 0) {
                    fives--;
                    tens--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    System.out.println("fives: " + fives + " tens: " + tens);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean lemonadeChange(int[] bills) {
        return optimal(bills);
    }

    public static void main(String[] args) {
        LemonadeChange lc = new LemonadeChange();
//        int[] bills = {5, 5, 5, 10, 20};
//        int[] bills = {5, 5, 10, 10, 20};
        int[] bills = {5, 5, 5, 10, 5, 20, 5, 10, 5, 20};
        System.out.println(lc.lemonadeChange(bills));
    }
}
