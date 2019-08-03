package com.github.evchumichev.calculator.operations;

public class NaturalLogarithm extends OneParamOperation {
    @Override
    protected double apply(double x) {
        return Math.log(x);
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
