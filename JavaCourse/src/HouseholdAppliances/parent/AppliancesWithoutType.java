package HouseholdAppliances.parent;

/**
 * УДАЛИТЬ потом Implements Rotating! И из весов тоже!
 */
public abstract class AppliancesWithoutType {//быт техника
    private String brand = "Noname";//бренд
    private String purpose;//назначение
    private int overall;//габарит (условно)
//    private int width;//ширина
//    private int depth;//глубина
    private int weight; //вес
//    private int numberOfTypesOfCommunication;//кол-во типов коммуникации (э/э, вода, газ)
    private double noiseLevel;


    public AppliancesWithoutType(String brand, String purpose, int overall, int weight, double noiseLevel) {
        this.brand = brand;
        this.purpose = purpose;
        this.overall = overall;
//        this.width = width;
//        this.depth = depth;
        this.weight = weight;
//        this.numberOfTypesOfCommunication = numberOfTypesOfCommunication;
        this.noiseLevel = noiseLevel;
    }

    public AppliancesWithoutType(String brand, String purpose) {
        this.brand = brand;
        this.purpose = purpose;
    }
    public AppliancesWithoutType(String purpose) {
        this.purpose = purpose;
    }

    public AppliancesWithoutType() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(double noiseLevel) {
        this.noiseLevel = noiseLevel;
    }


    public abstract void working();


    public boolean isBulky() {  //крупная бытовая или мелкая бытовая техника?
        return this.weight > 30 || this.overall > 1000;
    }

    public int getNoiseCategory() { //категория уровня шума
        if (this.noiseLevel == 0) return 0;
        else if (this.noiseLevel > 0 && this.noiseLevel <= 60.0) return 1;
        else if (this.noiseLevel > 60.0 && this.noiseLevel <= 80.0) return 2;
        else if (this.noiseLevel > 80.0 && this.noiseLevel <= 105.5) return 3;
        else return 4;
    }


    /**
     * Скорее всего нужно переопределять эти методы в конкретных классах, а не в абстрактном,
     * ну и удалить отсюда имплементацию интерфейса. С другой стороны эти методы нужны для всех наследников, чтобы не переопределять вручную
     * то скорее всего нужно оставить эти методы здесь.
     * Скорее всего отсюда нужно убрать и удалить интерфейс тоже. Так как у меня в любом случае
     * этот метод должен быть у каждого наследника.
     */

    @Override
    public String toString() {
        return getBrand() + " " + getPurpose();
    }

//    @Override
//    public String toString() {
//        return getBrand() + " " + getPurpose() + " " + getWeight();
//    }
}






