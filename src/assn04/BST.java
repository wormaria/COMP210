package assn04;

public interface BST<T extends Comparable<T>> {

  /**
   * Inserts element into the tree in the appropriate position.
   * Either returns the mutated tree after insertion or a new tree
   * with the inserted element if necessary.
   * @param element to be added to the tree
   * @return BST<T> after insertion
   */
  BST<T> insert(T element);

  /**
   * finds minimum from right tree as successor
   * @return T the minimum element
   */
  T findMin();

  /**
   * Removes the element from the tree if it is present.
   * Either returns the possibly mutated tree after removal or an empty tree.
   * @param element to be removed from tree
   * @return BST<T> after removal
   */
  BST<T> remove(T element);

   /**
   * Remove all the elements in the range 'start' to 'finish' inclusive of these.
   * If newValue is outside of this range, it should insert it, else not.
   * @param start the start of the range
   * @param end the end of the range
   * @param newValue the value to replace elements in the specified range
   * @return BST<T> after replacing elements within the range
   */
  BST<T> replaceRange(T start, T end, T newValue);

    /**
     * helper method for replaceRange
     * Removes all the elements in the range 'start' to 'finish' inclusive of these.
     * @param start the start of the range
     * @param end the end of the range
     * @return BST<T> after removing elements within the range
     */
  BST<T> removeRange(T start, T end);

  /**
   * Prints the tree in depth first inorder traversal.
   * Print the elements all in one line with a space after each element.
   */
  void printInOrderTraversal();

  /**
   * Prints the tree in depth first preorder traversal.
   * Print the elements all in one line with a space after each element.
   */
  void printPreOrderTraversal();

  /**
   * Prints the tree in depth first postorder traversal.
   * Print the elements all in one line with a space after each element.
   */
  void printPostOrderTraversal();

  int getHeight();

  BST<T> getLeft();

  BST<T> getRight();

  T getElement();
  
  boolean isEmpty();

}
