package OOP.solid;

import java.util.*;

/**
 * Проверка пользовательского выражения (ввода) (isNumber).
 * Проверить выражение на правильность (лишние скобки и тд)
 */
public class DateValidator {

    private final String expression;
    private final FieldsClass FIELDS;
    private final MethodsClass METHODS;

    public DateValidator(String expression) {
        this.expression = expression.replaceAll(" ", "").trim();
        this.FIELDS = new FieldsClass();
        this.METHODS = new MethodsClass();
    }



    /**
     * Метод для получения результирующей коллекции (массива), состоящего из токенов
     * пользовательского выражения, которая проводится
     * в приватном методе getArrayTokens.
     */
    //ВОПРОС: правильный ли метод? Нужен ли?
    //Нужно ли его делать приватным? А если да, то как в классе DateTransformator его использовать?
    public ArrayList<String> resultArrayAfterValidation() throws Exception {
        if (isValidTokens()) {
            if (isBracketsOrderCorrect()) {
                return getArrayOfTokens();
            } else throw new Exception("Некорректно расставлены скобки!");
        } else throw new Exception("Использованы недопустимые символы!");
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
    //todo ВОПРОС: можно ли и нужно ли использовать внутри методов другие методы как ниже?
    // Или каждый метод должен быть использован отдельно где-то в другом классе, например в мейне?
    // Например где-то в мейн вызываем метод isValidTokens, и если тру, то только тогда
    // метод isBracketsOrderCorrect?
    // Похоже это и есть принцип ИНКАПСУЛЯЦИИ? То есть чтобы пользователь не мог переставить
    // порядок методов иначе, мы должны скрыть порядок проверки (методов) внутри других методов.
    private boolean isBracketsOrderCorrect() {
        Deque<Character> stack = new LinkedList<>();
            for (char c : expression.toCharArray()) {
                //если мапа содержит значение "с" (откр скобка), то пушим ее в стек.
                if (FIELDS.getBracket().containsValue(c)) {
                    stack.push(c);
                    //иначе если перед нами закрыв скобка (ключ "с"), то:
                } else if (FIELDS.getBracket().containsKey(c)) {
                    //если стек пустой или последнее значение стека != значению по ключу (откр скобка),
                    // что означает что каждой закрыв скобке должна соответствовать (быть в стеке) откр скобка:
                    if (stack.isEmpty() || stack.pop() != FIELDS.getBracket().get(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty(); //или tru?
        }


    /**
     * Самый первый метод.
     * Метод проверки наличия в пользовательском выражении только валидных токенов
     */
    private boolean isValidTokens() {
        for (String item : expression.split("")) {
            if (!FIELDS.getToken().contains(item)) return false;
        }
        return true;
    }

}
