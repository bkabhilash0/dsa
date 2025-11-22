package recursion;

public class Factorial {
    int execute(int n){
        if(n == 0 || n == 1){
            return n;
        }
        return n * execute(n-1);
    }

    void execute(int n, int res){
        if(n == 0 || n == 1){
            System.out.printf("The factorial is %d", res);
            return;
        }
        execute(n - 1, res * n);
    }
    public static void main(String[] args) {
        Factorial fac = new Factorial();
        int res = fac.execute(5);
        System.out.printf("The factorial of %d is %d", 5, res);
        System.out.println();
        fac.execute(5, 1);
    }
}
