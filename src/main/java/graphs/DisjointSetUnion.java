package graphs;

public class DisjointSetUnion {
    private int[] parent;
    private int[] rank;
    private int[] sizes;
    private boolean bySize;
    private int size;
    private int numOfConnectedComponents;
    private int numberOfExtraEdges;

    private void init(int n) {
        parent = new int[n];
        rank = new int[n];
        sizes = new int[n];
        size = n;
        numOfConnectedComponents = n;
        numberOfExtraEdges = 0;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            sizes[i] = 1;
        }
    }

//    DisjointSetUnion(int n, boolean isOneBasedIndex) {
//        this.init(isOneBasedIndex ? n + 1 : n);
//    }

    public DisjointSetUnion(int n) {
        this.init(n);
    }

    DisjointSetUnion(int n, boolean bySize) {
        this.init(n);
        this.bySize = bySize;
    }


    public int[] getSizes() {
        return sizes;
    }

    public void setSizes(int[] sizes) {
        this.sizes = sizes;
    }

    public boolean isBySize() {
        return bySize;
    }

    public void setBySize(boolean bySize) {
        this.bySize = bySize;
    }

    public int getNumberOfExtraEdges() {
        return numberOfExtraEdges;
    }

    public void setNumberOfExtraEdges(int numberOfExtraEdges) {
        this.numberOfExtraEdges = numberOfExtraEdges;
    }

    public void setParent(int[] parent) {
        this.parent = parent;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumOfConnectedComponents(int numOfConnectedComponents) {
        this.numOfConnectedComponents = numOfConnectedComponents;
    }

    public int[] getParent() {
        return parent;
    }

    public int[] getRank() {
        return rank;
    }

    public int getSize() {
        return size;
    }

    public int getNumOfConnectedComponents() {
        return numOfConnectedComponents;
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            numberOfExtraEdges++;
            return false;
        }
        if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else if (rank[yRoot] > rank[xRoot]) {
            parent[xRoot] = yRoot;
        } else {
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
        numOfConnectedComponents--;
        return true;
    }

    public boolean unionBySize(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            numberOfExtraEdges++;
            return false;
        }
        if (sizes[xRoot] < sizes[yRoot]) {
            parent[xRoot] = yRoot;
            sizes[yRoot] += sizes[xRoot];
        } else if (sizes[yRoot] < sizes[xRoot]) {
            parent[yRoot] = xRoot;
            sizes[xRoot] += sizes[yRoot];
        } else {
            parent[xRoot] = yRoot;
            sizes[yRoot] += sizes[xRoot];
        }
        numOfConnectedComponents--;
        return true;
    }
}
