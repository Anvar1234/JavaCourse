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
                int priority = FIELDS.getPriority().get(item);

                //Если Стек операторов пуст или верхний элемент Стека == "(":
                if (operators.isEmpty() || operators.peek().equals("(")) {
                    operators.push(item);

                    //Если входящий priority > приоритета последнего элемента Стэка:
                } else if (priority > FIELDS.getPriority().get(operators.peek())) {
                    operators.push(item);
                }

                //Условие "Если входящий priority <= приоритета последнего элемента Стэка"
                //todo ЗДЕСЬ БЫЛА ОШИБКА, У НАС ЖЕ ЕСТЬ ЕЩЕ "(" СКОБКА В СТЕКЕ В
                // ПРИМЕРЕ (, (, 0, -, 1, ), *, 1, -, (, 1, +, 2, ), ) И ОНА ПЕРЕХОДИТ В ВЫВОД:
                // [0, 1, -, 1, *, (, 1, 2, +, -]!
                else if (priority <= FIELDS.getPriority().get(operators.peek())) {

                    //todo Так как у нас условие выше ЕСЛИ входящий приоритет <= того, котор на вершине, то
                    // дальше начинаются выполнять условия, поэтому проверка
                    // if (!operators.isEmpty() || !operators.peek().equals("(")) похоже не нужна,
                    // так как у нас стек и так получается не пуст, да и
                    // проверка на наличие "(" вроде не нужна, так как у нас условие
                    //  выше priority <= FIELDS.getPriority().get(operators.peek())
                    //  не выполниться, если на вершине "(", так как в мапе в FIELDS нет
                    //  ( и ). А для проверки "(" еще выше есть свое условие.
                    //  Без этой проверки вроде норм работает: при наличии этой строки результат
                    //  равен: [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *],
                    //  без: [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]
                    //если стек операторов не пуст ИЛИ на вершине стека не "(":
                    if (!operators.isEmpty() || !operators.peek().equals("(")) { //ВОПРОС: без этого и (ниже)
                        resultPostfixArray.add(operators.poll()); //[1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -] - верно
                    } //и без этого норм работает, почему?
                    operators.push(item); //[1, 1, 2, +, -, 3, 4, +, 5, 7, *] - это косяк
                }
            }
        }
        while (!operators.isEmpty()) {
            resultPostfixArray.add(operators.poll());
        }
        return resultPostfixArray;
    }
}

