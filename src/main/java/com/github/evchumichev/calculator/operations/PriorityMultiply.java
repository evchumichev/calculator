package com.github.evchumichev.calculator.operations;

public class PriorityMultiply extends TwoParamOperation{
    @Override
    protected double apply(double x, double y) {
        return x * y;
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
