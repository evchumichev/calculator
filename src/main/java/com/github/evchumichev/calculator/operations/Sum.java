package com.github.evchumichev.calculator.operations;

public class Sum implements TwoParamOperation {
    @Override
    public double apply(double x, double y) {
        return x + y;
    }
   // public double apply(double x) { return x; }
}
