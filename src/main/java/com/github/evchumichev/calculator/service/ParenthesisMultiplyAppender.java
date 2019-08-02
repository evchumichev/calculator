package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputNumber;
import com.github.evchumichev.calculator.domain.InputPart;
import com.github.evchumichev.calculator.domain.LeftParenthesis;
import com.github.evchumichev.calculator.domain.RightParenthesis;
import com.github.evchumichev.calculator.operations.Multiply;

import java.util.List;

public class ParenthesisMultiplyAppender {
    public void apply(List<InputPart> parts) {
        if (parts.isEmpty())
            throw new RuntimeException("Your input function is empty!\nPlease, insert correct input function!");
        for (int i = 0; i < parts.size(); i++) {
            int end = parts.size() - 1;
            if (parts.get(i) instanceof LeftParenthesis && i > 0 && (parts.get(i - 1) instanceof InputNumber || parts.get(i - 1) instanceof RightParenthesis)) {
                parts.add(i++, new Multiply());
                continue;
            }
            if (parts.get(i) instanceof RightParenthesis && i < end && parts.get(i + 1) instanceof InputNumber) {
                parts.add(++i, new Multiply());
                continue;
            }
        }
    }
}
