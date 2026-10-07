package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Event Check-In Results =====");
        Set<String> registered = new LinkedHashSet<String>();
        Set<String> checkedIn = new LinkedHashSet<String>();
        int rejected = 0;

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (sc1.hasNextLine()) {
            String id = sc1.nextLine();
            registered.add(id);
        }
        sc1.close();

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (sc2.hasNextLine()) {
            String id = sc2.nextLine();

            if (!registered.contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedIn.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                checkedIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        sc2.close();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}