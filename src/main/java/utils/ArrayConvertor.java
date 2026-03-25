package utils;

public class ArrayConvertor {
    public static int[][] parse2DArray(String input) {
        // Remove outer brackets
        input = input.substring(1, input.length() - 1);

        // Split rows
        String[] rows = input.split("\\],\\[");

        int[][] result = new int[rows.length][3]; // assuming each row has 3 elements

        for (int i = 0; i < rows.length; i++) {
            // Clean brackets
            String row = rows[i].replace("[", "").replace("]", "");

            String[] nums = row.split(",");

            for (int j = 0; j < nums.length; j++) {
                result[i][j] = Integer.parseInt(nums[j].trim());
            }
        }

        return result;
    }
}
