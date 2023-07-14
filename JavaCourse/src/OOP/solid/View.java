package OOP.solid;

import java.util.Scanner;

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */
public class View {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //(1+2)*3
        DateValidator dv = new DateValidator(prompt());
        System.out.println(dv.getExpression());
        System.out.println(dv.isExpressionValid()); // проверка метода isExpressionValid
        System.out.println(dv.isBracketsOrderCorrectViewer()); //строка для вывода стека

        //проверка метода addSpaces добавления пробелов:
        System.out.println(dv.addSpaces());

        //проверка метода getArrayTokens перевода токенов выражения в массив: (1+2.0) /3.0+1+2- (2+3)
        System.out.println(dv.getArrayTokens(dv.addSpaces()));
        System.out.println(dv.getArrayTokens(dv.addSpaces()).size());

        //проверка метода isUnaryMinus для нахождения и замены унарного минуса:
        System.out.println(dv.isUnaryMinus(dv.getArrayTokens(dv.addSpaces()))); // -(-(1+2)) // -(-1-(1+2))
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
