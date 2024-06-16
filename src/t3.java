import java.util.Collection;
import java.util.Iterator;
public class t3 {
    public static <AnyType> boolean contains(Collection<AnyType> c, AnyType x){
        Iterator<AnyType> iterC= c.iterator();
        while(iterC.hasNext()){
            if(x.equals( iterC.next()))
                return true;
        }
        return false;
    }
}
