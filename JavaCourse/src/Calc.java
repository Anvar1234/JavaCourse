import java.util.*;

/**
 * Примечания:
 * 1) Калькулятор выбрасывает исключение, если число отрицательное.
 * 2) Результат выводится в виде double, пока не знаю как использовать какой-то универс тип данных (вопросы ниже).
 * 3) 5+65*(1-2)+33/2-2 = Норм Ответ: -45,5. Такое считает некорректно, так же как и онлайн переводчик: http://primat.org/news/obratnaja_polskaja_zapis/2016-04-09-1181
 * а другой норм считает: https://raj457036.github.io/Simple-Tools/prefixAndPostfixConvertor.html
 * 4) В постфикс вроде как раз-таки переводит норм, а считает из постфикса в результат не норм.
 */
public class Calc {

    //Методы для определения является ли элемент числом (инт или дабл).
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Метод для определения приоритета оператора:
    public static Integer priorityOfChars(String character) {
        HashMap<String, Integer> priority = new HashMap<>();
        priority.put("+", 2);
        priority.put("-", 2);
        priority.put("*", 3);
        priority.put("/", 3);
        priority.put("(", 1);
        priority.put(")", -1);

        if (priority.containsKey(character)) return priority.get(character);
        return null;
    }

    //Метод для перевода пользовательского выражения в постфиксную/польскую нотацию:
    public static Queue<String> getPostfixNotation(String text) {

        //5 +45 -15/( 8.23-23.5/ 1) - пример для расчета.
        //Вопрос: почему нужно использовать именно такую конструкцию ниже, именно  new String[]{}?
        //Это Идея предложила автоисправление.
        //Вопрос: почистить можно только массив строк? Можно ли почистить стринг или как-то иначе?
        //Создадим массив строк, чтобы можно было подчистить от лишних пробелов и если нужно знаков.
        String[] textCleaning = new String[]{text.trim().replaceAll(" ", "")};

        StringBuilder sbForWriting = new StringBuilder();

        //Заполняем строку(?) СтрингБилдер для разграничения операторов и операндов пробелами в
        //вычищенном от лишних пробелов пользовательском выражении.
        // Работает и для целых чисел, и для чисел с точкой.
        for (int i = 0; i < textCleaning[0].length(); i++) {
            if (Character.isDigit(textCleaning[0].charAt(i)) || textCleaning[0].charAt(i) == '.')
                sbForWriting.append(textCleaning[0].charAt(i));
            else if (textCleaning[0].charAt(i) == '(') {
                sbForWriting.append(textCleaning[0].charAt(i)).append(" ");
            } else if (textCleaning[0].charAt(i) == ')') {
                sbForWriting.append(" ").append(textCleaning[0].charAt(i));
            } else sbForWriting.append(" ").append(textCleaning[0].charAt(i)).append(" ");
        }

        //Получаем массив Строк, где операнды и операторы явл отдельными значениями
        String[] strArray1 = sbForWriting.toString().split(" ");

        //Вопрос: правильно ли выбраны интерфейсы, реализации, обобщения типов?
        Deque<String> deqStack = new ArrayDeque<>(); //Вместо стека
        Queue<String> queQueue = new ArrayDeque<>(); //Вместо очереди

        for (String s : strArray1) {
            //Условие "Если элемент массива целое или с плавающей точкой"
            if (isInteger(s) || isDouble(s)) {
                queQueue.add(s);
                //Условие "Если элемент массива закр скобка ")""
            } else if (s.equals(")")) {
                while (!deqStack.peek().equals("(")) {
                    queQueue.add(deqStack.poll());
                }
                deqStack.remove("("); // Ошибка была в том, что удалялась не скобка, а последний элемент removeLast
                //Условие "Если элемент массива открывающая скобка "(""
            } else if (s.equals("(")) {
                deqStack.push(s);
            }
            //Блок else когда входит оператор:
            else { //Вопрос: Здесь возможно нужно/можно использовать свитч-кейс?
                int priority = priorityOfChars(s);

                //Условие "Если Стэк не пустой или последний элемент Стэка == '('"
                if (deqStack.isEmpty() || deqStack.peek().equals("(")) {
                    deqStack.push(s);

                    //Условие "Если входящий priority > приоритета последнего элемента Стэка"
                } else if (priority > priorityOfChars(deqStack.peek())) {
                    deqStack.push(s);
                }

                //Условие "Если входящий priority <= приоритета последнего элемента Стэка"
                else if (priority <= priorityOfChars(deqStack.peek())) { //Ошибка была в том, что использовал оператор || с условием, что deqStack.peek().equals("(").
                    while (!deqStack.isEmpty()) {
                        queQueue.add(deqStack.poll());
                    }
                    deqStack.push(s);
                }
            }
        }
        while (!deqStack.isEmpty()) {
            queQueue.add(deqStack.poll());
        }
        return queQueue;
    }

    //Метод для вычисления значения постфиксного выражения:
    public static Deque<Double> getResultOfEvaluatingPostfixExpression(Queue<String> queueNew) {
//Вопрос: можно ли при указании обобщения использовать какой-то универсальный тип, есть такой?
        //Я пытался указать класс Object, тогда ниже, где начинаются вычисления идея пишет мне,
        // что + * / - не применимы к Object.

        //Объявим и инициализируем стэк <Object> для считывания очереди:
        Deque<Double> resStack = new ArrayDeque<>();
        //Объявим переменную типа var для результирующего значение:
        double result;

        for (String s : queueNew) {
            //Условие "Если элемент массива целое или иначе с плавающей точкой"
            if (isInteger(s)) resStack.push((double) Integer.parseInt(s)); //переводим в целое
            else if (isDouble(s)) resStack.push(Double.parseDouble(s)); //переводим в вещественное
            else {
                switch (s) {
                    //Вопрос: В каком случае лучше использовать poll а в каком pop? Если ниже заменить на poll,
                    //то идея выделяет это выражение желтым.
                    //Разницу poll и pop знаю.
                    case "*":
                        result = resStack.pop() * resStack.pop();
                        resStack.push(result);
                        break;
                    case "/":
                        result = resStack.pop() / resStack.pop();
                        resStack.push(result);
                        break;
                    case "-":
                        result = -resStack.pop() + resStack.pop();
                        resStack.push(result);
                        break;
                    case "+":
                        result = resStack.pop() + resStack.pop();
                        resStack.push(result);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + s);
                }
            }
        }
        return resStack;
    }

    public static void main(String[] args) {
        System.out.print("Введите выражение: ");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        Queue<String> queQueue1 = getPostfixNotation(text);
        System.out.println("Постфиксная нотация (здесь очередь): ");
        System.out.println(queQueue1);

        Deque<Double> resultStack = getResultOfEvaluatingPostfixExpression(queQueue1);
        System.out.println("Результат: ");
        System.out.println(resultStack);

        //Вопрос: как понять какой реализации как вручную задать значения, какие использовать скобки, разделитель?
        //Например:
//        Queue<String> queQueue2 = new ArrayDeque<>("12","1","+","15","-"); // - так ошибка.
//        System.out.println(queQueue2);
//        Deque<Double> resultStack2 = getResultOfEvaluatingPostfixExpression(queQueue2);
//        System.out.println(resultStack2);

    }
}



