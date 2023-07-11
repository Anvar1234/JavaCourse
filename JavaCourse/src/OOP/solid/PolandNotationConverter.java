package OOP.solid;

public class PolandNotationConverter {

    private String expression;
    private DateValidator dateValidator;

    public PolandNotationConverter(String expression) {
        this.expression = expression;
        this.dateValidator = new DateValidator(expression);
    }

    public String convert(){
        if(dateValidator.isExpressionValid()){
            //если фолс, то исключение.
        }
        return null; //удалить
    }
    /**
     * используем здесь результат результат из ДатеВалидатор.
     */
}
