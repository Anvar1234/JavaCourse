package OOP.solid;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */
public class View {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //(1+2)*3 //-(-1-(1+2))
        String expressionNew = prompt();
        DateValidator dateValidator = new DateValidator(expressionNew);
        System.out.println(dateValidator.getExpression());
        System.out.println(dateValidator.isExpressionValid());

        //Проверка Трансформатора (Преобразователя):
        DateTransformator dateTransformator = new DateTransformator(dateValidator);
        if(dateValidator.isUnaryMinus()){
            System.out.println(dateTransformator.unaryMinusSymbolChanger());
            System.out.println(dateTransformator.specialSymbolChanger(dateTransformator.unaryMinusSymbolChanger()));

//            System.out.println();
        }
        else{
            System.out.println("В выражении нет унарного минуса.");
        }
    }

    //    private static void start(){
//        while (true){
//            prompt();
//        }
//    }
    private static String prompt() {
        System.out.print("Введите выражение: ");
        return sc.nextLine();
    }
}
