package recursion;

public class Palindrome {
    boolean palindrome(String str, int i){
        if(i == str.length()/2){
            return true;
        }

//        if(str.charAt(i) != str.charAt(str.length()-i-1)){
//            return false;
//        }

//        return palindrome(str, i+1);

//        Shorthand Syntax
        return palindrome(str, i+1) && str.charAt(i) == str.charAt(str.length()-i-1);
    }

    void palindrome(String str, int l, int r){
        if(l >= r){
            System.out.printf("%s is a palindrome\n", str);
            return;
        }
        if(str.charAt(l) != str.charAt(r)){
            System.out.printf("%s is not a palindrome\n", str);
            return;
        }

        palindrome(str, l+1, r-1);
    }

    public static void main(String[] args) {
        Palindrome pal = new Palindrome();
        pal.palindrome("abba", 0, 3);
        pal.palindrome("apple", 0, 4);

        String str = "abba";
        System.out.printf("%s is a palindrome: %b\n", str, pal.palindrome(str, 0));

        str = "apple";
        System.out.printf("%s is a palindrome: %b\n", str, pal.palindrome(str, 0));
    }
}
