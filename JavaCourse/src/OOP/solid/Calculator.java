package OOP.solid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import static OOP.solid.Utils.isNumber;

/**
 * Высчитывает ОПН.
 */
public class Calculator {

    private final PolandNotationConverter polandNotationConverter;
    private ArrayList<String> validExpressionAfterConvertation;

    //todo ВОПРОС: можно ли использовать в разных методах одинаковые названия возвращаемых одним
    // и входящих в другой аргументов?
    // Кажется что так вроде понятнее читается. Как здесь resultPostfixArray.

    public Calculator(String expression){
        this.polandNotationConverter = new PolandNotationConverter(expression);
        try {
            this.validExpressionAfterConvertation = polandNotationConverter.convertToPostfix();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }


    /**
     * Публичный результирующий метод для получения результата расчета пользовательского выражения.
     */
    public Deque<Double> resultDequeAfterCalculation() {
        return calculatePostfixNotation();
    }


    /**
     * Приватный метод для получения результата расчета пользовательского выражения.
     */
    private Deque<Double> calculatePostfixNotation() { //Обработка постфиксного выражения

        Deque<Double> resultStack = new ArrayDeque<>();
        double result;

        for (String element : validExpressionAfterConvertation) {
            //Условие "Если элемент массива число, то перевод в дабл и пушим в стек"
            if (isNumber(element)) resultStack.push(Double.parseDouble(element));
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
