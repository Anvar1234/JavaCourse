package OOP.solid;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

//todo ВОПРОС: Главный вопрос, нужно ли методы класса использовать внутри класса? А в других классах (вспомогательных)?

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */

/**
 * ВОПРОСЫ общие:
 * Нужно ли добавлять проверки методами из класса, методов этого же класса?
 */


public class View {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //(1+2)*3 //-(-1-(1+2)) - ош, (-1-(1+2)) - ош, меньше - норм
        System.out.println("-1-(1+2) = [0, 1, -, 1, *, 1, 2, +, -]");
        System.out.println("-(-1-(1+2)) = [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("3+(1-(1+2)) = [3, 1, 1, 2, +, -, +]");
        System.out.println("-3+(1-(1+2)) = [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) = [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2%+5 = [3, 1, 2, %, *, +, 5, +]\n");


        String expressionNew = prompt();

        DateValidator dateValidator = new DateValidator(expressionNew);

        DateTransformator dateTransformator = new DateTransformator(dateValidator);
        //todo Из-за этой строчки выходила ошибка! Проверить!
        // System.out.println("dateTransformator= " + dateTransformator.resultArrayAfterTransformation());
        // Похоже из-за того, что на экземпляре dateTransformator один раз метод resultArrayAfterTransformation вызвался,
        // то следующий вызов уже не имеет унарного минуса, поэтому сообщение и выходило.
        // По итогу не ясно, правильно ли я сделал, что для получения результирующего выражения и
        // в Валидаторе и в Трансформаторе сделал проверки. Хотя мы можем результат положить в переменную же.
        ArrayList<String> resultArrayOfTransformation = dateTransformator.resultArrayAfterTransformation();

        PolandNotationConverter polandNotationConverter = new PolandNotationConverter(resultArrayOfTransformation);
        ArrayList<String> resultArrayOfConvertation = polandNotationConverter.convertToPostfix();
        System.out.println(resultArrayOfConvertation);

        Deque<Double> finalResultArray = polandNotationConverter.handlingPostfixNotation(resultArrayOfConvertation);
        System.out.println(finalResultArray);

//        //Прошлый вариант:
//        //Проверка Трансформатора (Преобразователя):
//        DateTransformator dateTransformator = new DateTransformator(dateValidator);
//        if(dateValidator.isThereUnaryMinus()){
//            //выводим польз выражение с символом #, которым заменили унарный минус.
//            //System.out.println(dateTransformator.unaryMinusSymbolChanger());
//            //выводим польз выражение с замененным символом #.
//            System.out.println(dateTransformator.specialSymbolChanger());
//            PolandNotationConverter polandNotationConverter =
//                    new PolandNotationConverter(dateTransformator.specialSymbolChanger());
//            System.out.println(polandNotationConverter.convertToPostfix());
//            System.out.println(polandNotationConverter.handlingPostfixNotation(polandNotationConverter.convertToPostfix()));
//        }
//        else{
//            System.out.println("В выражении нет унарного минуса.");
//            PolandNotationConverter polandNotationConverter =
//                    new PolandNotationConverter(dateValidator.resultArrayAfterValidation());
//            System.out.println(polandNotationConverter.convertToPostfix());
//            System.out.println(polandNotationConverter.handlingPostfixNotation(polandNotationConverter.convertToPostfix()));
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
