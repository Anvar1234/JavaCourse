package OOP.solid;

import java.util.*;

public class FieldsClass {
    private final List<String> token;
    private final Map<Character, Character> bracket; //1.Во множ числе можно писать? 2.Здесь инициализировать или как я - ниже?
    private final List<String> addToArrayToken;

    private final Map<String, Integer> priority;

    public FieldsClass() {
        this.token = List.of("+", "-", "/", "*", "(", ")", "[", "]", ".", "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");

        this.bracket = new HashMap<>();
        this.bracket.put(')', '(');
        this.bracket.put(']', '[');

        this.addToArrayToken = new ArrayList<>(Arrays.asList("(", "0", "-", "1", ")", "*"));

        this.priority = new HashMap<>();
        this.priority.put("+", 2);
        this.priority.put("-", 2);
        this.priority.put("*", 3);
        this.priority.put("/", 3);
        this.priority.put("(", 1);
        this.priority.put(")", -1);
    }

    public List<String> getToken() {
        return token;
    }

    public Map<Character, Character> getBracket() {
        return bracket;
    }

    public List<String> getAddToArrayToken() {
        return addToArrayToken;
    }

    public Map<String, Integer> getPriority() {
        return priority;
    }
}

