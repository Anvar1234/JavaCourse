package OOP.solid.tokens;

public interface Tokens {
    String getSymbol();

    int getPriority();

    default boolean isNumber(){
        return false;
    }

    default boolean isEquals(Tokens a, Tokens b){
        return a.getSymbol().trim().equals(b.getSymbol().trim());
    }


}
