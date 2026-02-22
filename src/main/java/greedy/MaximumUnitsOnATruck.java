package greedy;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/maximum-units-on-a-truck/description/">Maximum Units on a Truck</a>
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 * Return the maximum total number of units that can be put on the truck.
 */
public class MaximumUnitsOnATruck {
    private static class BoxType {
        int numberOfBoxes;
        int numberOfUnitsPerBox;
        int totalUnits;

        public BoxType(int numberOfBoxes, int numberOfUnitsPerBox) {
            this.numberOfBoxes = numberOfBoxes;
            this.numberOfUnitsPerBox = numberOfUnitsPerBox;
            this.totalUnits = numberOfBoxes * numberOfUnitsPerBox;
        }
    }

    private BoxType[] createBoxTypes(int[][] boxTypes) {
        BoxType[] types = new BoxType[boxTypes.length];
        for (int i = 0; i < boxTypes.length; i++) {
            types[i] = new BoxType(boxTypes[i][0], boxTypes[i][1]);
        }
        return types;
    }

    // Time Complex: O(n log n) where n is the number of box types, we are sorting the box types based on the total units in descending order
    // Space Complex: O(n) where n is the number of box types
    private int optimal(int[][] boxTypes, int truckSize) {
        BoxType[] types = createBoxTypes(boxTypes);
        // We have to weight the box types based on the number of units per box, since we want to maximize the total number of units we can put on the truck,
        // we want to take the box types with the highest number of units per box first.
        Arrays.sort(types, (a, b) -> b.numberOfUnitsPerBox - a.numberOfUnitsPerBox);
        int totalUnits = 0;
        for (BoxType box : types) {
            // If the truck can accommodate all the boxes of the current type, we take all of them and move on to the next type.
            if (box.numberOfBoxes <= truckSize) {
                // Truck capacity is max number of boxes not the max number of units so we need to decrease the truck size
                // by the number of boxes we are taking and increase the total units by the total units of the boxes we are taking.
                truckSize -= box.numberOfBoxes;
                totalUnits += box.totalUnits;
            } else {
                // If the truck cannot accommodate all the boxes of the current type, we take as many as we can and then break out of the loop since the truck is full.
                totalUnits += box.numberOfUnitsPerBox * truckSize;
                break;
            }
        }
        return totalUnits;
    }

    // boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]
    // Time Complex: O(n log n) where n is the number of box types, we are sorting the box types based on the number of units per box in descending order
    // Space Complex: O(1) since we are sorting the boxTypes array in place
    private int moreOptimal(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int totalUnits = 0;
        for (int[] box : boxTypes) {
            // If the number of boxes are the current type is greater than the capacity then load the truck completely with the current type and break out of the loop since the truck is full.
            if (box[0] >= truckSize) {
                totalUnits += box[1] * truckSize;
                truckSize = 0;
            } else {
                totalUnits += box[1] * box[0];
                truckSize -= box[0];
            }
            if (truckSize == 0) break;
        }
        return totalUnits;
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        return moreOptimal(boxTypes, truckSize);
    }

    public static void main(String[] args) {
        MaximumUnitsOnATruck solution = new MaximumUnitsOnATruck();
        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
        int truckSize = 4;
        System.out.println(solution.maximumUnits(boxTypes, truckSize)); // Output: 8
    }
}
