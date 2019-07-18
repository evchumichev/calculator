package com.github.evchumichev.calculator.operations;

public class Multiply implements TwoParamOperation {
    @Override
    public double apply(double x, double y) {
        return x * y;
    }
}
