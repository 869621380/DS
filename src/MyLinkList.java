import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyLinkList<AnyType> implements Iterable<AnyType> {
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            date = d;
            prev = p;
            next = n;
        }
        public AnyType date;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }
    public MyLinkList(){doClear();}
    public void clear(){doClear();}
    private void doClear(){
        beginMaker=new Node<>(null,null,null);
        endMaker=new Node<>(null,beginMaker,null);
        beginMaker.next=endMaker;
        theSize=0;
        modCount++;
    }
    public int size(){return theSize;}
    public boolean isEmpty(){return theSize==0;}
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }
    public void add(int idx,AnyType x){
        addBefore(getNode(idx,0,size()),x);
    }
    public AnyType remove(int idx){
        return remove(getNode(idx));
    }
    public AnyType get(int idx){
        return getNode(idx).date;
    }
    public AnyType set(int idx,AnyType newVal){
        Node<AnyType>p=getNode(idx);
        AnyType oldVal=p.date;
        p.date=newVal;
        return oldVal;
    }
    public java.util.Iterator<AnyType>iterator(){
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType>current=beginMaker.next;
        private int expectedModCount=modCount;
        private boolean okToRemove=false;
        @Override
        public boolean hasNext() {
            return current!=endMaker;
        }
        @Override
        public AnyType next(){
            if(modCount!=expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();
            AnyType nextItem=current.date;
            current=current.next;
            okToRemove=true;
            return nextItem;
        }
        @Override
        public void remove() {
            if(modCount!=expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            MyLinkList.this.remove(current.prev);
            expectedModCount++;
            okToRemove=false;
        }
    }
    private void addBefore(Node<AnyType>p,AnyType x){
        Node<AnyType>newNode=new Node<>(x,p.prev,p);
        newNode.prev.next=newNode;
        newNode.next.prev=newNode;
        theSize++;
        modCount++;
    }
    private AnyType remove(Node<AnyType>p){
        p.next.prev=p.prev;
        p.prev.next=p.next;
        theSize--;
        modCount++;
        return p.date;
    }
    private Node<AnyType>getNode(int idx){
        return getNode(idx,0,size()-1);
    }
    private Node<AnyType>getNode(int idx,int lower,int upper){
        Node<AnyType>p;
        if(idx<lower||idx>upper)throw new IndexOutOfBoundsException();
        if(idx<size()/2){
            p=beginMaker.next;
            for(int i=0;i<idx;i++)p=p.next;
        }
        else {
            p=endMaker.prev;
            for (int i=size();i>idx;i--)p=p.prev;
        }
        return p;
    }
    private int theSize;
    private int modCount=0;
    private Node<AnyType>beginMaker;
    private Node<AnyType>endMaker;
}
