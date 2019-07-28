package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputPart;

import java.util.List;

public class MainEventLoop {


    public void start() {

        UserInputGetter userInputGetter = new UserInputGetter();
        Parser parser = new SimpleParser();
        EvaluationService evaluationService = new EvaluationService();
        ExceptionChecker exceptionChecker = new ExceptionChecker();
        while (true) {
            try {
                String inputString = userInputGetter.getInput();
                if (inputString.equals("exit"))
                    return;
                List<InputPart> parts = parser.parse(inputString);
                exceptionChecker.apply(parts);
                double result = evaluationService.evaluate(parts);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Something went wrong - " + e.getMessage());
                e.getMessage();
            }

        }
    }
}
