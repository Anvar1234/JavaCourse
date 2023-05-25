package HouseholdAppliances.parent;

import java.util.ArrayList;
import java.util.List;

public class AppliancesStore {
    private final List<AppliancesWithoutType> appliancesWithoutTypes;

    public AppliancesStore() {this.appliancesWithoutTypes = new ArrayList<>();}

    public AppliancesStore addAppliancesWithoutType(AppliancesWithoutType appliancesWithoutType){
        appliancesWithoutTypes.add(appliancesWithoutType);
        return this;
    }
    public List<AppliancesWithoutType> getAppliancesWithoutTypes() {
        return appliancesWithoutTypes;
    }


    /**
     * Методы для получения: использующих порошок, фреон, вращающийся механизм и тд.
     */
    public List<RotatingMechanismUsable> getRotatingMechanismUsable(){
        List<RotatingMechanismUsable> rotatingMechanismUsables = new ArrayList<>();
        for (AppliancesWithoutType appliancesWithoutType : getAppliancesWithoutTypes()){
            if (appliancesWithoutType instanceof RotatingMechanismUsable){
                rotatingMechanismUsables.add((RotatingMechanismUsable) appliancesWithoutType);
            }
        }
        return rotatingMechanismUsables;
    }




}
