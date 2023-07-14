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
        DateValidator dv = new DateValidator(expressionNew);
        System.out.println(dv.getExpression());
        System.out.println(dv.isExpressionValid());

        //Проверка Трансформатора (Преобразователя):
        DateTransformator dt = new DateTransformator(expressionNew);
        if(dv.isUnaryMinus()){
            System.out.println(dt.unaryMinusSymbolChanger());
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
