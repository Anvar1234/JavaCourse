package HouseholdAppliances;

import HouseholdAppliances.impl.Scale;
import HouseholdAppliances.impl.Vibrator;
import HouseholdAppliances.impl.Washingmachine;
import HouseholdAppliances.parent.AppliancesStore;
import HouseholdAppliances.parent.AppliancesWithType;
import HouseholdAppliances.parent.AppliancesWithoutType;

public class Main {
    public static void main(String[] args) {

        AppliancesStore store = new AppliancesStore();

        store.addAppliancesWithoutType(new Scale("SAMSUNG", "Весы"))
                .addAppliancesWithoutType(new Scale("Весы"))
                .addAppliancesWithoutType(new Scale())
                .addAppliancesWithoutType(new Vibrator("NoNa", "Вибратор"))
                .addAppliancesWithoutType(new Vibrator("Вибратор"))
                .addAppliancesWithoutType(new Vibrator())
                .addAppliancesWithoutType(new Washingmachine("Supra", "Стиралка"))
                .addAppliancesWithoutType(new Washingmachine("Стиралка"))
                .addAppliancesWithoutType(new Washingmachine());
        Washingmachine wm1 = new Washingmachine("LG", "Стиральная машина", 1000, 30, 30.5, "Инвертор");
        store.addAppliancesWithoutType(wm1);
        System.out.println("Общий список: ");
        System.out.println(store.getAppliancesWithoutTypes());

        System.out.println("\nОбъекты без типа:");
        for(AppliancesWithoutType app : store.getAppliancesWithoutTypes()){
            if(app instanceof AppliancesWithoutType && !(app instanceof AppliancesWithType)){
                System.out.println(app.getBrand() + "-" + app.getPurpose());
            }
        }


        System.out.println("\nОбъекты с типом:");
        for(AppliancesWithoutType app : store.getAppliancesWithoutTypes()){
            if(app instanceof AppliancesWithType){
                System.out.println(app.getBrand() + "-" + app.getPurpose());
            }
        }System.out.println();

        //получаем все объекты с вращающимся механизмом
        System.out.println("\nВращение есть: " + store.getRotatingMechanismUsable());

        System.out.println("\nМетоды для стиралок: ");
        System.out.println("Тип машинки: " + wm1.getType());
        System.out.println(wm1.isBulky());
        if(!wm1.isBulky()) System.out.printf("Габарит не превышен: %s\n", wm1.getOverall());
        else System.out.printf("Габарит превышен: %s!\n", wm1.getOverall());
        System.out.println("\nМеняем размер:");
        wm1.setOverall(1050);
        System.out.println(wm1.isBulky());
        if(!wm1.isBulky()) System.out.printf("Габарит не превышен: %s\n", wm1.getOverall());
        else System.out.printf("Габарит превышен: %s!\n", wm1.getOverall());

        wm1.setNoiseLevel(500.5);
        if(wm1.getNoiseCategory() == 0) System.out.println("\nШума 0, категория А!\n");
        else if(wm1.getNoiseCategory() == 1) System.out.println("\nШума не много, категория В!\n");
        else if(wm1.getNoiseCategory() == 2) System.out.println("\nШума значительно, категория С!\n");
        else if(wm1.getNoiseCategory() == 3) System.out.println("\nШума много, категория D!\n");
        else System.out.println("\nЗапредельная громкость, категория Ж!\n");

        wm1.working();
        wm1.usePowder();



    }
}
