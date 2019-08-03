package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.*;
import com.github.evchumichev.calculator.operations.*;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;

public class SimpleParser implements Parser {
    @Override
    public List<InputPart> parse(String inputString) {
        ImmutableMap<String, Operation> operations = ImmutableMap.<String, Operation>builder()
                .put("+", new Sum())
                .put("-", new Difference())
                .put("*", new Multiply())
                .put("/", new Division())
                .put("sqrt", new SquareRoot())
                .put("^", new Power())
                .build();

        ImmutableMap<String, Parenthesis> parenthesis = ImmutableMap.<String, Parenthesis>builder()
                .put("(", new LeftParenthesis())
                .put(")", new RightParenthesis())
                .build();
        List<InputPart> parts = new ArrayList<>();
        int startIndex = 0;
        boolean isNumber = false;
        boolean isOperation = false;

        inputString = inputString.replaceAll(" ", "");

        for (int i = 0; i < inputString.length(); i++) {
            char currentIndexCharacter = inputString.charAt(i);
            if (currentIndexCharacter == '-' && inputString.length() - 1 > 1) {
                if ((i == 0 || isOperation || (inputString.charAt(i - 1) == '(')) && inputString.charAt(i + 1) >= '0' && inputString.charAt(i + 1) <= '9') {
                    if (isOperation) {
                        if (!operations.containsKey(inputString.substring(startIndex,i)))
                            throw new RuntimeException("Your input is incorrect!");
                        parts.add(operations.get(inputString.substring(startIndex, i)));
                        isOperation = false;
                    }
                    isNumber = true;
                    startIndex = i;
                    continue;
                }
                if ((i == 0 || isOperation || (inputString.charAt(i - 1) == '(')) && (!(inputString.charAt(i + 1) >= '0' && inputString.charAt(i + 1) <= '9') && !(operations.containsKey(String.valueOf(inputString.charAt(i + 1)))))) {
                    if (isOperation) {
                        if (!operations.containsKey(inputString.substring(startIndex,i)))
                            throw new RuntimeException("Your input is incorrect!");
                        parts.add(operations.get(inputString.substring(startIndex, i)));
                        isOperation = false;
                    }
                    parts.add(new InputNumber(-1));
                    parts.add(new PriorityMultiply());
                    continue;
                }
            }
            if (parenthesis.containsKey(String.valueOf(currentIndexCharacter))) {
                if (isNumber) {
                    parts.add(new InputNumber(Double.parseDouble(inputString.substring(startIndex, i))));
                    isNumber = false;
                }
                if (isOperation) {
                    if (!operations.containsKey(inputString.substring(startIndex,i)))
                        throw new RuntimeException("Your input is incorrect!");
                    parts.add(operations.get(inputString.substring(startIndex, i)));
                    isOperation = false;
                }
                parts.add(parenthesis.get(String.valueOf(currentIndexCharacter)));
                continue;
            }
            if (currentIndexCharacter >= '0' && currentIndexCharacter <= '9') {
                if (isNumber)
                    continue;
                if (isOperation) {
                    if (!operations.containsKey(inputString.substring(startIndex,i)))
                        throw new RuntimeException("Your input is incorrect!");
                    parts.add(operations.get(inputString.substring(startIndex, i)));
                    isOperation = false;
                }
                isNumber = true;
                startIndex = i;
                continue;
            }
            if (isNumber) {
                parts.add(new InputNumber(Double.parseDouble(inputString.substring(startIndex, i))));
                isNumber = false;
            }
            if (isOperation) {
                if (!(operations.containsKey(inputString.substring(startIndex, i)))) {
                    continue;
                }
                parts.add(operations.get(inputString.substring(startIndex, i)));
            }
            isOperation = true;
            startIndex = i;
        }
        if (isOperation) {
            if (!operations.containsKey(inputString.substring(startIndex)))
                throw new RuntimeException("Your input is incorrect!");
            parts.add(operations.get(inputString.substring(startIndex)));
        }
        if (isNumber)
            parts.add(new InputNumber(Double.parseDouble(inputString.substring(startIndex))));
        return parts;
    }
}
