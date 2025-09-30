package assn03;

// Starter Code provided with Assignment #3 for COMP210
// The given main method has some examples of how to create and modify the linked lists
// It contains suggestions on how to test your code after completing the TODO Tasks

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(30);
        System.out.println("list = " + list.toString());
        System.out.println("size of list = " + list.size());
        System.out.println("list contains 10?: " + list.contains(10));     // implemented
        System.out.println("list contains 50?: " + list.contains(50));
        System.out.println("set element at index 2 to be 10");
        list.set(2, 10);
        System.out.println("get element at index 2 = " + list.get(2));
        System.out.println("list = " + list.toString());
        System.out.println("Last Index of element 10 in list = " + list.lastIndexOf(10));

        list.remove(20);
        System.out.println("list after removing 20 = " + list.toString());

        System.out.println("index of '30' = " + list.indexOf(30));
        LinkedList a = new LinkedList();
        a.add(1); a.add(2); a.add(3);

        LinkedList b = new LinkedList();
        b.add(4); b.add(5); b.add(6);

        // Test task 1
        // TBD (To Be Done) Write some code here to test task 1
        LinkedList x1 = new LinkedList();
        x1.add(1); x1.add(2); x1.add(3);
        LinkedList x2 = new LinkedList();
        x2.add(4); x2.add(5); x2.add(6);
        x1.simpleMerge(x2);
        System.out.println("Task 1: list after simpleMerge with list1 = " + x1.toString());


        // Test task 2
        LinkedList r = new LinkedList();
        r.add(1); r.add(4); r.add(2);
        r.removeAtIndex(1);// TBD
        System.out.println("Task 2: list after removing element at index 1 = " + r.toString());

        // Test task 3
        LinkedList e1 = new LinkedList();
        e1.add(1); e1.add(4); e1.add(2);
        LinkedList e2 = new LinkedList();
        e2.add(1); e2.add(4); e2.add(2);
        System.out.println("isEqual -> " + e1.isEqual(e2)); // true

        // Task 4 removeRepeats (sorted)
        LinkedList d = new LinkedList();
        d.add(1); d.add(2); d.add(2); d.add(2); d.add(3); d.add(3); d.add(4);
        d.removeRepeats();
        System.out.println("removeRepeats -> " + d); // 1->2->3->4

// Task 5 reverse
        LinkedList rev = new LinkedList();
        rev.add(1); rev.add(2); rev.add(3);
        rev.reverse();
        System.out.println("reverse -> " + rev); // 3->2->1

// Task 6 merge (interleave starting with list2)
        LinkedList m1 = new LinkedList();
        m1.add(1); m1.add(2); m1.add(3);
        LinkedList m2 = new LinkedList();
        m2.add(4); m2.add(5); m2.add(6);
        m1.merge(m2);
        System.out.println("merge equal -> " + m1); // 4->1->5->2->6->3

        LinkedList m3 = new LinkedList();
        m3.add(1); m3.add(2); m3.add(3); m3.add(4);
        LinkedList m4 = new LinkedList();
        m4.add(5); m4.add(6);
        m3.merge(m4);
        System.out.println("merge shorter -> " + m3); // 5->1->6->2->3->4
    }
}
