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
        System.out.println(dv.isExpressionValid());
        System.out.println(dv.isBracketsOrderCorrectViewer());
        //где-то либо создать класс, либо метод для добавления пробелов (есть в буфере).
        //также нужно сделать проверку число или оператор или скобка, скорее всего просто через проверку приоритета.

//        Deque<Integer> stack2 = new ArrayDeque<>();
//        stack2.add(1);
//        stack2.add(2);
//        stack2.addLast(0);
//        stack2.addFirst(100);
//        System.out.println(stack2);
    }

//    private static void start(){
//        while (true){
//            prompt();
//        }
//    }
    private static String prompt(){
        System.out.print("Введите выражение: ");
        return sc.nextLine();
    }
}
