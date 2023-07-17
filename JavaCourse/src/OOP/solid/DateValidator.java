package OOP.solid;

import java.util.*;

/**
 * Проверка пользовательского выражения (ввода) (isNumber).
 * Проверить выражение на правильность (лишние скобки и тд)
 */
public class DateValidator {
    private ArrayList<String> resultArrayAfterValidation;
    private final String expression;
    private final FieldsClass FIELDS;
    private final MethodsClass METHODS;

    public DateValidator(String expression) {
        this.expression = expression.replaceAll(" ", "").trim();
        this.FIELDS = new FieldsClass();
        this.METHODS = new MethodsClass();
    }

    //Геттер для получения пользовательского выражения. Нужен локально, в целом - нет.
    public String getExpression() {
        return expression;
    }


    /**
     * Публичный метод, который будет использоваться в другом классе после всех проверок.
     */
    public boolean isExpressionValid() { // был boolean, потом нужно будет изменить
        return isBracketsOrderCorrect();
    }


    /**
     * Метод для получения результирующей коллекции (массива), состоящего из токенов
     * пользовательского выражения, после результирующей проверки, которая проводится
     * в приватном методе getArrayTokens.
     */
    //ВОПРОС: правильный ли метод? Нужен ли?
    //Нужно ли его делать приватным? А если да, то как в классе DateTransformator его использовать?
    public ArrayList<String> resultArrayAfterValidation() {
        return getArrayTokens();
    }


    /**
     * ДОПОЛНИТЕЛЬНЫЙ метод для нахождения в пользовательском выражении унарного минуса.
     * Данный метод должен следовать после проверки наличия только валидных токенов и
     * правильной вложенности скобок, а также после перевода строки с пробелами в массив ArrayList.
     * После этого метода мы уже можем переводить в постфиксную нотацию.
     */
    public boolean isUnaryMinus() {
        //ВОПРОС: проверка по нижним методам пройдена, так как получилось создать ArrayList<String> через
        // метод getArrayTokens(), верно? Проверку в getArrayTokens добавил как раз для этого.
        // ВОПРОС: Метод не используется внутри класса! Допустимо? И он публичный, норм?
        ArrayList<String> arrayListTokens = getArrayTokens();
        for (int i = 1; i < arrayListTokens.size(); i++) {
            if ((i == 1 && arrayListTokens.get(0).equals("-")) ||
                    arrayListTokens.get(i).equals("-") &&
                            FIELDS.getBracket().containsValue(arrayListTokens.get(i - 1).charAt(0))) {
                //todo ВОПРОС: в brackets ошибка, не заходит в мапу походу. А птму что там у меня символы,
                // а здесь я использую стринг. Возможно лучше переделать все методы,
                // где используются char, либо здесь все правильно написал и делать лучше локально?
                return true;
            }
        }
        return false;
    }


    /**
     * Метод для перевода строки с пробелами в массив ArrayList.
     * Должен идти после метода addSpaces. В нашем случае метод addSpaces используется внутри тела метода.
     */
    private ArrayList<String> getArrayTokens() {
        ArrayList<String> arrayTokens = new ArrayList<>();
        if (isBracketsOrderCorrect()) {
            String[] tempArrayString = METHODS.addSpaces(expression).split(" "); //временный массив строк после сплита по пробелу
            for (String item : tempArrayString) {
                arrayTokens.add(item);
            }
        }
        return arrayTokens;
    }


    /**
     * Метод проверки вложенности скобок в пользовательском выражении.
     * Должен идти после самого первого метода isValidToken.
     */
    private boolean isBracketsOrderCorrect() {
        Deque<Character> stack = new LinkedList<>();
        if (isValidTokens()) {
            for (char c : expression.toCharArray()) {
                if (FIELDS.getBracket().containsValue(c)) {
                    stack.push(c);
                } else if (FIELDS.getBracket().containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != FIELDS.getBracket().get(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
        return false; //здесь была ошибка из-за того, что в мапе скобок были квадратные скобки, а в списке токенов - нет.
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
