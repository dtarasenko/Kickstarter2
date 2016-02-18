package com.kickstarter;

import java.util.Scanner;

public class ConsoleIO implements IO {

    public String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void print(String message) {
        System.out.print(message);
    }
}