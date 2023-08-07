package OOP.solid;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;


/**
 * Класс отвечающий за взаимодействие с пользователем.
 */

/**
 * ВОПРОСЫ общие:

 * Можно/нужно ли использовать одинаковые названия полей разных классов. А если смысл у них одинаковый?
 * <p>
 * Calculator Можно ли использовать в разных методах одинаковые названия возвращаемых одним и входящих
 * в другой аргументов? Кажется что так вроде понятнее читается. Как здесь resultPostfixArray.
 * <p>
 * Как правильно понять regexы? И составлять.
 * <p>
 * Что делать с этим методом?))
 */


public class ConsoleView {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //примеры выражений и ОПН выражения:
        System.out.println("-3*1*2^+5/2 = [0, 1, -, 3, *, 1, *, 2, ^, *, 5, 2, /, +]");
        System.out.println("-(-1-(1+2)) = [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("-3+(1-(1+2)) = [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) = [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2^+5 = [3, 1, 2, ^, *, +, 5, +]\n");

        //присваиваем пользовательский ввод переменной inputExpression.
        String inputExpression = prompt();

        //
        UnaryMinusPreparator unaryMinusPreparator = new UnaryMinusPreparator(inputExpression);
        ArrayList<String> tempArray = unaryMinusPreparator.resultArrayAfterTransformation(); // specialSymbolChanger();
        System.out.println("После трансформатора:\n" + tempArray);

        //Загоняем выражение, прошедшее проверку и преобразование в ОПН-конвертер.
        PolandNotationConverter polandNotationConverter = new PolandNotationConverter(inputExpression);
        ArrayList<String> resultArrayOfConvertation = polandNotationConverter.convertToPostfix();
        System.out.println("ОПН:\n" + resultArrayOfConvertation);

        //Загоняем ОПН-выражение в калькулятор (котор высчитывает ОПН).
        Calculator calculator = new Calculator(inputExpression);
        Deque<Double> finalResultArray = calculator.resultDequeAfterCalculation();
        //Выводим результат работы калькулятора.
        System.out.println("Результат:\n" + finalResultArray);


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
