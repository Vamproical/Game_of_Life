package com.hyperskill.medium.life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int sizeUniverse = scanner.nextInt();
        Generation generation = new Generation(sizeUniverse);
        generation.generation();
    }
}
