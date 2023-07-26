package OOP.solid;

import java.util.*;

public class FieldsClass {
    private final List<String> token;
    private final Map<Character, Character> bracket; //1.Во множ числе можно писать? 2.Здесь инициализировать или как я - ниже?
    private final List<String> additionalCollectionOfTokens;

    private final Map<String, Integer> priority;

    public FieldsClass() {
        this.token = List.of("+", "-", "/", "*", "(", ")", "[", "]", ".", "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "%");

        this.bracket = new HashMap<>();
        this.bracket.put(')', '(');
        this.bracket.put(']', '[');

        this.additionalCollectionOfTokens = new ArrayList<>(Arrays.asList("(", "0", "-", "1", ")", "*"));

        this.priority = new HashMap<>();
        this.priority.put("+", 2);
        this.priority.put("-", 2);
        this.priority.put("*", 3);
        this.priority.put("/", 3);
        this.priority.put("%", 4); // это типа возведение в степень, например. Если нужно добавить символ, то
        //нужно не забыть откорректировать регулярное выражение в методе addSpaces.
    }

    public List<String> getToken() {
        return token;
    }

    public Map<Character, Character> getBracket() {
        return bracket;
    }

    public List<String> getAdditionalCollectionOfTokens() {
        return additionalCollectionOfTokens;
    }

    public Map<String, Integer> getPriority() {
        return priority;
    }
}

