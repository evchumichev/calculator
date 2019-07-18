package com.github.evchumichev.calculator.domain;

public class InputNumber implements InputPart {
    private final double number;

    public InputNumber(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }
}
