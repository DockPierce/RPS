
package RPSPackage;

public class Control {
    public static String getResult(String playerMove, String cpuMove) {
        if (playerMove.equals(cpuMove)) {
            return "It's a Tie!";
        }
        if ((playerMove.equals("rock") && cpuMove.equals("scissors")) ||
            (playerMove.equals("paper") && cpuMove.equals("rock")) ||
            (playerMove.equals("scissors") && cpuMove.equals("paper"))) {
            return "You Win!";
        }
        return "CPU Wins!";
    }
}
