package OOP.solid;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Проверка пользовательского выражения (ввода) (isNumber).
 * Проверить выражение на правильность (лишние скобки и тд)
 */
public class DateValidator {
    private String expression;
    private List<String> token;

    public DateValidator(String expression) {
        this.expression = expression.replaceAll(" ", "").trim();
        this.token = List.of("+", "-", "/", "*", "(", ")", ".", "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9"); // дополнить
    }

    //публичный метод, который будет использоваться в другом классе после всех проверок.
    public boolean isExpressionValid() {
        return isElementOrderCorrect();

    }

    //переделать метод ниже, если нужно.
    private static boolean isNumber(String str) {
        //return Character.isDigit(Integer.parseInt(String.valueOf(str.charAt(0))));
        try {
            Integer.parseInt(String.valueOf(str.charAt(0)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод проверки порядка токенов в пользовательском выражении.
     */
    private boolean isElementOrderCorrect() {
        Deque<Character> stack = new ArrayDeque<>();
        if (isValidTokens()) {
            //ниже логика проверки порядка токенов в пользовательском выражении.

            for (char c : expression.toCharArray()) {
                if (c == '(' || c == '+' || c == '-' || c == '*' || c == '/') {
                    stack.addFirst(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peekFirst() != '(') {
                        return false;
                    }
                    stack.removeFirst(); // удаляем '(' из стека
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * Метод проверки наличия только валидных токенов
     */
    private boolean isValidTokens() {
        for (String item : expression.split("")) {
            if (!token.contains(item)) return false;
        }
        return true;
    }

}
