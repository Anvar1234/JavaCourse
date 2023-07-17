package OOP.solid;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.Collections.addAll;

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
    public ArrayList<String> unaryMinusSymbolChanger() {
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


    //todo Написать метод, для перевода # в (0-1)*
    //Возвращаем ArrayList, т.к. по итогу нам лучше работать с ArrayList, чем с LinkedList.
    //А используем ЛинкедЛист, чтобы быстрее вставлять/удалять из середины (если придется).
//    public ArrayList<String> specialSymbolChanger(ArrayList<String> arrayListTokens) {
//        ArrayList<String> changedArrayListTokens = new ArrayList<>();
//        changedArrayListTokens.addAll(arrayListTokens);
////        int j = i;
//        for (int i = 0; i < arrayListTokens.size(); i++) {
//            if (!arrayListTokens.get(i).equals("#")) {
//                changedArrayListTokens.add(arrayListTokens.get(i));
//            }
//            else {
//                arrayListTokens.remove(i);
//                arrayListTokens.addAll(i, FIELDS.getAddToArrayToken());
//                i += FIELDS.getAddToArrayToken().size() - 1;
//            }
//        }
//        return changedArrayListTokens;
//    }


    public ArrayList<String> specialSymbolChanger(ArrayList<String> arrayListTokens) {
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



//    /**
//     * Метод для копирования одного ArrayList в другой с определенной позиции.
//     */
//    public ArrayList<String> copyArrayListFromPosition(ArrayList<String> arrayListTokens,
//                                                       LinkedList<String> changedArrayListTokens,
//                                                       int position) {
//        if (position < 0 || position >= arrayListTokens.size()) {
//            throw new IndexOutOfBoundsException("Invalid position: " + position);
//        }
//        changedArrayListTokens.addAll(arrayListTokens.subList(position, arrayListTokens.size()));
//    }
//
//}
