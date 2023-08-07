import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TitleScreen {
    private final Game game;
    JPanel titleNamePanel;
    JPanel startButtonPanel;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);

    public TitleScreen(Game game) {
        this.game = game;

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        JLabel titleNameLabel = new JLabel(" Quest For Glory ");
        titleNameLabel.setForeground(Color.white);
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        startButton.addActionListener(new TitleScreenHandler());

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
    }

    public void showTitleScreen() {
        game.con.add(titleNamePanel);
        game.con.add(startButtonPanel);
    }

    class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            game.gameplay.showGameplay();
        }
    }
}