package lw01.unguided;

public class MotorcycleWash extends WashService {

    public MotorcycleWash(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int satuUnit = getDays() * 15000 + 5000;
        return getUnits() * satuUnit;
    }

    @Override
    public String label() {
        return "Motorcycle";
    }
}