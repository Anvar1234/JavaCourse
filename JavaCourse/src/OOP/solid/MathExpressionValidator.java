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
    private final Utils METHODS;

    public MathExpressionValidator(String expression) {
        this.expression = expression.replaceAll(" ", "").trim();
        this.METHODS = new Utils();
    }


    /**
     * Результирующий метод для получения результирующей коллекции (массива),
     * после необходимых проверок пользовательского выражения.
     */
    public ArrayList<String> resultArrayAfterValidation() throws Exception {
        if (isNotEmpty()) {
            if (isValidTokens()) {
                if (isBracketsOrderCorrect()) {
                    return getArrayOfTokens();
                } else throw new Exception("Некорректно расставлены скобки!");
            } else throw new Exception("Использованы недопустимые символы!");
        } else throw new Exception("Выражение пустое!");
    }


    /**
     * Метод для перевода массива строк (получаемого сплитованием по пробелам в исходном выражении
     * после работы метода addSpaces) в список ArrayList.
     */
    private ArrayList<String> getArrayOfTokens() {
//        if (isBracketsOrderCorrect()) { //Нужно ли оставлять такую проверку? Вопрос частично дублирует
        //вопрос про использование методов внутри других методов.
        //В строке ниже при сплитовании мы получаем массив строк. Методом asList мы преобразуем массив в список.
        //Ранее я делал переменную типа массива String[] и присваивал ей сплитованное выражение,
        //а затем форичом копировал в список.
        return new ArrayList<>(Arrays.asList(METHODS.addSpaces(expression).split(" ")));
//        }

    }


    /**
     * Метод проверки вложенности скобок в пользовательском выражении.
     * Должен идти после самого первого метода isValidToken.
     */
    private boolean isBracketsOrderCorrect() {
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
    }


    /**
     * Метод проверки наличия в пользовательском выражении только валидных токенов.
     * Самый первый метод.
     */
    private boolean isValidTokens() {
        for (String item : expression.split("")) {
            if (!tokens.contains(item)) return false;
        }
        return true;
    }


    /**
     * Метод проверки выражения на пустоту.
     */
    private boolean isNotEmpty() {
        return !expression.isEmpty();
    }

}
