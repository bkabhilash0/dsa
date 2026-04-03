package graphs;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/disjoint-set-union-find/1">Disjoint Set (Union Find)</a>
 * You are given n elements numbered from 1 to n. Initially, each element is in its own group.
 * You need to process k queries. Each query is one of the following types:
 * UNION x z – Merge the groups that contain elements x and z.
 * After this operation, both elements will belong to the same group.
 * FIND x – Output the ultimate representative of the group containing element x.
 * The representative is the element that acts as the "leader" of the group.
 * Initially, every element is the leader of its own group.
 */
public class DisjointSetGFG {
    int[] rank;

    DisjointSetGFG() {
        rank = new int[101];
    }

    int find(int[] par, int x) {
        // add code here.
        if (par[x] == x) return x;
        return par[x] = find(par, par[x]);
    }

    // Time Complexity: O(4alpha) ~= O(1)
    // Space Complexity: O(2n) -> For the rank array for parent + rank arrays
    void unionSet(int[] par, int x, int z) {
        if(rank == null) rank = new int[101];
        // add code here.
        int xRoot = find(par, x);
        int zRoot = find(par, z);
        if (xRoot != zRoot) {
            par[xRoot] = zRoot; // 🔥 force z as leader
        }
//        if(xRoot == zRoot) return;
//        if(rank[xRoot] > rank[zRoot]) {
//            par[zRoot] = xRoot;
//        }else if(rank[xRoot] < rank[zRoot]){
//            par[xRoot] = zRoot;
//        }else{
//            par[xRoot] = zRoot;
//            rank[zRoot]++;
//        }
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 4;
        DisjointSetGFG disjointSetGFG = new DisjointSetGFG();
        int[] par = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            par[i] = i;
        }
        System.out.println(disjointSetGFG.find(par, 4)); // 4
        System.out.println(disjointSetGFG.find(par, 1)); // 1
        disjointSetGFG.unionSet(par, 3, 1);
        System.out.println(disjointSetGFG.find(par, 3)); // 1
        System.out.println(Arrays.toString(par));
    }
}
