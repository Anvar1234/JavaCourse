package OOP.solid;

import java.util.*;


/**
 * Класс, хранящий неизменяемые поля, применение которых возможно для разных классов.
 */
public class Fields {
    public final static  List<String> tokens = List.of("+", "-", "/", "*", "(", ")", "[", "]", ".", "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "^");;
    public final static  Map<Character, Character> brackets = Map.of(')', '(', ']', '['); //1.Во множ числе можно писать? 2.Здесь инициализировать или как я - ниже?
    public final static  List<String> additionalCollectionOfTokens = Arrays.asList("(", "0", "-", "1", ")", "*");
    public final static  Map<String, Integer> priorities = Map.of("+", 2,"-", 2, "*", 3, "/", 3, "^", 4);


}

