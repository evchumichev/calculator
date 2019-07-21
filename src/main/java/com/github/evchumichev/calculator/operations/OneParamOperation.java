package com.github.evchumichev.calculator.operations;

public abstract class OneParamOperation implements Operation {
    @Override
    public double apply(double... params) {
        if (params.length != 1)
            throw new RuntimeException("Please insert correct Input!");
        return apply(params[0]);
    }

    protected abstract double apply(double x);

}
