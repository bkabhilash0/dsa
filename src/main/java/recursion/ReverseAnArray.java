package recursion;

import java.util.ArrayList;
import java.util.List;

public class ReverseAnArray {
    void reverse(List<Integer> list, int l, int r){
        if(l >= r){
            System.out.println(list);
            return;
        }
        int sourceElement = list.get(l);
        list.set(l, list.get(r));
        list.set(r, sourceElement);
        reverse(list, l+1, r-1);
    }

    List<Integer> reverse(List<Integer> list, int i){
        if(i == list.size() / 2){
            return list;
        }
        // Perform the reverse
        int sourceElement = list.get(i);
        int destination = list.size() - i - 1;
        list.set(i, list.get(destination));
        list.set(destination, sourceElement);
        return reverse(list, i+1);
    }
    public static void main(String[] args) {
        ReverseAnArray rev = new ReverseAnArray();
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5,6));
        List<Integer> reversed = rev.reverse(list, 0);
        System.out.println(reversed);
        list = new ArrayList<>(List.of(6,7,8,9,10));
        rev.reverse(list, 0, list.size()-1);
    }
}
