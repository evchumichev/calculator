package com.github.evchumichev.calculator.service;

import java.util.Scanner;

public final class UserInputGetter {
    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }
}
