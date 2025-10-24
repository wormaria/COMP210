package assn05;

/**
 * Interface for a BinaryHeap
 * @param <V> value
 * @param <P> priority
 */
public interface BinaryHeap<V, P extends Comparable<P>> {

    /** @return number of elements in heap */
    int size();

    /**
     * Create a new item with the given value and priority and enqueue it into the heap.
     * @param value the value to store
     * @param priority the priority of the element
     */
    void enqueue(V value, P priority);

    /**
     * Dequeue the element with the highest priority from the heap.
     * @return the value of the removed element, or null if the heap is empty
     */
    V dequeue();

    /**
     * Return the value of the element with the highest priority without removing it.
     * @return the value of the element with the highest priority, or null if the heap is empty
     */
    V getMax();

    /**
     * Retrieves contents of heap as an array (in internal heap order).
     * @return array of Prioritized elements in the order stored in the heap
     */
    Prioritized<V, P>[] getAsArray();
    
    /**
     * Removes all patients whose priority is below the given threshold.
     * Operates directly on the heap structure (in-place).
     * After removal, the heap still satisfies the max-heap property.
     * @param threshold the minimum priority required to remain in the heap
     */
    void removeBelow(P threshold);
}
