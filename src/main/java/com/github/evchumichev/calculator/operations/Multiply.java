package com.github.evchumichev.calculator.operations;

public class Multiply extends TwoParamOperation {
    @Override
    public double apply(double x, double y) {
        return x * y;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
