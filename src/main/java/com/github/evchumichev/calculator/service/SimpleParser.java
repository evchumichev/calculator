package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.*;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleParser implements Parser {
    @Override
    public List<InputPart> parse(String s) {
        Map<String, Operation> operations = ImmutableMap.<String, Operation>builder()
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

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {

                if (isNumber)
                    continue;
                startIndex = i;
                isNumber = true;
                continue;
            }

            if (operations.containsKey(String.valueOf(c))) {
                isNumber = false;
                if ((c == '-' && i == 0) || (c == '-' && !(s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9'))) {
                    startIndex = i;
                    isNumber = true;
                    continue;
                }
                if (i == s.length() - 1 || i == 0) {
                    throw new RuntimeException("Incorrect format of input function!");
                }
                parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex, i))));
                parts.add(operations.get(String.valueOf(c)));
                continue;
            }


            throw new RuntimeException("Incorrect format of input function!");

        }
        parts.add(new InputNumber(Double.parseDouble(s.substring(startIndex))));
        return parts;
    }
}
