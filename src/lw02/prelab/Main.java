package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (sc.hasNext()) {
            String[] transaction = new String[3];
            transaction[0] = sc.next();
            transaction[1] = sc.next();
            transaction[2] = sc.next();
            transactions.add(transaction);
        }

        sc.close();

        queue.addAll(transactions);

        while (!queue.isEmpty()) {
            String[] t = queue.poll();

            String name = t[0];
            String type = t[1];

            int amount = Integer.parseInt(t[2]);

            // find customer
            String[] customer = null;

            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                customer = new String[] { name, "0" };
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {

                balance += amount;
                customer[1] = String.valueOf(balance);

            } else if (type.equals("WITHDRAW")) {
                if (amount <= balance) {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                } else {
                    failed.push(t);
                }
            }
        }

        System.out.println("\n=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println("\n=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] t = failed.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}
