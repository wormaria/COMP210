package assn05;

import java.util.Arrays;

public class Main {

    /**
     * Write a series of tests to check the functionality of each task
     * @param args
     */
    public static void main(String[] args) {
        // testT1();
        // testT2();
        //testT3();
       testT4();
    }

    /**
     * Test Task 1 - Write some tests to convince yourself that your code for Task 1 is working
     */
    public static void testT1(){
        SimpleEmergencyRoom er = new SimpleEmergencyRoom();
        er.addPatient("Alice", 2);
        er.addPatient("Bob",   5);
        er.addPatient("Cara",  3);
        System.out.println(er.dequeue().getValue()); // Bob
        System.out.println(er.dequeue().getValue()); // Cara
        System.out.println(er.dequeue().getValue()); // Alice
        System.out.println(er.dequeue());            // null
    }

    /**
     * Test Task 2 - Write some tests to convince yourself that your code for Task 2 (A, B) is working
     */
    public static void testT2(){
        System.out.println("===== Task 2A: enqueue, getMax, dequeue =====");
        MaxBinHeapER<String, Integer> heap = new MaxBinHeapER<>();

        heap.enqueue("Alice", 10);
        heap.enqueue("Bob",   55);
        heap.enqueue("Cara",  30);
        heap.enqueue("Drew",  80);
        heap.enqueue("Ella",  49);
        heap.enqueue("Finn",  50);

        System.out.println("Initial heap (internal order): " + Arrays.toString(heap.getAsArray()));
        System.out.println("Heap size: " + heap.size());
        System.out.println("Highest priority (getMax): " + heap.getMax());

        System.out.println("\nDequeuing all patients by priority:");
        while (heap.size() > 0) {
            System.out.println(" -> Dequeued: " + heap.dequeue());
        }
        System.out.println("Dequeue on empty heap returns: " + heap.dequeue());
        System.out.println("GetMax on empty heap returns: " + heap.getMax());


        System.out.println("\n===== Task 2B: removeBelow(threshold) =====");
        MaxBinHeapER<String, Integer> heap2 = new MaxBinHeapER<>();

        heap2.enqueue("A", 10);
        heap2.enqueue("B", 55);
        heap2.enqueue("C", 30);
        heap2.enqueue("D", 80);
        heap2.enqueue("E", 49);
        heap2.enqueue("F", 50);

        System.out.println("Before removeBelow(50): " + Arrays.toString(heap2.getAsArray()));
        heap2.removeBelow(50);

        System.out.println("After removeBelow(50): " + Arrays.toString(heap2.getAsArray()));
        System.out.println("Heap size after removal: " + heap2.size());

        // Confirm that no priorities < 50 remain
        boolean belowFound = false;
        for (Prioritized<String,Integer> p : heap2.getAsArray()) {
            if (p.getPriority() < 50) belowFound = true;
        }
        System.out.println("Contains below-threshold priority? " + belowFound);
        System.out.println("Current highest priority (getMax): " + heap2.getMax());

        System.out.println("\nDequeuing remaining patients after removeBelow:");
        while (heap2.size() > 0) {
            System.out.println(" -> Dequeued: " + heap2.dequeue());
        }

        System.out.println("\nAll tests complete!");
    }

    /**
     * Test Task 3 - This part can be used to test for task 3.
     */
    public static void testT3(){
        System.out.println("\n===== Task 3: Build-Heap constructor (deterministic test) =====");

// Make a Prioritized[] with explicit priorities (no randomness)
        Prioritized<String, Integer>[] incoming = new Prioritized[] {
                new Patient<>("A", 42),
                new Patient<>("B", 5),
                new Patient<>("C", 77),
                new Patient<>("D", 13),
                new Patient<>("E", 77),  // tie at max
                new Patient<>("F", 1),
                new Patient<>("G", 50)
        };

// Build heap in O(n) via constructor
        MaxBinHeapER<String, Integer> h3 = new MaxBinHeapER<>(incoming);

// Print internal heap and max
        System.out.println("Heap after build: " + java.util.Arrays.toString(h3.getAsArray()));
        System.out.println("Expected max is one of {C,E}: " + h3.getMax());

// Dequeue everything to confirm descending priority
        System.out.println("Dequeuing (should be priorities 77,77,50,42,13,5,1):");
        while (h3.size() > 0) {
            String v = h3.dequeue();
            System.out.println(" -> " + v);
        }
        System.out.println("After draining, getMax(): " + h3.getMax()); // null

    }

    /**
     * Test Task 4 - Write some tests to convince yourself that your code for Task 4 is working
     * You can use some of the helper methods already given below.
     * An example is given below:
     */
    public static void testT4() {
        double[] res = compareRuntimes();
        System.out.println("SimpleER: total time for 100,000 dequeues: " + res[0] + " nanosec");
        System.out.println("SimpleER: time per dequeue: " + res[1] + " nanosec");
        System.out.println("Heap: total time for 100,000 dequeues: " + res[2] + " nanosec");
        System.out.println("Heap: time per dequeue: " + res[3] + " nanosec");
    }

    /**
     * fills up an Emergency Room based on a MaxBinHeapER
     * @param complexER an initially empty MaxBinHeapER
     */
    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    /**
     * fills up an Emergency Room based on a SimpleEmergencyRoom (overloaded)
     * @param simpleER an initially empty SimpleEmergencyRoom
     */
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    /**
     * Creates an array of patients
     * @return returns this array of patients
     */
    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    /**
     * TODO (Task 4): compareRuntimes
     * Compares the Run Times of the SimpleEmergencyRoom vs MaxBinHeapER
     * @return an array of results as follows:
     * index 0: total nanosec for simpleER dequeues
     * index 1: average nanosec for simpleER dequeues
     * index 2: total nanosec for maxHeapER dequeues
     * index 3: average nanosec for maxHeapER dequeues
     */
    public static double[] compareRuntimes() {
        // results[0]=simple total ns, [1]=simple avg ns, [2]=heap total ns, [3]=heap avg ns
        final int N = 100_000;
        double[] results = new double[4];

        // Build both with 100k random-priority patients (via one-arg add/enqueue)
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // --- Task 4.1: time SimpleEmergencyRoom dequeues ---
        long t0 = System.nanoTime();
        for (int i = 0; i < N; i++) {
            simplePQ.dequeue();
        }
        long t1 = System.nanoTime();
        long simpleTotal = t1 - t0;
        results[0] = simpleTotal;
        results[1] = simpleTotal / (double) N;

        // --- Task 4.2: time MaxBinHeapER dequeues ---
        long t2 = System.nanoTime();
        for (int i = 0; i < N; i++) {
            binHeap.dequeue();
        }
        long t3 = System.nanoTime();
        long heapTotal = t3 - t2;
        results[2] = heapTotal;
        results[3] = heapTotal / (double) N;

        return results;
    }


}
