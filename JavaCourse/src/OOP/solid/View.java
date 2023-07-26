package OOP.solid;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */

/**
 * ВОПРОСЫ общие:
 * Нужно ли добавлять проверки методами из класса, методов этого же класса?
 *
 */


public class View {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //(1+2)*3 //-(-1-(1+2)) - ош, (-1-(1+2)) - ош, меньше - норм
        System.out.println("-1-(1+2) = [0, 1, -, 1, *, 1, 2, +, -]");
        System.out.println("-(-1-(1+2)) = [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("3+(1-(1+2)) = [3, 1, 1, 2, +, -, +]");
        System.out.println("-3+(1-(1+2)) = [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) = [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2%+5 = [3, 1, 2, %, *, +, 5, +]"); // вот это самое глдавное выражение


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
            System.out.println(polandNotationConverter.handlingPostfixNotation(polandNotationConverter.convertToPostfix()));
        }
        else{
            System.out.println("В выражении нет унарного минуса.");
            PolandNotationConverter polandNotationConverter =
                    new PolandNotationConverter(dateValidator.resultArrayAfterValidation());
            System.out.println(polandNotationConverter.convertToPostfix());
            System.out.println(polandNotationConverter.handlingPostfixNotation(polandNotationConverter.convertToPostfix()));
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
