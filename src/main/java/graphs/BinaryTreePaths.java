package graphs;

import graphs.components.TreeNode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/binary-tree-path">Binary Tree Path</a>
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * A leaf is a node with no children.
 */
public class BinaryTreePaths {
    // Assume N = number of nodes in the binary tree, H = height of the binary tree, L = number of leaf nodes in the binary tree
    // Time Complexity: O(N + L * H) where N is the number of nodes in the binary tree, we are visiting each node once
    // Space Complexity: O(N) for the recursive stack and the path list
    public void execute(TreeNode root, ArrayList<String> res, ArrayList<Integer> path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            String finalPath = mapIntToString(path);
            res.add(finalPath);
            return;
        }
        if (root.left != null) {
            path.add(root.left.val);
            execute(root.left, res, path);
            path.removeLast();
        }
        if (root.right != null) {
            path.add(root.right.val);
            execute(root.right, res, path);
            path.removeLast();
        }
    }

    private String mapIntToString(ArrayList<Integer> path) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int num : path) {
            sb.append(num);
            if (index != path.size() - 1) {
                sb.append("->");
            }
            index++;
        }
        return sb.toString();
    }

    private List<String> optimal(TreeNode root) {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        if(root == null) return res;
        path.add(root.val);
        execute(root, res, path);
        return res;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        return optimal(root);
    }

    public static void main(String[] args) {
        BinaryTreePaths btp = new BinaryTreePaths();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        List<String> res = btp.binaryTreePaths(root);
        System.out.println(res);
    }
}
