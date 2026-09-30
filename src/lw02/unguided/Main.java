package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> processedOrders = new LinkedList<>();

        Queue<String[]> arrivedOrders = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        foods.add(new String[] { "Bakso", "2" });
        foods.add(new String[] { "Sate", "1" });
        foods.add(new String[] { "Soto", "2" });

        drinks.add(new String[] { "EsTeh", "4" });
        drinks.add(new String[] { "EsJeruk", "2" });

        while (sc.hasNext()) {
            String[] order = new String[4];
            order[0] = sc.next();
            order[1] = sc.next();
            order[2] = sc.next();
            order[3] = sc.next();
            orders.add(order);
        }

        sc.close();

        arrivedOrders.addAll(orders);

        while (!arrivedOrders.isEmpty()) {
            String[] o = arrivedOrders.poll();

            String name = o[0];
            String side_dish = o[1];
            String drink = o[2];
            String table = o[3];

            // find customer
            String[] foodItem = null;
            boolean foodAvail = true;
            if (!side_dish.equals("-")) {
                for (String[] f : foods) {
                    if (f[0].equals(side_dish)) {
                        foodItem = f;
                        break;
                    }
                }
                foodAvail = (foodItem != null && Integer.parseInt(foodItem[1]) > 0);
            }

            String[] drinkItem = null;
            boolean drinkAvail = true;
            if (!drink.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drink)) {
                        drinkItem = d;
                        break;
                    }
                }
                drinkAvail = (drinkItem != null && Integer.parseInt(drinkItem[1]) > 0);
            }

            if (foodAvail && drinkAvail) {
                if (foodItem != null) {
                    int stock = Integer.parseInt(foodItem[1]) - 1;
                    foodItem[1] = String.valueOf(stock);
                }
                if (drinkItem != null) {
                    int stock = Integer.parseInt(drinkItem[1]) - 1;
                    drinkItem[1] = String.valueOf(stock);
                }
                processedOrders.add(o);
            } else {
                failedOrders.push(o);
            }
        }

        // print
        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : processedOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("\n=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("\n=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println("\n=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}