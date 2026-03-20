package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.geeksforgeeks.org/problems/the-celebrity-problem/1">Celebrity Problem</a>
 * A celebrity is a person who is known to all but does not know anyone at a party.
 * A party is being organized by some people.
 * A square matrix mat[][] of size n*n is used to represent people at the party
 * such that if an element of row i and column j is set to 1 it means ith person knows jth person.
 * You need to return the index of the celebrity in the party, if the celebrity does not exist, return -1.
 * Note: Follow 0-based indexing.
 */
public class CelebrityProblem {
    // Time Complexity: O(N^2) + O(N)
    // Space Complexity: O(1)
    private int bruteForce(int[][] mat) {
        int res = -1;
        int count;
        for (int i = 0; i < mat.length; i++) {
            count = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            // The celebrity knows only himself and not others
            if (count == 1) {
                res = i;
                break;
            }
        }

        if (res == -1) {
            return res;
        }

        // Now verify if res is actually the celebrity
        // Check if everyone knows res
        for (int i = 0; i < mat.length; i++) {
            // If res knows someone or someone doesn't knows ref then he is not a celebrity
            if (i != res && (mat[res][i] == 1 || mat[i][res] == 0)) {
                return -1;
            }
        }
        return res;
    }

    // Let's use stack to solve this problem
    // Time Complexity: (3N) -> Push all candidates, pop all to find celebrity, verify again
    // Space Complexity: O(N) -> We are using a stack to store the candidates
    private int better(int[][] mat) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = -1;
        // Put all the indexes are mat into the stack
        for (int i = 0; i < mat.length; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty() && stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();

            // Check if a knows b, then b has chances of being a celebrity
            // If a doesn't know b then a has the chance of being a celebrity
            // Push back the candidate who can be the celebrity
            if (mat[a][b] == 1) {
                stack.push(b);
            } else {
                stack.push(a);
            }
        }
        res = stack.isEmpty() ? -1 : stack.pop();
        if (res == -1) {
            return res;
        }
        // Now verify if res is a celebrity
        for (int i = 0; i < mat.length; i++) {
            // If he knows someone or someone doesn't know him then he is not a celebrity
            if (i != res && (mat[res][i] == 1 || mat[i][res] == 0)) {
                return -1;
            }
        }
        return res;
    }

    // Instead of stack let just use normal array
    // Time Complexity: O(2N)
    // Space Complexity: O(1)
    private int optimal(int[][] mat) {
        int res = -1;
        int celebrityCandidate = 0;
        for (int i = 1; i < mat.length; i++) {
            // If the celebrityCandidate knows the current candidate
            // then current Candidate has the possibility of being a celebrity
            if (mat[celebrityCandidate][i] == 1) {
                celebrityCandidate = i;
            }
        }
        res = celebrityCandidate;
        // Verify
        for (int i = 0; i < mat.length; i++) {
            // If res knows someone or someone doesn't know res then res is not a celebrity
            if (i != res && (mat[res][i] == 1 || mat[i][res] == 0)) {
                return -1;
            }
        }
        return res;
    }

    public int celebrity(int[][] mat) {
        // code here
        return optimal(mat);
    }

    public static void main(String[] args) {
        CelebrityProblem cp = new CelebrityProblem();
        int[][] mat = {{1, 1, 0}, {0, 1, 0}, {0, 1, 1}};
//        int[][] mat = {{1, 0}, {0, 1}};
        System.out.println(cp.celebrity(mat));
    }
}
