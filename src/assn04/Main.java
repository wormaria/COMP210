package assn04;

public class Main {
    static void main(String[] args) {
        // Start empty and build a classic BST
        BST<Integer> bst = new EmptyBST<>();
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) bst = bst.insert(v);

        System.out.println("== Initial tree ==");
        System.out.print("In-order:   ");
        bst.printInOrderTraversal();
        System.out.println();
        System.out.print("Pre-order:  ");
        bst.printPreOrderTraversal();
        System.out.println();
        System.out.print("Post-order: ");
        bst.printPostOrderTraversal();
        System.out.println();
        System.out.println("findMin: " + bst.findMin());
        System.out.println("height:  " + bst.getHeight());  // height counted in EDGES (empty=-1, leaf=0)

        // Remove a LEAF (20)
        bst = bst.remove(20);
        System.out.println("\n== After remove(20)  // leaf = 20 ==");
        System.out.print("In-order:   ");
        bst.printInOrderTraversal();
        System.out.println();

        // Remove ONE-CHILD node (30 now has only right child 40)
        bst = bst.remove(30);
        System.out.println("\n== After remove(30)  // one child (40) ==");
        System.out.print("In-order:   ");
        bst.printInOrderTraversal();
        System.out.println();

        // Remove TWO-CHILDREN node (70 has children 60 and 80)
        bst = bst.remove(70); // should replace 70 with successor (min of right subtree, i.e., 80), then delete 80
        System.out.println("\n== After remove(70)  // two children ==");
        System.out.print("In-order:   ");
        bst.printInOrderTraversal();
        System.out.println();

        // Replace range [45, 75] with 55  (removes 50 and 60, inserts 55)
        bst = bst.replaceRange(45, 75, 55);
        System.out.println("\n== After replaceRange[45,75] -> 55 ==");
        System.out.print("In-order:   ");
        bst.printInOrderTraversal();
        System.out.println();

        // Remove range [55, 80]  (removes 55 and 80; should leave only 40)
        bst = bst.removeRange(55, 80);
        System.out.println("\n== After removeRange[55,80] ==");
        System.out.print("In-order:   ");
        bst.printInOrderTraversal();
        System.out.println();
        System.out.println("\nDone.");
    }
}
