package com.github.evchumichev.calculator.operations;

import com.github.evchumichev.calculator.domain.InputPart;

public interface TwoParamOperation extends InputPart {
    double apply(double x, double y);

}
