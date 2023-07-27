package OOP.solid;

import java.util.ArrayList;
import java.util.Objects;


public class DateTransformator {
    private final ArrayList<String> validExpression;
    private final FieldsClass FIELDS;
    private final MethodsClass METHODS;

//todo Может можно использовать поля ФИЕЛДЫ и МЕТОДС из Валидатора? Иначе получается дублирование кода вроде как?
    public DateTransformator(DateValidator dateValidator) throws Exception {
        this.validExpression = dateValidator.resultArrayAfterValidation();
        this.FIELDS = new FieldsClass();
        this.METHODS = new MethodsClass();
    }

    public ArrayList<String> resultArrayAfterTransformation() {
        if (isThereUnaryMinus()) {
            return specialSymbolChanger();//метод unaryMinusSymbolChanger() вызывать не нужно, потому что он внутри метода specialSymbolChanger() вызывается.
        } else {
            System.out.println("Унарного минуса не обнаружено. Выражение готово к использованию.");
        }
        return validExpression;
    }



    /**
     * Метод для замены спецсимвола "#", которым ранее методом unaryMinusSymbolChanger
     * в пользовательском выражении заменили унарный минус, на коллекцию символов "(0-1)*".
     */
    private ArrayList<String> specialSymbolChanger() {

            ArrayList<String> arrayListTokens = unaryMinusSymbolChanger();
            ArrayList<String> changedArrayListTokens = new ArrayList<>();
            for (String item : arrayListTokens) {
                if (!item.equals("#")) {
                    changedArrayListTokens.add(item);
                } else {
                    changedArrayListTokens.addAll(FIELDS.getAdditionalCollectionOfTokens());
                }
            }
            return changedArrayListTokens;
        }



    /**
     * Метод для замены в пользовательском выражении унарного минуса на спецсимвол "#".
     */
    //ВОПРОС: Наверное лучше добавить сюда проверку унарного минуса, и возвращать сразу с замененным символом?
    //todo ВОПРОС: А точнее, может при создании (в конструкторе) экземпляра сразу заменять?
    private ArrayList<String> unaryMinusSymbolChanger() {

            ArrayList<String> arrayListTokens = this.validExpression;
            for (int i = 1; i < arrayListTokens.size(); i++) {
                if (i == 1 && arrayListTokens.get(0).equals("-")) {
                    arrayListTokens.set(0, "#");
                    //i++;
                } else if (arrayListTokens.get(i).equals("-") &&
                        FIELDS.getBracket().containsValue(arrayListTokens.get(i - 1).charAt(0))) {
                    arrayListTokens.set(i, "#");
                    //i++;
                }

            }
            return arrayListTokens;
        }


    /**
     * Метод для нахождения в пользовательском выражении унарного минуса.
     * Данный метод должен следовать после проверки наличия только валидных токенов и
     * правильной вложенности скобок, а также после перевода строки с пробелами в массив ArrayList.
     * После этого метода мы уже можем переводить в постфиксную нотацию.
     */
    private boolean isThereUnaryMinus() {
        for (int i = 1; i < Objects.requireNonNull(validExpression).size(); i++) {
            if ((i == 1 && validExpression.get(0).equals("-")) ||
                    validExpression.get(i).equals("-") &&
                            FIELDS.getBracket().containsValue(validExpression.get(i - 1).charAt(0))) {
                //todo ВОПРОС: в brackets ошибка, не заходит в мапу походу. А птму что там у меня символы,
                // а здесь я использую стринг. Возможно лучше переделать все методы,
                // где используются char, либо здесь все правильно написал и делать лучше локально?
                return true;
            }
        }
        return false;
    }
}


