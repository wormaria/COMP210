package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER<V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /** Empty heap */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    /**
     * O(log n): append new (value, priority), then bubble up while parent has lower priority.
     */
    @Override
    public void enqueue(V value, P priority) {
        // Use your Patient<V,P> which implements Prioritized<V,P>
        _heap.add(new Patient<>(value, priority));
        bubbleUp(_heap.size() - 1);
    }

    /**
     * Overload: create an item with a random priority (matches your Patient(value) ctor)
     */
    public void enqueue(V value) {
        _heap.add(new Patient<>(value)); // Patient(value) assigns random priority
        bubbleUp(_heap.size() - 1);
    }

    /**
     * O(log n): remove and return value of max-priority element (root).
     */
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) return null;

        V top = _heap.get(0).getValue();

        // Move last element to root and shrink; then restore heap property
        Prioritized<V,P> last = _heap.remove(_heap.size() - 1);
        if (!_heap.isEmpty()) {
            _heap.set(0, last);
            bubbleDown(0);
        }
        return top;
    }

    /**
     * O(1): peek at max, or null if empty.
     */
    @Override
    public V getMax() {
        return _heap.isEmpty() ? null : _heap.get(0).getValue();
    }

    /**
     * Remove all entries with priority < threshold, keeping heap property.
     * Simple, correct approach: filter then heapify (O(n)).
     */
    @Override
    public void removeBelow(P threshold) {
        // 1) Keep only nodes with priority >= threshold
        ArrayList<Prioritized<V,P>> kept = new ArrayList<>(_heap.size());
        for (Prioritized<V,P> node : _heap) {
            if (node.getPriority().compareTo(threshold) >= 0) {
                kept.add(node);
            }
        }

        // 2) Replace and heapify bottom-up
        _heap.clear();
        _heap.addAll(kept);
        for (int i = parentIndex(_heap.size() - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    /**
     * Build-heap constructor from an initial array (heapify in O(n)).
     */
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries) {
        _heap = new ArrayList<>(initialEntries == null ? 0 : initialEntries.length);
        if (initialEntries == null || initialEntries.length == 0) return;

        // 1) copy entries in array order
        for (Prioritized<V, P> e : initialEntries) {
            if (e != null) _heap.add(e); // skip nulls just in case
        }

        // 2) bottom-up heapify (Build-Heap) — O(n)
        for (int i = parentIndex(_heap.size() - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result =
                (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    // ========= Heap helpers =========

    private void bubbleUp(int i) {
        while (i > 0) {
            int p = parentIndex(i);
            // If child has strictly higher priority than parent, swap
            if (_heap.get(i).compareTo(_heap.get(p)) > 0) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int i) {
        int n = _heap.size();
        while (true) {
            int left = leftIndex(i), right = rightIndex(i), largest = i;

            if (left < n && _heap.get(left).compareTo(_heap.get(largest)) > 0) {
                largest = left;
            }
            if (right < n && _heap.get(right).compareTo(_heap.get(largest)) > 0) {
                largest = right;
            }
            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    private static int parentIndex(int i) { return (i - 1) / 2; }
    private static int leftIndex(int i)   { return 2 * i + 1; }
    private static int rightIndex(int i)  { return 2 * i + 2; }

    private void swap(int i, int j) {
        Prioritized<V,P> tmp = _heap.get(i);
        _heap.set(i, _heap.get(j));
        _heap.set(j, tmp);
    }
}
