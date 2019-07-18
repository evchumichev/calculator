package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.*;

import java.util.LinkedList;
import java.util.List;

public class OperationOrderBuilder {

    public LinkedList<Integer> build(List<InputPart> parts) {
        LinkedList<Integer> operationsQueue = new LinkedList<>();
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i) instanceof Difference || parts.get(i) instanceof Sum) {
                operationsQueue.addLast(i);
            }
            if (parts.get(i) instanceof Multiply || parts.get(i) instanceof Division) {
                operationsQueue.addFirst(i);
            }

        }
        return operationsQueue;
    }
}
