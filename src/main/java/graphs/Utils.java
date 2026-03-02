package graphs;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    static ArrayList<List<Integer>> createList(int[][] matrix) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < matrix.length; i++) {
            int[] neighbours = matrix[i];
            for (int neighbour : neighbours) {
                list.get(i).add(neighbour);
            }
        }
        return list;
    }
}
