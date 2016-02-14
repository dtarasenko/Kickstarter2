package com.kickstarter;

import java.util.Scanner;

public class ConsoleIO implements IO {
    public int read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public void print(String message) {
        System.out.print(message);
    }
}