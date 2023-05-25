package HouseholdAppliances.parent;

public abstract class AppliancesWithType extends AppliancesWithoutType {
    private String type;

    public AppliancesWithType(String brand, String purpose, int overall, int weight, double noiseLevel, String type) {
        super(brand, purpose, overall, weight, noiseLevel);
        this.type = type;
    }

    public AppliancesWithType(String brand, String purpose) {
        super(brand, purpose);
    }

    public AppliancesWithType(String purpose) {
        super(purpose);
    }

    public AppliancesWithType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
