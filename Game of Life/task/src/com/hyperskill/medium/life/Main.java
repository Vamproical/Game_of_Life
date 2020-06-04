package com.hyperskill.medium.life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int sizeUniverse= scanner.nextInt();
        final long seed = scanner.nextLong();
        final int sizeGeneration = scanner.nextInt();
        Random random = new Random(seed);
        Generation generation = new Generation(sizeUniverse,random,sizeGeneration);
        generation.generation();
    }
}
