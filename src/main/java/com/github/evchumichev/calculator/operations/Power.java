package com.github.evchumichev.calculator.operations;

public class Power extends TwoParamOperation{
    @Override
    public double apply(double x, double y) {
        return Math.pow(x, y);
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
