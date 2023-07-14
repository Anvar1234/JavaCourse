package OOP.solid;

import java.util.ArrayList;

public class DateTransformator {
    private final ArrayList<String> validExpression;
    private final FieldSet FIELDS;
    private final MethodSet METHODS;



    public DateTransformator(String expression) {
        DateValidator dateValidator = new DateValidator(expression);
        this.validExpression = dateValidator.resultArrayAfterValidation();
        this.FIELDS = new FieldSet();
        this.METHODS = new MethodSet();
    }

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
}
