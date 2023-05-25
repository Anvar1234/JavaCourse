package HouseholdAppliances.impl;

import HouseholdAppliances.parent.AppliancesWithoutType;
import HouseholdAppliances.parent.RotatingMechanismUsable;

public class Vibrator extends AppliancesWithoutType implements RotatingMechanismUsable {


    public Vibrator(String brand, String purpose, int overall, int weight, double noiseLevel) {
        super(brand, purpose, overall, weight, noiseLevel);
    }

    public Vibrator(String brand, String purpose) {
        super(brand, purpose);
    }

    public Vibrator(String purpose) {
        super(purpose);
    }

    public Vibrator() {
        setPurpose("Вибратор стандарт");
    }

    @Override
    public void working() {
        System.out.println("Вибрач работает очень интересно");
    }

    @Override
    public void useRotatingMechanism() {
        System.out.println("В вибраче вращается вал");
    }
}
