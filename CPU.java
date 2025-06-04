
package RPSPackage;

import java.util.Random;

public class CPU {
    private String name;
    private String move;
    private String playerLastMove;
    private Random random;

    public CPU() {
        this.name = "Computer";
        this.random = new Random();
        this.move = "";
        this.playerLastMove = "";
    }

    public void setPlayerLastMove(String playerMove) {
        this.playerLastMove = playerMove.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getMove() {
        return getRandomMove();
    }

    private String getRandomMove() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0: return "rock";
            case 1: return "paper";
            default: return "scissors";
        }
    }
}
