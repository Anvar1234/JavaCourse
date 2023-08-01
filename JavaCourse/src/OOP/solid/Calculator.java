package OOP.solid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Высчитывает ОПН.
 */
public class Calculator {

    //todo ВОПРОС: можно ли использовать в разных методах одинаковые названия возвращаемых одним
    // и входящих в другой аргументов?
    // Кажется что так вроде понятнее читается. Как здесь resultPostfixArray.
    public Deque<Double> calculatePostfixNotation(ArrayList<String> resultPostfixArray) { //Обработка постфиксного выражения
        Deque<Double> resultStack = new ArrayDeque<>();
        double result;

        for (String element : resultPostfixArray) {
            //Условие "Если элемент массива число, то перевод в дабл и пушим в стек"
            Utils METHODS = new Utils();
            if (METHODS.isNumber(element)) resultStack.push(Double.parseDouble(element));
            else {

                switch (element) {
                    case "*" -> {
                        result = resultStack.pop() * resultStack.pop();
                        resultStack.push(result);
                    }
                    case "/" -> {
                        double division = resultStack.pop();
                        result = resultStack.pop() / division;
                        resultStack.push(result);
                    }
                    case "-" -> {
                        result = -resultStack.pop() + resultStack.pop();
                        resultStack.push(result);
                    }
                    case "+" -> {
                        result = resultStack.pop() + resultStack.pop();
                        resultStack.push(result);
                    }
                    case "^" -> {
                        double exponentiation = resultStack.pop();
                        result = exponentiation * exponentiation;
                        resultStack.push(result);
                    }
                    default -> System.out.println("Алармус");
                }
            }
        }
        return resultStack;
    }


}
