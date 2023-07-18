package OOP.solid;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.Collections.addAll;
//todo ВОПРОС: Главный вопрос, нужно ли методы класса использовать внутри класса? А в других классах (вспомогательных)?


public class DateTransformator {
    private final ArrayList<String> validExpression;
    private final FieldsClass FIELDS;
    private final MethodsClass METHODS;


    public DateTransformator(DateValidator dateValidator) {
        this.validExpression = dateValidator.resultArrayAfterValidation();
        this.FIELDS = new FieldsClass();
        this.METHODS = new MethodsClass();
    }

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
     * Метод для замены спецсимвола "#", который знаменует собой место унарного минуса, на коллекцию символов "(0-1)*",
     * которые далее будут в финальном выражении и это выражение необходимо будет переводить в польскую нотацию.
     */

    public ArrayList<String> specialSymbolChanger() {
        ArrayList<String> arrayListTokens = unaryMinusSymbolChanger();
        ArrayList<String> changedArrayListTokens = new ArrayList<>();
        for (String item : arrayListTokens) {
            if (!item.equals("#")) {
                changedArrayListTokens.add(item);
            } else {
                changedArrayListTokens.addAll(FIELDS.getAddToArrayToken());
            }
        }
        return changedArrayListTokens;
    }
}


