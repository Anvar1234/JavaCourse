package OOP.solid;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */

/**
 * ВОПРОСЫ общие:
 * Нужно ли добавлять проверки методами из класса методов этого же класса?
 *
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
            //выводим польз выражение с символом #, которым заменили унарный минус.
            //System.out.println(dateTransformator.unaryMinusSymbolChanger());
            //выводим польз выражение с замененным символом #.
            System.out.println(dateTransformator.specialSymbolChanger());
            PolandNotationConverter polandNotationConverter =
                    new PolandNotationConverter(dateTransformator.specialSymbolChanger());
            System.out.println(polandNotationConverter.convertToPostfix());
        }
        else{
            System.out.println("В выражении нет унарного минуса.");
            PolandNotationConverter polandNotationConverter =
                    new PolandNotationConverter(dateValidator.resultArrayAfterValidation());
            System.out.println(polandNotationConverter.convertToPostfix());
        }



//        if(dateValidator.isExpressionValid()){
//            //если тру, то проверяем на наличие унарного минуса:
//            //todo ВОПРОС: если фолс, то исключение?
//            if (dateValidator.isUnaryMinus()){
//                //если тру, то меняем унарный минус на спецсимвол и меняем спецсимвол на набор символов "(0-1)*".
//                //если фолс, то начинаем перевод в польскую нотацию.
//                dateTransformator.specialSymbolChanger();
//                //начинаем перевод в польскую нотацию.
//
//            }
//            else()
//
//        }



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
