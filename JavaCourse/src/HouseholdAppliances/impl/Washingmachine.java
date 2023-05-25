package HouseholdAppliances.impl;

import HouseholdAppliances.parent.AppliancesWithType;
import HouseholdAppliances.parent.PowderUsable;
import HouseholdAppliances.parent.RotatingMechanismUsable;

public class Washingmachine extends AppliancesWithType implements RotatingMechanismUsable, PowderUsable {


    public Washingmachine(String brand, String purpose, int overall, int weight, double noiseLevel, String type) {
        super(brand, purpose, overall, weight, noiseLevel, type);
    }

    public Washingmachine(String brand, String purpose) {
        super(brand, purpose);
    }

    public Washingmachine(String purpose) {
        super(purpose);
    }

    public Washingmachine() {
        setPurpose("Стиралка стандарт");
    }

    @Override
    public void working() {
        System.out.println("Стиральная машинка работает: вжжж-вжжж-вжжж");
    }

    @Override
    public void usePowder() {
        System.out.println("Стиралка: Использует порошок");
    }

    @Override
    public void useRotatingMechanism() {
        System.out.println("Стиралка крутит белье.");

    }
}

