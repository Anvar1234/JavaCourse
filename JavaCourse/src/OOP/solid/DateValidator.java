package OOP.solid;

import java.util.*;

/**
 * Проверка пользовательского выражения (ввода) (isNumber).
 * Проверить выражение на правильность (лишние скобки и тд)
 */
public class DateValidator {
    private String expression;
    private FieldSet fields;
    private MethodSet methods;

    public DateValidator(String expression) {
        this.expression = expression.replaceAll(" ", "").trim();
        this.fields = new FieldSet();
        this.methods = new MethodSet();
    }

    //Геттер для получения пользовательского выражения. Нужен локально, в целом - нет.
    public String getExpression() {
        return expression;
    }




    //публичный метод, который будет использоваться в другом классе после всех проверок.
    public boolean isExpressionValid() {
        return isBracketsOrderCorrect();

    }

//    /**
//     * Вспомогательный метод, который проверяет, является ли ПЕРВЫЙ символ входящей строки числом (а у нас в массиве
//     * хранятся строки). И нам не за чем проверять всю строку (а строка может состоять из цифр типа 10.2), а
//     * достаточно проверить лишь первый символ, то есть str.charAt(0), и тогда ясно - что перед нами число.
//     */
//    public boolean isNumber(String str) {
//        //return Character.isDigit(Integer.parseInt(String.valueOf(str.charAt(0))));
//        try {
//            Integer.parseInt(String.valueOf(str.charAt(0)));
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }


    /**
     * Метод для нахождения и замены унарного минуса.
     * Данный метод должен следовать после проверки наличия только валидных токенов и
     * правильной вложенности скобок.
     * После этого метода мы уже можем переводить в постфиксную нотацию.
     */
    public ArrayList<String> isUnaryMinus(ArrayList<String> arrayListTokens) {

        //здесь добавить if выполняются необх предыдущие методы, то.
        for (int i = 1; i < arrayListTokens.size(); i++) {
            if (i == 1 && arrayListTokens.get(0).equals("-") &&
                    (fields.getBracket().containsValue(arrayListTokens.get(1).charAt(0)) ||
                            methods.isNumber(arrayListTokens.get(1)))) {
                //todo ВОПРОС: в brackets ошибка, не заходит в мапу походу. А птму что там у меня символы,
                // а здесь я использую стринг. Возможно лучше переделать все методы,
                // где используются char, либо здесь все правильно написал и делать лучше локально?
                arrayListTokens.set(0, "#");
                i++;
                System.out.println(i);
            } else if (arrayListTokens.get(i - 1).equals("-") &&
                    (fields.getBracket().containsValue(arrayListTokens.get(i - 2).charAt(0)) ||
                            fields.getBracket().containsValue(arrayListTokens.get(i).charAt(0)))) {
                arrayListTokens.set(i - 1, "#");
                i++;
                System.out.println(i);
            }

        }
        return arrayListTokens;
    }


    /**
     * Метод для добавления пробелов между операторами и операндами.
     * Идет где-то после проверки наличия только валидных токенов и правильной вложенности скобок.
     * Даже скорее всего после проверки выражения на валидный порядок всех токенов, операторов и операндов.
     */
    public String addSpaces() {
        // заменяем все вхождения скобок и операторов на сами символы с добавлением пробелов
        String result = this.expression.replaceAll("([\\(\\)\\+\\-\\*\\/])", " $1 ");
        // удаляем лишние пробелы
        result = result.replaceAll("\\s+", " ").trim();
        return result;
    }

    /**
     * Метод для перевода строки с пробелами в массив ArrayList, должен идти после
     * метода addSpaces.
     */
    public ArrayList<String> getArrayTokens(String exp) { //String, входящий после метода addSpaces
        String[] tempArrayString = exp.split(" "); //временный массив строк после сплита по пробелу
        ArrayList<String> arrayStrings = new ArrayList<>();
        for (String item : tempArrayString) {
            arrayStrings.add(item);
        }
        return arrayStrings;
    }


    /**
     * Метод проверки вложенности скобок в пользовательском выражении.
     */
    private boolean isBracketsOrderCorrect() {
        Deque<Character> stack = new LinkedList<>();
        if (isValidTokens()) {

            //ниже логика проверки вложенности скобок в пользовательском выражении.
            for (char c : expression.toCharArray()) {
                if (fields.getBracket().containsValue(c)) {
                    stack.push(c);
                } else if (fields.getBracket().containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != fields.getBracket().get(c)) {
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
                if (fields.getBracket().containsValue(c)) {
                    stack.push(c);
                } else if (fields.getBracket().containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != fields.getBracket().get(c)) {
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
            if (!fields.getToken().contains(item)) return false;
        }
        return true;
    }

}
