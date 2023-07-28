package OOP.solid;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;


/**
 * Класс отвечающий за взаимодействие с пользователем.
 */

/**
 * ВОПРОСЫ общие:
 * Нужно ли добавлять проверки методами из класса, методов этого же класса?
 * Нужно/можно ли методы класса использовать внутри класса? А в других классах (вспомогательных)?
 * Можно/нужно ли использовать одинаковые названия полей разных классов. А если смыл у них одинаковый?
 * Можно ли использовать в разных методах одинаковые названия возвращаемых одним и входящих в другой аргументов?
 * кажется что так вроде понятнее читается. Как здесь resultPostfixArray.
 * Я правильно помню, что лучше использовать if-else вместо switch-case?
 * Как правильно понять regexы? И составлять.
 * Нужно ли оставлять такую проверку? Вопрос частично дублирует вопрос про использование методов внутри
 * других методов.
 * Что делать с этим методом?))
 */


public class View {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //примеры выражений и ОПН выражения:
        System.out.println("-3*1*2%+5/2 = [0, 1, -, 3, *, 1, *, 2, %, *, 5, 2, /, +]");
        System.out.println("-(-1-(1+2)) = [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("-3+(1-(1+2)) = [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) = [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2%+5 = [3, 1, 2, %, *, +, 5, +]\n");

        //присваиваем пользовательский ввод переменной inputExpression.
        String inputExpression = prompt();

        //запускаем проверку входящего выражения inputExpression.
        DateValidator dateValidator = new DateValidator(inputExpression);

        //запускаем преобразование входящего выражения inputExpression.
        DateTransformator dateTransformator = new DateTransformator(dateValidator);
        //todo Из-за этой строчки выходила ошибка! Проверить!
        // System.out.println("dateTransformator= " + dateTransformator.resultArrayAfterTransformation());
        // Похоже из-за того, что на экземпляре dateTransformator один раз метод resultArrayAfterTransformation вызвался,
        // то следующий вызов уже не имеет унарного минуса, поэтому сообщение и выходило.
        // По итогу не ясно, правильно ли я сделал, что для получения результирующего выражения и
        // в Валидаторе и в Трансформаторе сделал проверки. Хотя мы можем результат положить в переменную же.

        //Получаем окончательное выражение для преобразования в ОПН.
        ArrayList<String> resultArrayOfTransformation = dateTransformator.resultArrayAfterTransformation();

        //Загоняем выражение, прошедшее проверку и преобразование в ОПН-конвертер.
        PolandNotationConverter polandNotationConverter = new PolandNotationConverter(resultArrayOfTransformation);
        ArrayList<String> resultArrayOfConvertation = polandNotationConverter.convertToPostfix();
        System.out.println(resultArrayOfConvertation);

        //Загоняем ОПН-выражение в калькулятор (котор высчитывает ОПН).
        Calculator calculator = new Calculator();
        Deque<Double> finalResultArray = calculator.calculatePostfixNotation(resultArrayOfConvertation);
        //Выводим результат работы калькулятора.
        System.out.println(finalResultArray);


    }

    //todo ВОПРОС: что делать с этим методом?))

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
