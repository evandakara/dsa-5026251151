package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty())
                continue;
            transactions.add(line.split(" "));
        }
        sc.close();

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] t : transactions) {
            boolean exists = false;
            for (String[] c : customers) {
                if (c[0].equals(t[0])) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customers.add(new String[] { t[0], "0" });
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else {
                if (amount > balance) {
                    failed.push(t);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] t = failed.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}
