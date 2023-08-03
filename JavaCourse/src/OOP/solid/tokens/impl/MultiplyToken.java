package OOP.solid.tokens.impl;

import OOP.solid.tokens.MathOperations;
import OOP.solid.tokens.Tokens;

public class MultiplyToken implements MathOperations, Tokens {
    private final String symbol;
    private final int priority;

    public MultiplyToken() {
        this.symbol = "*";
        this.priority = 3;
    }

    @Override
    public double calculate(double[] args){
        return args[0] * args[1];
    }


    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
