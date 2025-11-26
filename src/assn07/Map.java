package assn07;

import java.util.List;
import java.util.Set;

public interface Map <K, V> {

    /**
     * The 'put' method creates an Account object using the key value pair,
     * and inserts the object at the appropriate index
     * based on the hash of they key. If the key already
     * exists in map, update its value.
     * @param key: the website name
     * @param value: the password
     */
    void put(K key, V value);

    /**
     * 'get' returns the value associated with the given key.
     * This operation should have O(1) runtime.
     * If the key is not in the array, return null.
     * @param key
     * @return the value (password) associated with that key
     */
    V get(K key);

    /**
     * 'size' returns the number of key-value mappings in the map.
     * @return the number of accounts in the map.
     */
    int size();

    /**
     * 'keySet' returns a Set of all the keys (websites) contained in this map.
     * @return A set of the keys contained in the map
     */
    Set<K> keySet();

    /**
     * 'remove' removes the Key and value pair from the map
     * and returns the removed value.
     * If the key is not in the array, return null.
     * @param key to be removed
     * @return the value (password) that was removed
     */
    V remove(K key);

    /**
     * 'checkDuplicate' returns an Arraylist of the website names
     * that have a password matching the given value
     * @return A List containing the keys of accounts whose password
     * match the given value
     */
    List<K> checkDuplicates(V value);

    /**
     * Checks to see if the entered Master Password matches
     * the password stored in MASTER_PASSWORD
     * @param enteredPassword - the password the user typed in
     * @return true if passwords match, false otherwise
     */
    boolean checkMasterPassword(String enteredPassword);

    /**
     * Returns basic collision statistics for this hash table implementation.
     * The returned array contains:
     * [0] = total number of accounts
     * [1] = number of buckets in use (non-empty)
     * [2] = number of buckets with collisions (chains of length > 1)
     * [3] = maximum chain length in any bucket
     */
    int[] collisionStats();

    /**
     * generates a safe Random Password.
     * @param length - the length of password to generate
     * @return the generated password
     */
    String generatesafeRandomPassword(int length);

}
