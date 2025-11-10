package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }

    // ================================================
    // Helper methods !!!
    // ================================================

    private int nodeHeight(AVLTree<T> node) {
        return (node == null) ? -1 : node._height;
    }

    private int nodeSize(AVLTree<T> node) {
        return (node == null) ? 0 : node._size;
    }

    private void update() {
        _height = 1 + Math.max(nodeHeight(_left), nodeHeight(_right));
        _size = 1 + nodeSize(_left) + nodeSize(_right);
    }

    private int balanceFactor() {
        return nodeHeight(_left) - nodeHeight(_right);
    }



    //==================================================================
    // The methods below need to be completed.
    // Refer to the SelfBalancingTree interface for the descriptions.
    //==================================================================

    // ============================
// ROTATIONS
// ============================

    private AVLTree<T> rotateLeft() {
        AVLTree<T> newRoot = _right;
        AVLTree<T> transfer = newRoot._left;

        // Perform rotation
        newRoot._left = this;
        this._right = transfer;

        // Update both
        this.update();
        newRoot.update();

        return newRoot;
    }

    private AVLTree<T> rotateRight() {
        AVLTree<T> newRoot = _left;
        AVLTree<T> transfer = newRoot._right;

        // Perform rotation
        newRoot._right = this;
        this._left = transfer;

        // Update both
        this.update();
        newRoot.update();

        return newRoot;
    }


    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (isEmpty()) {
            _value = element;
            _height = 0;
            _size = 1;
            _left = null;
            _right = null;
            return this;
        }

        int cmp = element.compareTo(_value);
        if (cmp < 0) {
            if (_left == null) _left = new AVLTree<>();
            _left = (AVLTree<T>) _left.insert(element);
        } else if (cmp > 0) {
            if (_right == null) _right = new AVLTree<>();
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            // duplicate — do nothing
            return this;
        }

        // Update height and size
        update();

        // Rebalance
        return rebalance();
    }
    private AVLTree<T> rebalance() {
        int balance = balanceFactor();

        // Left heavy
        if (balance > 1) {
            if (_left != null && _left.balanceFactor() < 0) {
                _left = _left.rotateLeft(); // LR case
            }
            return rotateRight(); // LL case
        }

        // Right heavy
        if (balance < -1) {
            if (_right != null && _right.balanceFactor() > 0) {
                _right = _right.rotateRight(); // RL case
            }
            return rotateLeft(); // RR case
        }

        return this; // balanced
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (isEmpty()) return this;

        int cmp = element.compareTo(_value);
        if (cmp < 0) {
            if (_left != null) _left = (AVLTree<T>) _left.remove(element);
        } else if (cmp > 0) {
            if (_right != null) _right = (AVLTree<T>) _right.remove(element);
        } else {
            // Found node to remove

            // Case 1: no children
            if (_left == null && _right == null) {
                return new AVLTree<>();
            }

            // Case 2: one child
            if (_left == null) return _right;
            if (_right == null) return _left;

            // Case 3: two children -> replace with min from right
            T successor = _right.findMin();
            _value = successor;
            _right = (AVLTree<T>) _right.remove(successor);
        }

        update();
        return rebalance();
    }


    @Override
    public T findMin() {
        if (_left == null || _left.isEmpty()) return _value;
        return _left.findMin();
    }

    @Override
    public T findMax() {
        if (_right == null || _right.isEmpty()) return _value;
        return _right.findMax();
    }


    @Override
    public boolean contains(T element) {
        if (isEmpty()) return false;
        int cmp = element.compareTo(_value);
        if (cmp == 0) return true;
        if (cmp < 0) return (_left != null) && _left.contains(element);
        return (_right != null) && _right.contains(element);
    }


    @Override
    public int countInRange(T start, T end) {
        if (isEmpty()) return 0;
        int count = 0;
        int cmpLow = _value.compareTo(start);
        int cmpHigh = _value.compareTo(end);

        if (cmpLow >= 0 && cmpHigh <= 0) count = 1;
        if (_left != null && cmpLow > 0) count += _left.countInRange(start, end);
        if (_right != null && cmpHigh < 0) count += _right.countInRange(start, end);
        return count;
    }


}
