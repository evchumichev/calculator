package com.github.evchumichev.calculator.operations;

import com.github.evchumichev.calculator.domain.InputPart;

public interface Operation extends InputPart {
    double apply(double ... params);
    int getPriority();
}
