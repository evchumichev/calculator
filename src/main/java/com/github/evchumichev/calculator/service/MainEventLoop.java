package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputPart;

import java.util.List;

public class MainEventLoop {


    public void start() {

        UserInputGetter userInputGetter = new UserInputGetter();
        Parser parser = new SimpleParser();
        EvaluationService evaluationService = new EvaluationService();
        while (true) {

            String inputString = userInputGetter.getInput();
            if (inputString.equals("exit"))
                return;
            List<InputPart> parts = parser.parse(inputString);
            double result = evaluationService.evaluate(parts);
            System.out.println(result);

        }
    }
}
