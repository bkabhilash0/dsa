package recursion;

public class SumOfNaturalNumbers {
    int sumOfNaturalNumbers(int n){
        if(n == 0){
            return 0;
        }
        return n + sumOfNaturalNumbers(n-1);
    }

    void sumOfNaturalNumbers(int n, int sum) {
        if(n == 0){
            System.out.printf("The sum of natural numbers is %d", sum);
            return;
        }
        sumOfNaturalNumbers(n-1, sum+n);
    }
    public static void main(String[] args) {
        SumOfNaturalNumbers sum = new SumOfNaturalNumbers();
        int res = sum.sumOfNaturalNumbers(10);
        System.out.printf("The sum of natural numbers upto %d is %d", 10, res);
        System.out.println();
        sum.sumOfNaturalNumbers(10, 0);
    }
}
