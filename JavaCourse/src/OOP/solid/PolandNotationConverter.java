package OOP.solid;

import java.util.*;

public class PolandNotationConverter {
    private final FieldsClass FIELDS;
    private final MethodsClass METHODS;


    private ArrayList<String> expression; //Идея предлагает убрать поле и упрооститьт конструктор

    //todo Подумать, что добавлять в конструктор? Так-то у нас всегда приходит АррауЛист, хоть после Валидатора,
    // хоть после Трансформатора.
    public PolandNotationConverter(ArrayList<String> expression) {
        this.expression = expression;
        this.FIELDS = new FieldsClass();
        this.METHODS = new MethodsClass();
    }

    /**
     * Метод для перевода пользовательского выражения в обратную польскую нотацию.
     */
    public ArrayList<String> convertToPostfix() {
        Deque<String> operators = new ArrayDeque<>(); //стек операторов
        ArrayList<String> resultPostfixArray = new ArrayList<>(); //коллекция вывода

        for (String item : this.expression) {
            //Если элемент массива число, то в вывод:
            if (METHODS.isNumber(item)) {
                resultPostfixArray.add(item);
                //Если элемент массива закр скобка ")", то:
            } else if (item.equals(")")) {
                //пока на вершине не появится откр скобка "(":
                //todo ВОПРОС: в этом блоке кода выводит ошибку, ниже я заменил код
                // на то, что предложила Идея. Тогда код просто останавливается,
                // когда появляется null и промежут результат не норм:
//                while (!operators.peek().equals("(")) {
//                    //добавлялем в вывод элементы из стека операторов
//                    resultPostfixArray.add(operators.poll());
//                }
                while (true) {
                    assert operators.peek() != null;
                    if (operators.peek().equals("(")) break;
                    //добавлялем в вывод элементы из стека операторов
                    resultPostfixArray.add(operators.poll());
                }
                //удаляем откр скобку:
                operators.remove("(");
                //Если элемент массива открывающая скобка "(":
            } else if (item.equals("(")) {
                //добавляем элемент в стек операторов:
                operators.push(item);
            }
            /**
             * Блок else когда входит оператор:
             */
            else {
                int priority = FIELDS.getPriority().get(item);

                //todo Похоже нужно поправить, а то в условии написано "Если Стэк НЕ пустой...", а
                // по условию ниже получается что условие звучит наоборот "if operators.isEmpty()"!
                // Может как раз условие и должно звучать "Если Стэк ПУСТОЙ..."???
                //Если Стэк не пустой или последний элемент Стэка == "(":
                if (operators.isEmpty() || operators.peek().equals("(")) {
                    operators.push(item);

                    //Если входящий priority > приоритета последнего элемента Стэка:
                } else if (priority > FIELDS.getPriority().get(operators.peek())) {
                    operators.push(item);
                }

                //Условие "Если входящий priority <= приоритета последнего элемента Стэка"
                //todo Возможно добавить условие || Objects.requireNonNull(operators.peek()).equals(")")
                else if (priority <= FIELDS.getPriority().get(operators.peek())) {
                    while (!operators.isEmpty()) {
                        resultPostfixArray.add(operators.poll());
                    }
                    operators.push(item);
                }
            }
        }
        while (!operators.isEmpty()) {
            resultPostfixArray.add(operators.poll());
        }
        return resultPostfixArray;
    }


//            for (String item : this.expression) {
//                if (METHODS.isNumber(item)) {
//                    resultPostfixArray.add(item);
//                } else if (FIELDS.getPriority().get(item).equals(1)) { // ( = 1
//                    operators.push(item);
//                } else if (FIELDS.getPriority().get(item).equals(-1)) { // ) = -1
//                    //пока на вершине стека операторов не "(", выталкиваем в коолекц вывода
//                    while (!operators.peek().equals("(")) {
//                        resultPostfixArray.add(operators.poll());
//                    }
//                    operators.remove("(");
//                    // d. Если текущий токен - оператор, то:
//                } else if (FIELDS.getPriority().get(item) >= 2) { //см табл приоритетов.
//                    // i. Пока на вершине стека операторов есть операторы с большим или равным приоритетом, чем
//                    // текущий оператор,
//                    while (!(FIELDS.getPriority().get(item) >= FIELDS.getPriority().get(operators.peek()))) {
//                        //выталкивай их из стека операторов в список вывода.
//                        resultPostfixArray.add(operators.poll());
//                    }
//                    // ii. Помести текущий оператор в стек операторов
//                    operators.push(item);
//                }
//            }
//        //6.Когда все токены обработаны, вытолкни любые оставшиеся операторы из стека операторов в список вывода
//        while (!operators.isEmpty()) {
//            resultPostfixArray.add(operators.poll());
//        }
//        return resultPostfixArray;
//    }

}

