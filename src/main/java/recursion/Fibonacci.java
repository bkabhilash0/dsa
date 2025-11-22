package recursion;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 */
public class Fibonacci {
    int fib(int n){
        if(n == 0 || n == 1){
            return n;
        }

        int i = 1;
        int j = 1;
        int pos = 2;
        while(pos < n){
            int sum = i + j;
            i = j;
            j = sum;
            pos++;
        }

        return j;
    }

    int fibonacci(int n){
        if(n == 0 || n == 1){
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        int res = fib.fibonacci(10);
        int res2 = fib.fib(10);
        System.out.printf("The Fibonacci number of %d is %d", 10, res);
        System.out.println();
        System.out.printf("The Fibonacci number of %d is %d", 10, res2);
    }
}
