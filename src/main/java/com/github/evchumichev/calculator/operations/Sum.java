package com.github.evchumichev.calculator.operations;

public class Sum extends TwoParamOperation {
    @Override
    public double apply(double x, double y) {
        return x + y;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
