package OOP.solid.tokens.impl;

import OOP.solid.tokens.Tokens;
import OOP.solid.tokens.MathOperations;

public class ZeroToken implements Tokens, MathOperations {


    @Override
    public double calculate(double[] args) {
        return 0;
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isNumber() {
        return true;
    }
}
