
package RPSPackage;

public class Player {
    private String choice;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        this.name = "Player";
        this.choice = "rock";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getChoice() {
        return choice;
    }

    public void makeChoice(String choice) {
        if (choice.matches("rock|paper|scissors")) {
            this.choice = choice;
        } else {
            System.out.println("Wrong input: Choose Rock, Paper, or Scissors.");
        }
    }
}
