package assn07;

// Fall 2027 starter code

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        testA();
    }

    public static void testA() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> passwordManager = new PasswordManager<>();
        String s = "";
        while (!passwordManager.checkMasterPassword(s)) {
            System.out.println("Enter Master Password");
            s = scanner.nextLine();
        }
        String command = "";
        String w, p;
        ArrayList list;
        int n;
        Set set;

        while (!command.equals("Exit")) {
            command = scanner.nextLine();

            switch (command) {
                case "New password":
                    w = scanner.nextLine();
                    p = scanner.nextLine();
                    passwordManager.put(w, p);
                    System.out.println("New Password added");
                    break;

                case "Get password":
                    w = scanner.nextLine();
                    p = passwordManager.get(w);
                    if (p != null)
                        System.out.println(p);
                    else
                        System.out.println("Account does not exist");
                    break;

                case "Check duplicate password":
                    p = scanner.nextLine();
                    list = (ArrayList) passwordManager.checkDuplicates(p);
                    if (list.size() > 0) {
                        System.out.println("Websites using that password:");
                        for (int i = 0; i < list.size(); i++)
                            System.out.println(list.get(i));
                    } else
                        System.out.println("No account uses that password");
                    break;

                case "Generate random password":
                    n = scanner.nextInt();
                    System.out.println(passwordManager.generatesafeRandomPassword(n));
                    break;

                case "Get accounts":
                    set = passwordManager.keySet();
                    Iterator it = set.iterator();
                    System.out.println("Your accounts:");
                    while (it.hasNext())
                        System.out.println(it.next());
                    break;

                case "Delete account":
                    w = scanner.nextLine();
                    if (passwordManager.remove(w) != null)
                        System.out.println("Account deleted");
                    else
                        System.out.println("Account does not exist");
                    break;

                case "Collision stats":
                    int[] stats = passwordManager.collisionStats();
                    System.out.println("Total accounts: " + stats[0]);
                    System.out.println("Buckets used: " + stats[1]);
                    System.out.println("Buckets with collisions: " + stats[2]);
                    System.out.println("Maximum chain length: " + stats[3]);
                    break;

                case "Exit":
                    break;

                default:
                    System.out.println("Command not found");
            }
        }

    }
}
