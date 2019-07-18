package com.github.evchumichev.calculator.operations;

public class Division implements TwoParamOperation {
    @Override
    public double apply(double x, double y) {
        return x / y;
    }
}
