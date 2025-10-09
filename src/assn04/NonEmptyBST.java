package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
    private T _element;
    private BST<T> _left;
    private BST<T> _right;

    public NonEmptyBST(T element) {
        _left = new EmptyBST<>();
        _right = new EmptyBST<>();
        _element = element;
    }

    @Override
    public BST<T> insert(T element) {
        int comp = element.compareTo(_element);
        if (comp < 0) {
            _left = _left.insert(element);
        } else if (comp > 0) {
            _right = _right.insert(element);
        }
        return this;
    }

    @Override
    public void printInOrderTraversal() {
        _left.printInOrderTraversal();
        System.out.print(_element + " ");
        _right.printInOrderTraversal();
    }


    @Override
    public void printPreOrderTraversal() {
        System.out.print(_element + " ");
        _left.printPreOrderTraversal();
        _right.printPreOrderTraversal();
    }


    @Override
    public void printPostOrderTraversal() {
        _left.printPostOrderTraversal();
        _right.printPostOrderTraversal();
        System.out.print(_element + " ");
    }


    @Override
    public BST<T> remove(T element) {
        int cmp = element.compareTo(_element);

        if (cmp < 0) {
            // target is in the left subtree
            _left = _left.remove(element);
            return this;
        } else if (cmp > 0) {
            // target is in the right subtree
            _right = _right.remove(element);
            return this;
        }

        // cmp == 0 → remove this node

        // Case 1: leaf
        if (_left.isEmpty() && _right.isEmpty()) {
            return new EmptyBST<>();
        }

        // Case 2: one child
        if (_left.isEmpty()) {          // only right child
            return _right;
        }
        if (_right.isEmpty()) {         // only left child
            return _left;
        }

        // Case 3: two children
        // Replace this node's value with the in-order successor
        T succ = _right.findMin();       // smallest in right subtree
        _element = succ;
        _right = _right.remove(succ);    // remove the successor node from right subtree
        return this;
    }


    @Override
    public T findMin() {
        if (_left.isEmpty()) {
            return _element;
        } else
            return _left.findMin();
    }

    @Override
    public BST<T> replaceRange(T start, T end, T newValue) {
        BST<T> treeAfterRemoval = this.removeRange(start, end);
        treeAfterRemoval = treeAfterRemoval.insert(newValue);
        return treeAfterRemoval;
    }

    @Override
    public BST<T> removeRange(T start, T end) {
        // Recurse into left and right first
        _left = _left.removeRange(start, end);
        _right = _right.removeRange(start, end);

        // Now check the current node
        if (_element.compareTo(start) >= 0 && _element.compareTo(end) <= 0) {
            // Current element is inside the range → remove it
            return this.remove(_element);
        }

        return this;
    }


    //====================================================================
	// Do not change the methods below
	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
