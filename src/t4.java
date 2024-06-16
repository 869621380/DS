import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
public class t4 {
    public static void main(String[]args){
        Integer []L1={1,3,5,7,9,12};
        Integer []L2={1,5,6,7,9,10};
        ArrayList<Integer> L=(ArrayList<Integer>) intersection(Arrays.asList(L1),Arrays.asList(L2));
        System.out.println(L);
    }
    public static Collection<Integer> intersection(Collection<Integer>L1,Collection<Integer>L2) {
        Iterator<Integer> iter1 = L1.iterator();
        Iterator<Integer> iter2 = L2.iterator();
        ArrayList<Integer> L = new ArrayList<>();
        Integer i2 = iter2.next();
        while (iter1.hasNext()) {
            Integer i1 = iter1.next();
            if (i1 == i2) L.add(i1);
            else if (i1 > i2) {
                while (i1 > i2 && iter2.hasNext()) {
                    i2 = iter2.next();
                }
                if (i1 == i2) L.add(i1);
            }

        }
        return L;
    }
}
