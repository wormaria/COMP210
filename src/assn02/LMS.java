package assn02;

import java.util.Scanner;


public class LMS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book[] library = new Book[5]; // capacity 5
        int count = 0;                // how many books stored


        boolean running = true;
        while (running) {
            // Print menu
            System.out.println("Library Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Add a Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Check Out a Book");
            System.out.println("4. Return a Book");
            System.out.println("Enter your choice:");


            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // clear newline
            } else {
                sc.nextLine(); // discard invalid input
                System.out.println("Invalid choice. Try again.");
                continue;
            }


            switch (choice) {
                case 0:
                    System.out.println("Bye for now!");
                    running = false;
                    break;


                case 1:
                    if (count == 5) {
                        System.out.println("Book not added. Library is full!");
                    } else {
                        System.out.println("Enter book title:");
                        String title = sc.nextLine();
                        System.out.println("Enter book author:");
                        String author = sc.nextLine();
                        library[count] = new Book(title, author);
                        count++;
                        System.out.println("Book added to library!");
                    }
                    break;


                case 2:
                    System.out.println("Books in Library:");
                    for (int i = 0; i < count; i++) {
                        System.out.println(library[i].displayLine(i + 1));
                    }
                    break;


                case 3:
                    System.out.println("Enter book number to check out:");
                    if (sc.hasNextInt()) {
                        int num = sc.nextInt();
                        sc.nextLine();
                        if (num < 1 || num > count) {
                            System.out.println("Invalid book number!");
                        } else {
                            library[num - 1].checkOut();
                            System.out.println("Book checked out!");
                        }
                    } else {
                        sc.nextLine();
                        System.out.println("Invalid book number!");
                    }
                    break;


                case 4:
                    System.out.println("Enter book number to return:");
                    if (sc.hasNextInt()) {
                        int num = sc.nextInt();
                        sc.nextLine();
                        if (num < 1 || num > count) {
                            System.out.println("Invalid book number!");
                        } else {
                            library[num - 1].returnBook();
                            System.out.println("Book returned!");
                        }
                    } else {
                        sc.nextLine();
                        System.out.println("Invalid book number!");
                    }
                    break;


                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }


        sc.close();
    }
}

