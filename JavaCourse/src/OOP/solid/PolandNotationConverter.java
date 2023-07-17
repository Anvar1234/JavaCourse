package OOP.solid;

import java.util.ArrayList;

public class PolandNotationConverter {

    private String expression;
    private DateValidator dateValidator;
    private DateTransformator dateTransformator;

    public PolandNotationConverter(String expression) {
        this.expression = expression;
        this.dateValidator = new DateValidator(this.expression);
        this.dateTransformator = new DateTransformator(this.dateValidator);

    }

    /**
     * Метод
     */
    public ArrayList<String> convertToPostfix(){
        if(dateValidator.isExpressionValid()){
            //если тру, то проверяем на наличие унарного минуса:
            //если фолс, то исключение.
            if (dateValidator.isUnaryMinus()){
                //если тру, то меняем унарный минус на спецсимвол.
                dateTransformator.unaryMinusSymbolChanger();
                //если фолс, то начинаем непосредственно перевод в польскую нотацию.
            }
        }
        return null; //null удалить как логика будет
    }
}
