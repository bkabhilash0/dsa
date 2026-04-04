package daily_questions.april;

/**
 * Date: April 4, 2026,
 * <a href="https://leetcode.com/problems/decode-the-slanted-ciphertext">Decode the Slanted Cipher Text</a>
 * A string originalText is encoded using a slanted transposition cipher to a string
 * encodedText with the help of a matrix having a fixed number of rows rows.
 * originalText is placed first in a top-left to bottom-right manner.
 * The blue cells are filled first, followed by the red cells, then the yellow cells,
 * and so on, until we reach the end of originalText.
 * The arrow indicates the order in which the cells are filled.
 * All empty cells are filled with ' '. The number of columns is chosen such that
 * the rightmost column will not be empty after filling in originalText.
 * encodedText is then formed by appending all characters of the matrix in a row-wise fashion.
 * The characters in the blue cells are appended first to encodedText,
 * then the red cells, and so on, and finally the yellow cells.
 * The arrow indicates the order in which the cells are accessed.
 * For example, if originalText = "cipher" and rows = 3, then we encode it in the following manner:
 */
public class DecodeTheSlantedCipherText {
    // Time Complexity: O(N) -> We are traversing only through the diagonal elements of the matrix which is equal to the length of the encoded text
    // Space Complexity: O(N) -> String Builder for output but ideally ~= O(1)
    private String bruteForce(String encodedText, int rows) {
        StringBuilder decodedText = new StringBuilder();
        int totalCells = encodedText.length();
        int numOfCols = totalCells / rows;
        int col;
        int baseIndex;
        int index;
        // Traverse the first Row
        for (int i = 0; i < numOfCols; i++) {
            col = i;
            for (int j = 0; j < rows; j++) {
                // We do this so that the index won't go beyond total cell, in this way we are controlling both i and col
                if (col >= numOfCols) break;
                index = (j * numOfCols) + col;
                decodedText.append(encodedText.charAt(index));
                col++;
            }
        }
        return decodedText.toString().stripTrailing();
    }

    // Time Complexity: O(N) -> We are traversing only through the diagonal elements of the matrix which is equal to the length of the encoded text
    // Space Complexity: O(N) -> String Builder for output but ideally ~= O(1)
    private String optimal(String encodedText, int rows) {
        StringBuilder decodedText = new StringBuilder();
        int totalCells = encodedText.length();
        int numOfCols = totalCells / rows;
        for (int i = 0; i < numOfCols; i++) {
            int col = i;
            int row = 0;
            while(row < rows && col < numOfCols){
                int baseIndex = (row * numOfCols) + col;
                decodedText.append(encodedText.charAt(baseIndex));
                row++;
                col++;
            }
        }
        return decodedText.toString().stripTrailing();
    }

    public String decodeCiphertext(String encodedText, int rows) {
        if(rows == 1) return encodedText;
        if(encodedText.isEmpty()) return "";
        return optimal(encodedText, rows);
    }

    public static void main(String[] args) {
//        String encodedText = "iveo    eed   l te   olc";
//        int rows = 4;
        String encodedText = " b  ac";
        int rows = 2;
        DecodeTheSlantedCipherText decodeTheSlantedCipherText = new DecodeTheSlantedCipherText();
        String result = decodeTheSlantedCipherText.decodeCiphertext(encodedText, rows);
        System.out.println(result); // "cipher"
    }
}
