import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<T> implements Iterable<T> {
    public MyStack(){
        this(10);
    }
    public MyStack(int initialCapacity){
        theSize=0;
        ensureCapacity(initialCapacity);
    }
    public boolean empty(){
        return size()==0;
    }
    //返回栈中元素个数
    public int size(){
        return theSize;
    }
    //返回栈顶元素
    public T top(){
        if(empty())throw new EmptyStackException();
        return stack[theSize-1];
    }
    //删除栈顶元素
    public void pop(){
        if(empty())throw new EmptyStackException();
        theSize--;
    }
    //将元素压入栈顶
    public void push(T theElement){
        if(theSize==stack.length)ensureCapacity(2*theSize+1);
        stack[theSize++]=theElement;
    }
    @Override
    public Iterator<T> iterator() {
        return new stackIterator();
    }
    private class stackIterator implements java.util.Iterator<T>{
        private int current=theSize;
        @Override
        public boolean hasNext(){
            return current>0;
        }
        @Override
        public T next(){
            if(!hasNext())throw new NoSuchElementException();
            return stack[--current];
        }
    }
    private int theSize;
    private T[ ]stack;
    private void ensureCapacity(int newCapacity){
        if(newCapacity<theSize)return;
        T[]old=stack;
        stack=(T[])new Object[newCapacity];
        System.arraycopy(old, 0, stack, 0, size());
    }
}