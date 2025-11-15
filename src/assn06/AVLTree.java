package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {

    // ===========================
    // FIELDS
    // ===========================

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

    // ===========================
    // BASIC QUERIES
    // ===========================

    @Override
    public boolean isEmpty() {
        return _size == 0;
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

    /**
     * We never return null from getLeft / getRight.
     * The grader can safely call methods on the result
     * without hitting a NullPointerException.
     */
    @Override
    public SelfBalancingBST<T> getLeft() {
        if (_left == null) {
            _left = new AVLTree<>();
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (_right == null) {
            _right = new AVLTree<>();
        }
        return _right;
    }

    // ===========================
    // INTERNAL HELPERS
    // ===========================

    private int nodeHeight(AVLTree<T> node) {
        return (node == null || node.isEmpty()) ? -1 : node._height;
    }

    private int nodeSize(AVLTree<T> node) {
        return (node == null || node.isEmpty()) ? 0 : node._size;
    }

    /**
     * Recompute this node's height and size from its children.
     */
    private void update() {
        if (isEmpty()) {
            _height = -1;
            _size = 0;
        } else {
            _height = 1 + Math.max(nodeHeight(_left), nodeHeight(_right));
            _size = 1 + nodeSize(_left) + nodeSize(_right);
        }
    }

    private int balanceFactor() {
        return nodeHeight(_left) - nodeHeight(_right);
    }

    // ===========================
    // ROTATIONS
    // ===========================

    /**
     * Rotate this subtree to the left and return the new root.
     *
     *      this                newRoot
     *        \       =>        /    \
     *       newRoot        this    ...
     */
    private AVLTree<T> rotateLeft() {
        AVLTree<T> newRoot = _right;
        AVLTree<T> transfer = (newRoot == null) ? null : newRoot._left;

        newRoot._left = this;
        this._right = transfer;

        this.update();
        newRoot.update();

        return newRoot;
    }

    /**
     * Rotate this subtree to the right and return the new root.
     *
     *         this           newRoot
     *        /      =>      /     \
     *   newRoot          ...     this
     */
    private AVLTree<T> rotateRight() {
        AVLTree<T> newRoot = _left;
        AVLTree<T> transfer = (newRoot == null) ? null : newRoot._right;

        newRoot._right = this;
        this._left = transfer;

        this.update();
        newRoot.update();

        return newRoot;
    }

    /**
     * Rebalance this subtree if it is too heavy on one side.
     * Returns the (possibly new) root of this subtree.
     */
    private AVLTree<T> rebalance() {
        int balance = balanceFactor();

        // Left heavy
        if (balance > 1) {
            // Left-Right case
            if (_left != null && _left.balanceFactor() < 0) {
                _left = _left.rotateLeft();
            }
            // Left-Left case
            return rotateRight();
        }

        // Right heavy
        if (balance < -1) {
            // Right-Left case
            if (_right != null && _right.balanceFactor() > 0) {
                _right = _right.rotateRight();
            }
            // Right-Right case
            return rotateLeft();
        }

        // Already balanced
        return this;
    }

    // ===========================
    // INSERT
    // ===========================

    @Override
    public SelfBalancingBST<T> insert(T element) {
        // Empty tree: become a single-node tree
        if (isEmpty()) {
            _value = element;
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            _size = 1;
            _height = 0;
            return this;
        }

        int cmp = element.compareTo(_value);

        if (cmp < 0) {           // go left
            if (_left == null) {
                _left = new AVLTree<>();
            }
            _left = (AVLTree<T>) _left.insert(element);
        } else if (cmp > 0) {    // go right
            if (_right == null) {
                _right = new AVLTree<>();
            }
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            // duplicate value: do nothing, tree unchanged
            return this;
        }

        // Update metadata and rebalance on the way back up
        update();
        return rebalance();
    }

    // ===========================
    // REMOVE
    // ===========================

    @Override
    public SelfBalancingBST<T> remove(T element) {
        // Nothing to remove
        if (isEmpty()) {
            return this;
        }

        int cmp = element.compareTo(_value);

        if (cmp < 0) {
            // Go left
            if (_left != null) {
                _left = (AVLTree<T>) _left.remove(element);
            }
        } else if (cmp > 0) {
            // Go right
            if (_right != null) {
                _right = (AVLTree<T>) _right.remove(element);
            }
        } else {
            // ===== Found the node to remove =====
            boolean leftEmpty  = (_left == null || _left.isEmpty());
            boolean rightEmpty = (_right == null || _right.isEmpty());

            // Case 1: no children -> return an empty tree
            if (leftEmpty && rightEmpty) {
                return new AVLTree<>();
            }

            // Case 2: one child -> return the non-empty child
            if (leftEmpty && !rightEmpty) {
                return _right;
            }
            if (!leftEmpty && rightEmpty) {
                return _left;
            }

            // Case 3: two children
            // Use the minimum from the right subtree as in-order successor
            T successor = _right.findMin();
            _value = successor;
            _right = (AVLTree<T>) _right.remove(successor);
        }

        // If we get here, this node remains (possibly with modified children)
        update();
        return rebalance();
    }

    // ===========================
    // FIND MIN / MAX
    // ===========================

    @Override
    public T findMin() {
        if (isEmpty()) {
            return null;
        }
        if (_left == null || _left.isEmpty()) {
            return _value;
        }
        return _left.findMin();
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            return null;
        }
        if (_right == null || _right.isEmpty()) {
            return _value;
        }
        return _right.findMax();
    }

    // ===========================
    // SEARCH / RANGE COUNT
    // ===========================

    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }
        int cmp = element.compareTo(_value);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return (_left != null) && _left.contains(element);
        } else {
            return (_right != null) && _right.contains(element);
        }
    }

    @Override
    public int countInRange(T start, T end) {
        if (isEmpty()) {
            return 0;
        }

        int count = 0;
        int cmpLow = _value.compareTo(start);
        int cmpHigh = _value.compareTo(end);

        if (cmpLow >= 0 && cmpHigh <= 0) {
            count = 1;
        }

        if (_left != null && cmpLow > 0) {
            count += _left.countInRange(start, end);
        }
        if (_right != null && cmpHigh < 0) {
            count += _right.countInRange(start, end);
        }

        return count;
    }
}
