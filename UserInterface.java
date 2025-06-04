/*
 * Dock Pierce
 */

package RPSPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    private JFrame frame;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton replayButton;
    private Player Player;
    private CPU cpu;
    private int playerScore = 0;
    private int cpuScore = 0;
    private final int SCORE_LIMIT = 10;

    public static void main(String[] args) {
        new UserInterface();
    }

    public UserInterface() {
        Player = new Player();
        cpu = new CPU();

        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        replayButton = new JButton("Play Again");
        replayButton.setVisible(false);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(replayButton);

        resultLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        scoreLabel = new JLabel("Player: 0 | CPU: 0", SwingConstants.CENTER);

        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(resultLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        rockButton.addActionListener(e -> startCountdown("rock"));
        paperButton.addActionListener(e -> startCountdown("paper"));
        scissorsButton.addActionListener(e -> startCountdown("scissors"));
        replayButton.addActionListener(e -> resetGame());

        frame.setVisible(true);
    }

    private void startCountdown(String playerMove) {
        disablePlayButtons();
        int[] count = {3};
        Timer timer = new Timer(1000, null);
        timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (count[0] > 0) {
                    resultLabel.setText("..." + count[0] + "...");
                    count[0]--;
                } else {
                    timer.stop();
                    playRound(playerMove);
                    if (playerScore < SCORE_LIMIT && cpuScore < SCORE_LIMIT) {
                        enablePlayButtons();
                    }
                }
            }
        });
        timer.start();
    }

    private void playRound(String playerMove) {
        String cpuMove = cpu.getMove();
        String result = Control.getResult(playerMove, cpuMove);

        if (result.equals("You Win!")) {
            playerScore++;
        } else if (result.equals("CPU Wins!")) {
            cpuScore++;
        }

        resultLabel.setText("<html>CPU chose: " + cpuMove + "<br>" + result + "</html>");
        scoreLabel.setText("Player: " + playerScore + " | CPU: " + cpuScore);

        if (playerScore >= SCORE_LIMIT || cpuScore >= SCORE_LIMIT) {
            showGameOver();
        }
    }

    private void showGameOver() {
        String winner = (playerScore >= SCORE_LIMIT) ? "Player Wins!" : "CPU Wins!";
        resultLabel.setText("<html>Game Over! " + winner + "</html>");
        disablePlayButtons();
        replayButton.setVisible(true);
    }

    private void resetGame() {
        playerScore = 0;
        cpuScore = 0;
        resultLabel.setText("Make your move!");
        scoreLabel.setText("Player: 0 | CPU: 0");
        enablePlayButtons();
        replayButton.setVisible(false);
    }

    private void disablePlayButtons() {
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);
    }

    private void enablePlayButtons() {
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
    }
}

