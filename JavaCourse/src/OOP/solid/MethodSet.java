package OOP.solid;

public class MethodSet {

    public MethodSet() {
    }

    /**
     * Вспомогательный метод, который проверяет, является ли ПЕРВЫЙ символ входящей строки числом (а у нас в массиве
     * хранятся строки). И нам не за чем проверять всю строку (а строка может состоять из цифр типа 10.2), а
     * достаточно проверить лишь первый символ, то есть str.charAt(0), и тогда ясно - что перед нами число.
     */
    public boolean isNumber(String exp) {
        //return Character.isDigit(Integer.parseInt(String.valueOf(str.charAt(0))));
        try {
            Integer.parseInt(String.valueOf(exp.charAt(0)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Вспомогательный метод для добавления в строке String пробелов между операторами и операндами.
     * Идет где-то после проверки наличия только валидных токенов и правильной вложенности скобок.
     * Даже скорее всего после проверки выражения на валидный порядок всех токенов, операторов и операндов.
     */
    public String addSpaces(String exp) {
        // заменяем все вхождения скобок и операторов на сами символы с добавлением пробелов
        String result = exp.replaceAll("([\\(\\)\\+\\-\\*\\/])", " $1 ");
        // удаляем лишние пробелы
        result = result.replaceAll("\\s+", " ").trim();
        return result;
    }

}
