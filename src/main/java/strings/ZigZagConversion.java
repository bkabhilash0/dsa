package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/zigzag-conversion/description/">Zigzag Conversion</a>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 */
public class ZigZagConversion {
    // Time Complexity: O(N) where N is size of the string
    // Space Complexity: O(k) where k is the num of Rows, for each row we create a String Builder Object
    private String execute(String s, int numRows) {
        StringBuilder[] rows = new StringBuilder[numRows];
        if(numRows == 1 || s.length() <= numRows){
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        boolean goingDown = false;
        int currentRowIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder currentRow = rows[currentRowIndex];
            currentRow.append(s.charAt(i));
            if (currentRowIndex == 0 || currentRowIndex == numRows - 1) {
                goingDown = !goingDown;
            }

            if (goingDown) {
                currentRowIndex++;
            } else {
                currentRowIndex--;
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    public String convert(String s, int numRows) {
        return execute(s, numRows);
    }

    public static void main(String[] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
//        String s = "PAYPALISHIRING";
//        int numRows = 3;
        String s = "AB";
        int numRows = 1;
        String result = zigZagConversion.convert(s, numRows);
        System.out.println(result);
    }
}
