package OOP.solid;

public class MethodSet {
    public MethodSet() {
    }


    /**
     * Вспомогательный метод, который проверяет, является ли ПЕРВЫЙ символ входящей строки числом (а у нас в массиве
     * хранятся строки). И нам не за чем проверять всю строку (а строка может состоять из цифр типа 10.2), а
     * достаточно проверить лишь первый символ, то есть str.charAt(0), и тогда ясно - что перед нами число.
     */
    public boolean isNumber(String str) {
        //return Character.isDigit(Integer.parseInt(String.valueOf(str.charAt(0))));
        try {
            Integer.parseInt(String.valueOf(str.charAt(0)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
