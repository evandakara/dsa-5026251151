package lw01.unguided;

public class CarWash extends WashService {

    public CarWash(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int dayCost = 0;

        if (days <= 3) {
            dayCost = days * 35000;
        } else {
            dayCost = 3 * 35000;
            dayCost = dayCost + (days - 3) * 25000;
        }

        int perUnit = dayCost + 15000;
        return getUnits() * perUnit;
    }

    @Override
    public String label() {
        return "Car";
    }
}