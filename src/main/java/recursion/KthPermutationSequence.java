package recursion;

/**
 * Given n and k, return the kth permutation sequence of numbers from 1 to n.
 *
 * Example:
 * Input: n = 3, k = 4
 * Output: "231"
 *
 * Explanation:
 * The permutation sequences are:
 * 1. "123"
 * 2. "132"
 * 3. "213"
 * 4. "231"
 * 5. "312"
 * 6. "321"
 *
 * The 4th permutation sequence is "231".
 */
public class KthPermutationSequence {
    private int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private String execute(int n, int k, String input, StringBuilder output) {
        if (output.length() == n) {
            return output.toString();
        }
        int eachSetPermutation = factorial(input.length() - 1);
        int kPresentInSet = k / eachSetPermutation;
        output.append(input.charAt(kPresentInSet));
        StringBuilder newInput = new StringBuilder(input);
        newInput.deleteCharAt(kPresentInSet);
        int newK = k % eachSetPermutation;
        return execute(n, newK, newInput.toString(), output);
    }

    public String getPermutation(int n, int k) {
        StringBuilder output = new StringBuilder();
        StringBuilder input = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            input.append(String.valueOf(i));
        }
        return execute(n, k - 1, input.toString(), output);
    }

    public static void main(String[] args) {
        int inputN =4;
        int inputK = 17;
        KthPermutationSequence kthPermutationSequence = new KthPermutationSequence();
        String result = kthPermutationSequence.getPermutation(inputN, inputK);
        System.out.println("The " + inputK + "th permutation sequence of numbers from); 1 to " + inputN + " is: " + result);
    }
}
