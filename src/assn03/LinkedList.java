package assn03;

// Starter Code provided with Assignment #3 for COMP210 (Fall 2025)

public class LinkedList {
    private Node _head = null;
    private Node _tail = null;
    private int _size = 0;

    /**
     * Task 1
     * Simply merge given linked (list2) at the start of the current list.
     * <p>
     * Note: Do NOT create and return a new list, merge the second list at the start of the first one.
     * <p>
     * ex: list: 1 -> 2 -> 3
     * list2: 4 -> 5 -> 6
     * return: 4 -> 5 -> 6 -> 1 -> 2 -> 3
     *
     * @param list2 - list to be merged
     */
    public void simpleMerge(LinkedList list2) {
        if (list2 == null || list2._head == null) return;
        if (list2 == this) return; // avoid self-merge

        // find tail of list2 (or use list2._tail if guaranteed correct)
        Node tail = list2._tail != null ? list2._tail : list2._head;
        while (tail.getNext() != null) tail = tail.getNext();

        // stitch: list2 tail -> current head
        tail.setNext(this._head);

        // new head is list2 head
        this._head = list2._head;

        // update size
        this._size += list2._size;

        // update tail: if current list was empty, tail is list2's tail; else keep current tail
        if (this._tail == null) this._tail = tail;

        // consume list2 (optional but safer)
        list2._head = null;
        list2._tail = null;
        list2._size = 0;
    }


    /**
     * Task 2
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the _size of the list, throw an IndexOutOfBounds Exception
     * <p>
     * ex: list: A -> B -> C -> D
     * i: 1
     * list after removeAtIndex: A -> C -> D
     *
     * @param i - index of node to remove
     */
    public void removeAtIndex(int index) {
        if (index < 0 || index >= _size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            // remove head
            if (_head != null) {
                _head = _head.getNext();
                _size--;
                if (_size == 0) _tail = null;          // list became empty
            }
            return;
        }

        // walk to node BEFORE the one we remove
        Node prev = _head;
        for (int i = 0; i < index - 1; i++) prev = prev.getNext();

        Node target = prev.getNext();                 // node to remove
        Node after = (target == null) ? null : target.getNext();
        prev.setNext(after);

        // if we removed the last node, update _tail
        if (after == null) _tail = prev;

        _size--;
    }

    /**
     * Task 3
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same _size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     * list2: 1 -> 4 -> 2
     * return: true
     * <p>
     * list: 1 -> 5
     * list2: 2 -> 5
     * return false;
     *
     * @param list2 - the list to compare with the current list
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList other) {
        if (other == null) return false;
        if (this._size != other._size) return false;

        Node a = this._head, b = other._head;
        while (a != null && b != null) {
            if (!java.util.Objects.equals(a.getValue(), b.getValue())) return false;
            a = a.getNext();
            b = b.getNext();
        }
        return a == null && b == null; // both ended together
    }


    /**
     * Task 4
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     * list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        Node curr = _head;
        while (curr != null && curr.getNext() != null) {
            if (java.util.Objects.equals(curr.getValue(), curr.getNext().getValue())) {
                // skip duplicate node
                curr.setNext(curr.getNext().getNext());
                _size--;
                // if we just removed the last node, update _tail
                if (curr.getNext() == null) _tail = curr;
            } else {
                curr = curr.getNext();
            }
        }
    }


    /**
     * Task 5
     * Reverse the list.
     * eg list:  10 -> 9 -> 8 -> 7
     * list after reverse: 7 -> 8 -> 9 -> 10
     */
    public void reverse() {
        Node prev = null, curr = _head;
        // old head becomes new tail
        _tail = _head;

        while (curr != null) {
            Node next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        _head = prev;
        // _size unchanged
    }

    /**
     * Task 6
     * Merge the given linked list2 into the current list. The 2 lists will always be
     * either the same _size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     * <p>
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     * <p>
     * ex: list: 1 -> 2 -> 3
     * list2: 4 -> 5 -> 6
     * return: 4 -> 1 -> 5 -> 2 -> 6 -> 3
     * <p>
     * list: 1 -> 2 -> 3 -> 4
     * list2: 5 -> 6
     * return 5 -> 1 -> 6 -> 2 -> 3 -> 4
     *
     * @param list2
     */

    public void merge(LinkedList list2) {
        if (list2 == null || list2._head == null) return;
        if (list2 == this) return;

        Node p1 = this._head;   // walk this list
        Node p2 = list2._head;  // walk list2

        // new head must start with list2's first node
        this._head = p2;

        while (p2 != null) {
            Node next2 = p2.getNext();
            p2.setNext(p1);             // ...p2 -> p1

            if (p1 == null) break;      // if this list ran out (rare given precond), stop

            Node next1 = p1.getNext();

            if (next2 == null) {
                // list2 is about to finish; keep rest of this list attached
                p1.setNext(next1);      // already true, but explicit for clarity
                break;
            }

            p1.setNext(next2);          // ...p1 -> next list2
            p1 = next1;                 // advance both
            p2 = next2;
        }

        // size becomes sum; list2 is consumed
        this._size += list2._size;
        list2._head = null;
        list2._tail = null;
        list2._size = 0;

        // recompute tail (simple & safe)
        Node t = this._head;
        if (t == null) {
            _tail = null;
        } else {
            while (t.getNext() != null) t = t.getNext();
            _tail = t;
        }
    }

    /* Implementations below are being given to you. Do not modify below this line. */

    public int size() {
        return _size;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public void clear() {
        _head = null;
        _tail = null;
        _size = 0;
    }

    public boolean contains(int element) {
        Node current = _head;
        while (current != null) {
            if (current.getValue() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int[] toArray() {
        int[] arr = new int[size()];
        Node current = _head;
        int i = 0;
        if (isEmpty()) {
            return arr;
        }
        while (current != null) {
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(int element) {
        Node newNode = new NodeImpl(element, null);
        if (isEmpty()) {
            _head = newNode;
            _tail = newNode;
            _size++;
        } else {
            _tail.setNext(newNode);
            _tail = newNode;
            _size++;
        }

    }

    public boolean remove(int element) {
        Node current = _head;
        if (isEmpty()) {
            return false;
        }
        if (current.getValue() == element) {
            _head = _head.getNext();
            _size--;
            return true;
        }
        while (current.getNext().getValue() != element) {
            current = current.getNext();
            if (current == null) {
                return false;
            }
        }
        if (current.getNext().getNext() == null) {
            _tail = current;
        }
        current.setNext(current.getNext().getNext());
        _size--;
        return true;
    }

    public int get(int index) {
        validIndex(index);
        Node current = _head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public int set(int index, int element) {
        validIndex(index);
        Node current = _head;
        int prevValue = 0;
        int i = 0;
        if (index == 0) {
            prevValue = _head.getValue();
            _head.setValue(element);
        } else {
            while (current != null) {
                if (i == index) {
                    prevValue = current.getValue();
                    current.setValue(element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, int element) {
        if (index > _size) {
            validIndex(index);
        }
        Node current = _head;
        int i = 0;
        if (index == 0) {
            if (isEmpty()) {
                add(element);
                return;
            } else {
                Node newNode = new NodeImpl(element, _head.getNext());
                _head = newNode;
                _size++;
                return;
            }

        } else if (index == _size) {
            add(element);
            return;
        }
        while (current != null) {
            if (i == (index - 1)) {
                Node temp = current.getNext();
                Node newNode = new NodeImpl(element, temp);
                current.setNext(newNode);
                _size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(int element) {
        Node current = _head;
        int index = 0;
        while (current != null) {
            if (current.getValue() == element) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(int element) {
        Node current = _head;
        int index = -1;
        int i = 0;
        while (current != null) {
            if (current.getValue() == element) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if (i < 0 || i >= _size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public Node gethead() {
        return _head;
    }

    @Override
    public String toString() {
        String list = "";
        Node current = _head;
        while (current != null) {
            if (current.getNext() == null)
                list += current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}