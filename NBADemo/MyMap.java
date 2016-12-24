package NBADemo;
import java.util.Iterator;
import java.util.NoSuchElementException;
// implements MMap interface to store the information of players

public class MyMap<K,V> implements MMap<K,V>, Iterable<K> {
	 /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list.  
      */
	 private Entry entryList;
	 private int size;

	 @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
    	entryList = null;
    	size = 0;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
    	return (entryList.get(key) != null) ;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    @Override
    public V get(K key) {
    	if (entryList.get(key)!= null) {
    		return entryList.get(key).val;
    	}
    	else {
    		return null;
    	}
    }

    /** Set the value of a specific Key-value pair to a new Value */
    @Override
    public void set(K key, V value) {
        if ( entryList.get(key) != null ) {
            entryList.set(key,value);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (entryList != null) {
            if (entryList.get(key) != null) {
                entryList.get(key).val = value;
            }
            else {
                entryList = new Entry(key, value, entryList);
                size += 1;
            }
        }
        else {
            entryList = new Entry(key, value, entryList);
            size += 1;
        }    	
    }

    /* Removes the mapping for the specified key from this map if present.
     */
    @Override
    public V remove(K key) {
    	if ( entryList.get(key) == null) {
    		return null;
    	}
    	else {
    		V removeValue = entryList.get(key).getVal();
    		entryList.get(key).val = null;
    		size--;
    		return removeValue;
    	}
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value*/
    @Override
    public V remove(K key, V value) {
    	if ( entryList.get(key) == null) {
    		return null;
    	}
    	else {
    		V removeValue = entryList.get(key).getVal();
    		entryList.get(key).val = null;
    		entryList.get(key).key = null;
    		size--;
    		return removeValue;
    	}
    }

    @Override
    public Iterator<K> iterator() {
        return new ULLMapIter();
    }

    private class ULLMapIter implements Iterator<K> {
        private Entry curr;

        public ULLMapIter() {
            curr = entryList;
        }

        @Override
        public boolean hasNext() {
            return (curr != null);
        }

        @Override
        public K next() {
            if (this.hasNext()) {
                K i = curr.key;
                curr = curr.next;
                return i;
            }
            else {throw new NoSuchElementException();}
        }


        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }    

	private class Entry {
	 	private K key;
	 	private V val;
	 	private Entry next;

        public Entry(K key, V val, Entry next) { 
            this.key = key;
            this.val = val;
            this.next = next;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K ke) { 
            
            if (key.equals(ke)) {
                return this;
            }
            if (key.equals(null)){
                return null;
            }
            if (this.next == null) {
                return null;
            }
            return this.next.get(ke); 
        }


        /** set the value of the Entry in this linked list with a specific key
         */
        public void set(K ke, V newVal) { 
            if (key.equals(ke)) {
                this.val = newVal;
                return;
            }

            if (key.equals(null)){
                return;
            }

            if (this.next == null) {
                return;
            }
            this.next.get(ke);            
        
        }

        public V getVal() {
        	return this.val;
        }

	 }
}