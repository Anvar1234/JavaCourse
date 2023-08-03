package OOP.solid;

import java.util.ArrayList;

import static OOP.solid.Fields.*;

/**
 * Класс для преобразования пользовательского выражения, как то: проверка на унарный минус,
 * замена унарного минуса спецсимволом, замена спецсимвола на набор символов, после
 * прохождения необходимых проверок.
 * Имеет один публичный метод, который возвращает окончательный результат для дальнейшего использования.
 */
public class UnaryMinusPreparator {
    private ArrayList<String> validExpression;
    private final MathExpressionValidator mathExpressionValidator;


    public UnaryMinusPreparator(String expression) {
        this.mathExpressionValidator = new MathExpressionValidator(expression);
        try {
            this.validExpression = mathExpressionValidator.resultArrayAfterValidation();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Публичный результирующий метод для получения результирующей коллекции (массива),
     * после необходимых трансформаций пользовательского выражения.
     */
    public ArrayList<String> resultArrayAfterTransformation() {
        return unaryMinusChanger();
    }


//    /**
//     * Метод для замены спецсимвола "#", которым ранее методом unaryMinusSymbolChanger
//     * в пользовательском выражении заменили унарный минус, на коллекцию символов "(0-1)*".
//     */
//    public ArrayList<String> specialSymbolChanger() {
//
//        ArrayList<String> arrayListTokens = unaryMinusSymbolChanger();
//        ArrayList<String> changedArrayListTokens = new ArrayList<>();
//        for (String item : arrayListTokens) {
//            if (!item.equals("#")) {
//                changedArrayListTokens.add(item);
//            } else {
//                changedArrayListTokens.addAll(additionalCollectionOfTokens);
//            }
//        }
//        return changedArrayListTokens;
//    }
//
//
//    /**
//     * Метод для замены в пользовательском выражении унарного минуса на спецсимвол "#".
//     */
//    ArrayList<String> unaryMinusSymbolChanger() {
//
//        ArrayList<String> arrayListTokens = this.validExpression;
//        for (int i = 1; i < arrayListTokens.size(); i++) {
//            if (i == 1 && arrayListTokens.get(0).equals("-")) {
//                arrayListTokens.set(0, "#");
//                //i++;
//            } else if (arrayListTokens.get(i).equals("-") &&
//                    brackets.containsValue(arrayListTokens.get(i - 1).charAt(0))) {
//                arrayListTokens.set(i, "#");
//                //i++;
//            }
//
//        }
//        return arrayListTokens;
//    }

    /**
     * Метод замены унарного минуса в выражении.
     */
    private ArrayList<String> unaryMinusChanger() {
        if (isThereUnaryMinus()) {
            ArrayList<String> arrayListTokens = this.validExpression;
            ArrayList<String> tempArray = new ArrayList<>();
            for (int i = 0; i < arrayListTokens.size(); i++) {
                //если элемент не минус, то добавляем его в вывод. коллекцию.
                if (!arrayListTokens.get(i).equals("-")) {
                    tempArray.add(arrayListTokens.get(i));
                    //иначе если элемент является первым в коллекции (i==0),
                    // то в вывод коллекцию добавляем строки 0 и -.
                } else if (i == 0) {
                    tempArray.add("0");
                    tempArray.add("-");
                    //иначе, если элемент "-" не первый, проверяем есть ли перед ним откр скобка, если
                    // да, то в вывод коллекцию добавляем строки 0 и -.
                } else if (brackets.containsValue(arrayListTokens.get(i - 1).charAt(0))) {
                    tempArray.add("0");
                    tempArray.add("-");
                    //если минус - это не первый элемент и перед ним нет откр скобки,
                    // то добавляем в выводную коллекцию.
                } else tempArray.add("-");
            }
            return tempArray;
        }
        return this.validExpression;
    }


    /**
     * Метод для нахождения в пользовательском выражении унарного минуса.
     * Данный метод должен следовать после проверки наличия только валидных токенов и
     * правильной вложенности скобок, а также после перевода строки с пробелами в массив ArrayList.
     * После этого метода мы уже можем переводить в постфиксную нотацию.
     */
    private boolean isThereUnaryMinus() {
        for (int i = 1; i < validExpression.size(); i++) {
            // Начинаю с i==1, птму что ниже в условии get(i - 1) может быть такое, что нулевой элемент это минус,
            // и тогда метод get(i - 1) вызовет ошибку, так как
            // в выражении нет предыдущих эементов.
            // если нулевой элемент == минусу или если текущее значение (i>1) в выражении == минусу, но при этом
            // предыдущий элемент == открывающей скобке, то:
            if ((i == 1 && validExpression.get(0).equals("-")) ||
                    validExpression.get(i).equals("-") &&
                            brackets.containsValue(validExpression.get(i - 1).charAt(0))) {
                return true;
            }
        }
        return false;
    }
}


