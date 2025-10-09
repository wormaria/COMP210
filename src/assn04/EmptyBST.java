package assn04;

public class EmptyBST<T extends Comparable<T>> implements BST<T> {

	@Override
	public BST<T> insert(T element) {
		return new NonEmptyBST<>(element);
	}

	@Override
	public BST<T> remove(T element) {
		return this;
	}

    @Override
    public BST<T> removeRange(T start, T end) {
        return this;
    }

    @Override
    public BST<T> replaceRange(T start, T end, T newValue){return this;}

	@Override
	public int getHeight() {
		return -1;
	}
	
	@Override
	public void printInOrderTraversal() {
	}

    @Override
    public T findMin() {
        throw new UnsupportedOperationException();
    }

    @Override
	public void printPreOrderTraversal() {

	}

	@Override
	public void printPostOrderTraversal() {
    }

	@Override
	public BST<T> getLeft() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BST<T> getRight() {
		throw new UnsupportedOperationException();
	}

	@Override
	public T getElement() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

}
