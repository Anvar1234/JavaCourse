package OOP.solid;

import java.util.*;

/**
 * Класс для перевода пользовательского выражения в обратную польскую нотацию (ОПН).
 */
public class PolandNotationConverter {
    private final FieldsClass FIELDS;
    private final MethodsClass METHODS;
    private final ArrayList<String> expression;

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
            //Если элемент массива число, то в выводной список:
            if (METHODS.isNumber(item)) {
                resultPostfixArray.add(item);
            }
            //Если элемент массива открывающая скобка "(":
            else if (item.equals("(")) {
                //добавляем элемент в стек операторов:
                operators.push(item);
            } else if (item.equals(")")) {//Если элемент массива закр скобка ")", то:
                //todo Условие ниже (первый if) скорее всего тоже можно убрать.
                if (!operators.isEmpty()) {//если стек операторов не пуст, то:
                    if (!operators.peek().equals("(")) {//пока не встретим на вершине стека откр скобку "(":
                        //добавлялем в вывод список элементы из стека операторов (опер-ры удаляем):
                        resultPostfixArray.add(operators.poll());
                    }
                    //иначе удаляем откр скобку (последний эл-нт): до этого было operators.remove("(");
                    operators.poll();
                }
                //todo Удалить саут, это просто для наглядности.
                //если стек опер-ров пуст, то:
                //System.out.println("Стек пуст, переходим дальше");
            }
            /**
             * Блок else когда входит оператор:
             */
            else {
                int incomingPriority = FIELDS.getPriority().get(item);

                //Если Стек операторов пуст или верхний элемент Стека == "(":
                if (operators.isEmpty() || operators.peek().equals("(")) {
                    operators.push(item);

                    //Если входящий priority > приоритета последнего элемента Стэка:
                } else if (incomingPriority > FIELDS.getPriority().get(operators.peek())) {
                    operators.push(item);
                }

                //Условие "Если входящий priority <= приоритета верхнего элемента Стэка"
                else if (incomingPriority <= FIELDS.getPriority().get(operators.peek())) {

                    //  Ошибка была в том, что в цикле while вместо && ставил ||, и peek выдавал ноль.
                    while (!operators.isEmpty() && !operators.peek().equals("(") && incomingPriority <= FIELDS.getPriority().get(operators.peek())) {
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



}


