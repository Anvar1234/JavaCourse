package HouseholdAppliances.impl;

import HouseholdAppliances.parent.AppliancesWithoutType;

public class Scale extends AppliancesWithoutType {


    //private String purpose = "Весы стандарт";
    public Scale(String brand, String purpose, int overall, int weight, double noiseLevel) {//Scale - весы
        super(brand, purpose, overall, weight, noiseLevel);
    }

    public Scale(String brand, String purpose) {
        super(brand, purpose);
    }

    public Scale(String purpose) {
        super(purpose);
    }

    public Scale() {
        setPurpose("Весы стандарт");
    }

    @Override
    public void working() {
        System.out.println("Весы такие \"пик-пик\"");
    }

}
