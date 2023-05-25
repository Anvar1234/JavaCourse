package HouseholdAppliances.impl;

import HouseholdAppliances.parent.AppliancesWithType;
import HouseholdAppliances.parent.RotatingMechanismUsable;

public class Microwave extends AppliancesWithType implements RotatingMechanismUsable {
    String type;

    public Microwave(String brand, String purpose, int overall, int weight, double noiseLevel, String type, String type1) {
        super(brand, purpose, overall, weight, noiseLevel, type);
        this.type = type1;
    }

    public Microwave(String brand, String purpose) {
        super(brand, purpose);
    }

    public Microwave(String purpose) {
        super(purpose);
    }

    public Microwave() {
        setPurpose("Микроволновка стандарт");
    }

    @Override
    public void working() {
        System.out.println("Микроволны работают");
    }

    @Override
    public void useRotatingMechanism() {
        System.out.println("В микроволновке вращается поддон");
    }
}
