import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class t1 {
    public static void main(String[]args){
        Character[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Integer[] idxs = {1, 4, 7, 10, 17, 25};
        ArrayList<Character> L = new ArrayList<Character>(Arrays.asList(chars));
        ArrayList<Integer> P = new ArrayList<Integer>(Arrays.asList(idxs));
        long startTime = System.nanoTime();
        printLots(L, P);
        long endTime = System.nanoTime();
        System.out.println("Duration: "+(endTime - startTime) / 1e6 + " ms");
    }
    public static<T> void printLots(Collection<T>L,Collection<Integer>P){
        Iterator iterP= P.iterator();
        Iterator iterL=L.iterator();
        int idxL = 0;
        T itemL = null;
        while (iterP.hasNext() && iterL.hasNext()) {
            int idx = (int) iterP.next();
            while (iterL.hasNext() && idxL < idx) {
                itemL = (T) iterL.next();
                idxL++;
            }
            System.out.print(itemL + " ");
        }
        System.out.println();
    }
}
