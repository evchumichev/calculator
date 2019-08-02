package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.*;
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

        ImmutableMap<String, Parenthesis> parenthesis = ImmutableMap.<String, Parenthesis>builder()
                .put("(", new LeftParenthesis())
                .put(")", new RightParenthesis())
                .build();
        List<InputPart> parts = new ArrayList<>();
        int startIndex = 0;
        boolean isNumber = false;
        boolean isOperation = false;

        s = s.replaceAll(" ", "");

        for (int i = 0; i < s.length(); i++) {
            char currentIndexCharacter = s.charAt(i);
            if (currentIndexCharacter == '-' && s.length() - 1 > 1) {
                if ((i == 0 || (!parts.isEmpty() && (parts.get(parts.size() - 1)) instanceof Operation)) && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')) {
                    isNumber = true;
                    continue;
                }
                if ((i == 0 || (!parts.isEmpty() && (parts.get(parts.size() - 1)) instanceof TwoParamOperation) && (!operations.containsKey(String.valueOf(s.charAt(i + 1))) && String.valueOf(s.charAt(i + 1)) == "("))) {
                    parts.add(new InputNumber(-1));
                    parts.add(new Multiply());
                    continue;
                }
            }
            if (parenthesis.containsKey(String.valueOf(currentIndexCharacter))) {
                if (isNumber) {
                    parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex, i))));
                    isNumber = false;
                }
                if (isOperation) {
                    parts.add(operations.get(s.substring(startIndex, i)));
                    isOperation = false;
                }
                parts.add(parenthesis.get(String.valueOf(currentIndexCharacter)));
                continue;
            }
            if (currentIndexCharacter >= '0' && currentIndexCharacter <= '9') {
                if (isNumber)
                    continue;
                if (isOperation) {
                    parts.add(operations.get(s.substring(startIndex, i)));
                    isOperation = false;
                }
                isNumber = true;
                startIndex = i;
                continue;
            }
            if (isNumber) {
                parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex, i))));
                isNumber = false;
            }
            if (isOperation) {
                if (!(operations.containsKey(s.substring(startIndex, i)))) {
                    continue;
                }
                parts.add(operations.get(s.substring(startIndex, i)));
            }
            isOperation = true;
            startIndex = i;
        }
        if (isOperation)
            throw new RuntimeException("Your input is incorrect!");
        if (isNumber)
            parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex))));
        return parts;
    }
}
