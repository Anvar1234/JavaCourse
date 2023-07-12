package OOP.solid;

import java.util.*;

/**
 * Проверка пользовательского выражения (ввода) (isNumber).
 * Проверить выражение на правильность (лишние скобки и тд)
 */
public class DateValidator {
    private String expression;
    private List<String> token;
    private Map<Character, Character> brackets; //1.Во множ числе можно писать? 2.Здесь инициализировать или как я - ниже?

    public DateValidator(String expression) {
        this.expression = expression.replaceAll(" ", "").trim();
        this.token = List.of("+", "-", "/", "*", "(", ")", "[","]", ".", "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");
        this.brackets = new HashMap<>();
        this.brackets.put(')', '(');
        this.brackets.put(']', '[');
    }

    public String getExpression() {
        return expression;
    }

    //публичный метод, который будет использоваться в другом классе после всех проверок.
    public boolean isExpressionValid() {
        return isBracketsOrderCorrect();

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
    private boolean isBracketsOrderCorrect() {
        Deque<Character> stack = new LinkedList<>();
        if (isValidTokens()) {

            //ниже логика проверки вложенности скобок в пользовательском выражении.
            for (char c : expression.toCharArray()) {
                if (brackets.containsValue(c)) {
                    stack.push(c);
                } else if (brackets.containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
        return false; //здесь была ошибка из-за того, что в мапе скобок были квадратные скобки, а в списке токенов - нет.
    }

    /**
     * Ниже повтор метода, просто чтобы визуализировать стек.
     */
    public Deque<Character> isBracketsOrderCorrectViewer() {
        Deque<Character> stack = new LinkedList<>();
        if (isValidTokens()) {
            //ниже логика проверки вложенности скобок в пользовательском выражении.

            for (char c : expression.toCharArray()) {
                if (brackets.containsValue(c)) {
                    stack.push(c);
                } else if (brackets.containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                        return stack;
                    }
                }
            }
            return stack;
        }
        return stack;
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
