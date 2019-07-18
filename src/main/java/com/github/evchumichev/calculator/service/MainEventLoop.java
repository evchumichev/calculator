package com.github.evchumichev.calculator.service;

import com.github.evchumichev.calculator.domain.InputPart;

import java.util.List;

public class MainEventLoop {


    public void start() {

        UserInputGetter userInputGetter = new UserInputGetter();
        Parser parser = new SimpleParser();
        EvaluationService evaluationService = new EvaluationService();
        OperationOrderBuilder operationOrderBuilder = new OperationOrderBuilder();
        while (true) {

            String inputString = userInputGetter.getInput();
            if (inputString.equals("exit"))
                return;
            List<InputPart> parts = parser.parse(inputString);
            List<Integer> operationsOrder = operationOrderBuilder.build(parts);
            double result = evaluationService.doIt(parts);
            System.out.println(result);

        }
    }
}
