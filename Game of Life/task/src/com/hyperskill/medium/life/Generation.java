package com.hyperskill.medium.life;

import java.util.Random;

public class Generation {
    private final int sizeUniverse;
    private final int numberOfGeneration;
    private final Random random;

    Generation(int sizeUniverse, Random random, int numberOfGeneration) {
        this.sizeUniverse = sizeUniverse;
        this.random = random;
        this.numberOfGeneration = numberOfGeneration;
    }

    private void printGeneration(Boolean[][] print) {
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                if (print[i][j] == null) {
                    System.out.println("Error i = " + i + " j = " + j);
                }
                else {
                    System.out.print(print[i][j] ? "O" : " ");
                }
            }
            System.out.println();
        }
    }

    private Boolean[][] createGeneration(Boolean[][] currentGeneration) {
        int aliveCells = 0;
        int neighbourI;
        int neighbourJ;
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
                aliveCells = 0;
            }
        }
        return nextGeneration;
    }

    public void generation() {
        Boolean[][] universe = new Boolean[sizeUniverse][sizeUniverse];
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                universe[i][j] = random.nextBoolean();
            }
        }
        if (numberOfGeneration != 0) {
            for (int i = 1; i <= numberOfGeneration; i++) {
                universe = createGeneration(universe);
            }
        }
        printGeneration(universe);
    }
}
