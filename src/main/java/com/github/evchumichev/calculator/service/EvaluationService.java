package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.TwoParamOperation;

import java.util.List;

public class EvaluationService {
    public double doIt(List<InputPart> parts) {
        double answer = 0;

        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i) instanceof TwoParamOperation) {
                InputNumber x = (InputNumber) parts.get(i - 1);
                InputNumber y = (InputNumber) parts.get(i + 1);
                answer = ((TwoParamOperation) parts.get(i)).apply(x.getNumber(), y.getNumber());
            }
        }
        return answer;
    }


}
