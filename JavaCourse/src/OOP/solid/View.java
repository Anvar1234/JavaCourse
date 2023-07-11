package OOP.solid;

import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Класс отвечающий за взаимодействие с пользователем.
 */
public class View {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        start();
    }

    private static void start(){
        while (true){
            prompt();
        }
    }
    private static String prompt(){
        System.out.print("Введите выражение: ");
        return sc.nextLine();
    }
}
