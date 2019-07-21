package com.github.evchumichev.calculator.operations;

public abstract class TwoParamOperation implements Operation {
    @Override
    public final double apply(double... params) {
        if (params.length != 2)
            throw new RuntimeException("Please insert correct Input");
        return apply(params[0], params[1]);
    }
    protected abstract double apply(double x, double y);

}
