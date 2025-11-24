package recursion;

import java.util.List;

public class LetterCombinationsOfPhoneNumber {
    private static final String[] LETTERS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private void execute(String digits, int index, StringBuilder current, List<String> res) {
        if (current.length() == digits.length()) {
            res.add(current.toString());
            return;
        }
        String letters = LETTERS[digits.charAt(index) - '0'];
        for(int i = 0; i < letters.length(); i++){
            current.append(letters.charAt(i));
            execute(digits, index + 1, current, res);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new java.util.ArrayList<>();
        execute(digits, 0, new StringBuilder(), res);
        return res;
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber comb = new LetterCombinationsOfPhoneNumber();
        String digits = "232";
        List<String> res = comb.letterCombinations(digits);
        System.out.println(res);
    }
}
