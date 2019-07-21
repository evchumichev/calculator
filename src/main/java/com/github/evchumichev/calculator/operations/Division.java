package com.github.evchumichev.calculator.operations;

public class Division extends TwoParamOperation {
    @Override
    public double apply(double x, double y) {
        return x / y;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
