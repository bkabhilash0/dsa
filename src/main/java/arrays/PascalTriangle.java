package arrays;

import java.util.AbstractList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/pascals-triangle/description/">...</a>
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 */
public class PascalTriangle {
    private List<Integer> generateRow(int row){
        int num = 1;
        List<Integer> rowList = new java.util.ArrayList<>();
        rowList.add(num);
        for(int col = 1; col < row; col++){
            num = num * (row - col);
            num = num / (col);
            rowList.add(num);
        }
        return rowList;
    }

    // Time Complexity: O(numRows^2)
    // Space Complexity: O(1) excluding the result list
    private void execute(int numRows, List<List<Integer>> res){
        for(int row = 1; row <= numRows; row++){
            res.add(generateRow(row));
        }
    }

    public List<List<Integer>> generate(int numRows) {
        return new AbstractList<List<Integer>>() {
            List<List<Integer>> row;

            void init(){
                row = new java.util.ArrayList<>();
                execute(numRows, row);
            }

            @Override
            public List<Integer> get(int index) {
                return row.get(index);
            }

            @Override
            public int size() {
                if(row == null){
                    init();
                }
                return row.size();
            }
        };
    }

    public static void main(String[] args) {
        PascalTriangle triangle = new PascalTriangle();
        int numRows = 5;
        List<List<Integer>> result = triangle.generate(numRows);
        System.out.println(result);
    }
}
