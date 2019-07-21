package com.github.evchumichev.calculator.operations;

public class SquareRoot extends OneParamOperation{
    @Override
    public double apply(double x) {
        return Math.sqrt(x);
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
