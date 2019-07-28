package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.*;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;

public class SimpleParser implements Parser {
    @Override
    public List<InputPart> parse(String s) {
        ImmutableMap<String, Operation> operations = ImmutableMap.<String, Operation>builder()
                .put("+", new Sum())
                .put("-", new Difference())
                .put("*", new Multiply())
                .put("/", new Division())
                .put("sqrt", new SquareRoot())
                .put("^", new Power())
                .build();

        List<InputPart> parts = new ArrayList<>();
        int startIndex = 0;
        boolean isNumber = false;
        boolean isOperation = false;
        boolean isNegative = false;

        s = s.replaceAll(" ", "");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (isOperation) {
                    if (!(operations.containsKey(s.substring(startIndex, i))))
                        throw new RuntimeException("Incorrect format of input function!");
                    parts.add(operations.get(s.substring(startIndex, i)));
                    startIndex = i;
                    isOperation = false;
                    isNumber = true;
                    continue;
                }
                if (isNegative) {
                    isNumber = true;
                    isNegative = false;
                }
                if (isNumber)
                    continue;
                startIndex = i;
                isNumber = true;
                continue;
            }

            if (!(c >= '0' && c <= '9')) {
                if ((c == '-' && i == 0) || (c == '-' && !(s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9'))) {
                    if (isOperation) {
                        if (!(operations.containsKey(s.substring(startIndex, i))))
                            throw new RuntimeException("Incorrect format of input function!");
                        parts.add(operations.get(s.substring(startIndex, i)));
                        isOperation = false;
                    }
                    startIndex = i;
                    isNumber = true;
                    continue;
                }
                if (isNumber) {
                    parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex, i))));
                    isNumber = false;
                }
                if (isOperation && !operations.containsKey(s.substring(startIndex, i)))
                    continue;
                if (isOperation && operations.containsKey(s.substring(startIndex, i))) {
                    parts.add(operations.get(s.substring(startIndex, i)));
                }
                isOperation = true;
                startIndex = i;

            }


        }
        if (isNumber)
            parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex))));
        if (isOperation) {
            if (!(operations.containsKey(s.substring(startIndex))))
                throw new RuntimeException("Incorrect format of input function!");
            parts.add(operations.get(s.substring(startIndex)));
        }

        return parts;
    }
}
