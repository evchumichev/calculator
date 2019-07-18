package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputPart;

import java.util.LinkedList;
import java.util.List;

public interface Parser {
    List<InputPart> parse(String s);

}
