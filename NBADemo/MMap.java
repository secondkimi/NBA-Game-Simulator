package NBADemo;
// This is a simplified interface of Map
public interface MMap<K, V> {
    /** Removes all of the mappings from this map. */
    public void clear();

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key);

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    public V get(K key);

    /** Set the value of a specific Key-value pair to a new Value */
    public void set(K key, V value);

   /* Returns the number of key-value mappings in this map. */
    public int size();

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value);

    /* Removes the mapping for the specified key from this map if present.
     */
    public V remove(K key);

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value*/
    public V remove(K key, V value);   
}