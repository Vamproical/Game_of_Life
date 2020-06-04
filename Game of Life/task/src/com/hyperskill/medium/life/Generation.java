package com.hyperskill.medium.life;

import java.io.IOException;
import java.util.Random;

public class Generation {
    private final int sizeUniverse;
    private final Random random = new Random();

    Generation(int sizeUniverse) {
        this.sizeUniverse = sizeUniverse;
    }

    private void printGeneration(Boolean[][] print) {
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                System.out.print(print[i][j] ? "O" : " ");
            }
            System.out.println();
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Boolean[][] createGeneration(Boolean[][] currentGeneration) {
        int aliveCells = 0;
        int neighbourI;
        int neighbourJ;
        int numberOfAlive = 0;
        Boolean[][] nextGeneration = new Boolean[sizeUniverse][sizeUniverse];
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                neighbourI = (i + sizeUniverse - 1) % sizeUniverse;
                neighbourJ = (j + sizeUniverse - 1) % sizeUniverse;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = (i + sizeUniverse - 1) % sizeUniverse;
                neighbourJ = j;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = (i + sizeUniverse + 1) % sizeUniverse;
                neighbourJ = j;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = (i + sizeUniverse - 1) % sizeUniverse;
                neighbourJ = (j + sizeUniverse + 1) % sizeUniverse;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = (i + sizeUniverse + 1) % sizeUniverse;
                neighbourJ = (j + sizeUniverse - 1) % sizeUniverse;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = i;
                neighbourJ = (j + sizeUniverse - 1) % sizeUniverse;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = i;
                neighbourJ = (j + sizeUniverse + 1) % sizeUniverse;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                neighbourI = (i + sizeUniverse + 1) % sizeUniverse;
                neighbourJ = (j + sizeUniverse + 1) % sizeUniverse;
                if (currentGeneration[neighbourI][neighbourJ]) ++aliveCells;

                if (currentGeneration[i][j]) {
                    nextGeneration[i][j] = aliveCells == 2 || aliveCells == 3;
                } else {
                    nextGeneration[i][j] = aliveCells == 3;
                }
                if (nextGeneration[i][j]) ++numberOfAlive;
                aliveCells = 0;
            }
        }
        System.out.println("Alive: " + numberOfAlive);
        return nextGeneration;
    }

    public void generation() {
        Boolean[][] universe = new Boolean[sizeUniverse][sizeUniverse];
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                universe[i][j] = random.nextBoolean();
            }
        }
        int i = 1;
        while (i != 100) {
            System.out.println("Generation #" + i);
            universe = createGeneration(universe);
            printGeneration(universe);
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    Thread.sleep(300);
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (IOException | InterruptedException ignored) {
            }
            i++;
        }
    }
}
