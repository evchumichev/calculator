package com.github.evchumichev.calculator.operations;

public class Logarithm extends OneParamOperation {
    @Override
    protected double apply(double x) {
        return Math.log10(x);
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
