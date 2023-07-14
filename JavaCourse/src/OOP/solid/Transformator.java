package OOP.solid;

import java.util.ArrayList;

public class Transformator {

    private String validExpression;
    private FieldSet fields;
    private MethodSet methods;



    public Transformator(String validExpression) {
        this.validExpression = validExpression;
        this.fields = new FieldSet();
        this.methods = new MethodSet();
    }

    public ArrayList<String> isUnaryMinus(ArrayList<String> arrayListTokens) {

        //здесь добавить if выполняются необх предыдущие методы, то.
        for (int i = 1; i < arrayListTokens.size(); i++) {
            if (i == 1 && arrayListTokens.get(0).equals("-") &&
                    (fields.getBracket().containsValue(arrayListTokens.get(1).charAt(0)) ||
                            methods.isNumber(arrayListTokens.get(1)))) {
                //todo ВОПРОС: в brackets ошибка, не заходит в мапу походу. А птму что там у меня символы,
                // а здесь я использую стринг. Возможно лучше переделать все методы,
                // где используются char, либо здесь все правильно написал и делать лучше локально?
                arrayListTokens.set(0, "#");
                i++;
                System.out.println(i);
            } else if (arrayListTokens.get(i - 1).equals("-") &&
                    (fields.getBracket().containsValue(arrayListTokens.get(i - 2).charAt(0)) ||
                            fields.getBracket().containsValue(arrayListTokens.get(i).charAt(0)))) {
                arrayListTokens.set(i - 1, "#");
                i++;
                System.out.println(i);
            }

        }
        return arrayListTokens;
    }
}
