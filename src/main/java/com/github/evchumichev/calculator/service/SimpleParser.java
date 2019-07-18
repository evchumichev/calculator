package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.operations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleParser implements Parser {
    @Override
    public List<InputPart> parse(String s) {

        List<InputPart> parts = new ArrayList<>();
        HashMap<String, TwoParamOperation> operations = new HashMap<>();
        operations.put("+", new Sum());
        operations.put("-", new Difference());
        operations.put("*", new Multiply());
        operations.put("/", new Division());

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
