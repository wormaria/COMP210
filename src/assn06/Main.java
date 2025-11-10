package assn06;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post-insertion root that is
        // returned may be different from the original root because of the insertion.
        // resulting height should be about 6.

        for (int i=0; i<50; i++) {
            avl_bst = avl_bst.insert((int) (Math.random()*100));
        }
        System.out.println(avl_bst.height());
        System.out.println(avl_bst.size());

        SelfBalancingBST<Integer> avl_bst2 = new AVLTree<Integer>();
        // Now insert 50 integers in increasing order which would
        // cause a simple BST to become very tall but for our
        // self-balancing tree won't be too bad (should be 5)

        for (int i=0; i<50; i++) {
            avl_bst2 = avl_bst2.insert(i);
        }
        System.out.println(avl_bst2.height());
        System.out.println(avl_bst2.size());

        SelfBalancingBST<Integer> avl_bst3 = new AVLTree<Integer>();
        avl_bst3 = avl_bst3.insert(10);
        avl_bst3 = avl_bst3.insert(12);
        avl_bst3 = avl_bst3.insert(4);
        avl_bst3 = avl_bst3.insert(3);
        avl_bst3 = avl_bst3.insert(5);
        avl_bst3 = avl_bst3.insert(8);
        avl_bst3 = avl_bst3.insert(7);
        System.out.println(avl_bst3.height());
        System.out.println(avl_bst3.size());
        System.out.println(avl_bst3.countInRange(3,8));     // should print 5
        System.out.println(avl_bst3.countInRange(3,5));     // should print 3
        System.out.println(avl_bst3.countInRange(6,8));     // should print 2
        avl_bst3 = avl_bst3.insert(6);
        System.out.println(avl_bst3.countInRange(3,8));     // should print 6
    }
}
