package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    T[] array;
    Predicate<T> filter;

    public IterableWithPolicyImpl(T[] array) {
        this(array, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });

    }
    
    public IterableWithPolicyImpl(T[] array, Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }


    private class MyIterator implements Iterator<T> {

        int i = 0;

        @Override
        public boolean hasNext() {
            while(array.length > i){

                if(filter.test(array[i])){
                    return true;
                }
                i++;
            }
            
            return false;
        }

        @Override
        public T next() {
                
            if(this.hasNext()){
                return array[i++];
            }
            throw new NoSuchElementException();

        }
        
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

}
