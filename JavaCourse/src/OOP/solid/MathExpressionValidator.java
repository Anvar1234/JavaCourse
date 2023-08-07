package OOP.solid;

import java.util.*;

import static OOP.solid.Fields.brackets;
import static OOP.solid.Fields.tokens;


/**
 * Класс для проверки пользовательского выражения (ввода).
 * Проверяет выражение на правильность (лишние скобки и тд).
 * Имеет один публичный метод, который возвращает окончательный результат для дальнейшего использования.
 */
public class MathExpressionValidator {

    private final String expression;

    public MathExpressionValidator(String expression) {
        expression.replaceAll(" ", "").trim();
        this.expression = expression;
    }



    /**
     * Публичный результирующий метод проверки валидности выражения.
     */
    public boolean isExpressionValid() throws RuntimeException {
        if (isBracketsOrderCorrect()) {
            return true;
        } else throw new RuntimeException("Некорректно расставлены скобки!");
    }


    /**
     * Метод проверки вложенности скобок в пользовательском выражении.
     */
    private boolean isBracketsOrderCorrect() throws RuntimeException{
        if (isValidTokens()) {
            Deque<Character> stack = new LinkedList<>();
            for (char c : expression.toCharArray()) {
                //если мапа содержит значение "с" (откр скобка), то пушим ее в стек.
                if (brackets.containsValue(c)) {
                    stack.push(c);
                    //иначе если перед нами закрыв скобка (ключ "с"), то:
                } else if (brackets.containsKey(c)) {
                    //если стек пустой или последнее значение стека != значению по ключу (откр скобка),
                    // что означает что каждой закрыв скобке должна соответствовать (быть в стеке) откр скобка:
                    if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty(); //или tru?
        } else throw new RuntimeException("Использованы недопустимые символы!");
    }


    /**
     * Метод проверки наличия в пользовательском выражении только валидных токенов.
     */
    private boolean isValidTokens() throws RuntimeException {
        if (isNotEmpty()) {
            for (String item : expression.split("")) {
                if (!tokens.contains(item)) return false;
            }
            return true;
        } else throw new RuntimeException("Выражение пустое!");
    }


    /**
     * Метод проверки выражения на пустоту.
     */
    private boolean isNotEmpty() {
        return !expression.isEmpty();
    }

}
