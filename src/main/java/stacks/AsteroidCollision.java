package stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <a href="https://leetcode.com/problems/asteroid-collision/description/">Asteroid Collision</a>
 * We are given an array asteroids of integers representing asteroids in a row.
 * The indices of the asteroid in the array represent their relative position in space.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {
    // Time Complexity: O(n) + O(n) In worst case zero asteroids collide
    // Space Complexity: (n)
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean destroyed;
        for (int asteroid : asteroids) {
            destroyed = false;
            // From here we know the asteroids are moving in 2 different directions
            // We check only top > 0 && aster < 0 because top > 0 means the asteroid is moving right and asteroid < 0 means it is moving left
            // If we check the other way around we identify its different direction but it would be moving to left and right which do not create collision
            // That is only we check top > 0 moving right and asteroid < 0 moving left
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                int top = stack.peek();
                if (Math.abs(asteroid) > Math.abs(stack.peek())) {
                    stack.pop(); // stack asteroid destroyed
                    continue;
                } else if (Math.abs(asteroid) == Math.abs(top)) {
                    stack.pop(); // both asteroids destroyed
                }
                destroyed = true;
                break;
            }
            if (!destroyed) {
                stack.push(asteroid);
            }
        }
        int[] res = new int[stack.size()];
        int i = res.length - 1;
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        AsteroidCollision ac = new AsteroidCollision();
        int[] asteroids = {3, 5, -6, 2, -1, 4};
        int[] res = ac.asteroidCollision(asteroids);
        System.out.println(Arrays.toString(res));
    }
}
