package OOP.solid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldSet {
    private List<String> token;
    private Map<Character, Character> bracket; //1.Во множ числе можно писать? 2.Здесь инициализировать или как я - ниже?

    public FieldSet() {
        this.token = List.of("+", "-", "/", "*", "(", ")", "[", "]", ".", "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9");
        this.bracket = new HashMap<>();
        this.bracket.put(')', '(');
        this.bracket.put(']', '[');
    }

    public List<String> getToken() {
        return token;
    }

    public Map<Character, Character> getBracket() {
        return bracket;
    }
}
