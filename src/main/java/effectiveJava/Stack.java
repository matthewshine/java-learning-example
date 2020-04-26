package effectiveJava;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size;
    public Stack(){
        elements = new Object[16];
    }

    public void  push(Object e){
        ensureCapacity();
        elements[size++] =e;
    }
    public Object pop(){
        if(size==0){
            throw new EmptyStackException();
        }
        return elements[--size];
    }
    //当数组长度等于size的时候扩容
    private  void  ensureCapacity(){
        if(elements.length==size){
            elements = Arrays.copyOf(elements,2*size+1);

        }
    }
    public static void main(String[] args) {

    }
}
