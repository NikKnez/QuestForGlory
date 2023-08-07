import javax.swing.*;
import java.awt.*;

class Game {
    JFrame window;
    Container con;
    TitleScreen titleScreen;
    Gameplay gameplay;

    public Game() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();

        titleScreen = new TitleScreen(this);
        gameplay = new Gameplay(this);
    }

    public void start() {
        titleScreen.showTitleScreen();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
