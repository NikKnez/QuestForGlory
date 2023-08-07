import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Gameplay {
    private final Game game;
    private JTextArea mainTextArea;
    private JLabel hpLabelNumber;
    private JLabel weaponLabelName;
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private JButton choice1, choice2, choice3, choice4;
    private int playerHP, dragonHP, silverRing;
    private String weapon, position;
    private final ChoiceHandler choiceHandler;

    public Gameplay(Game game) {
        this.game = game;
        choiceHandler = new ChoiceHandler();
    }

    public void showGameplay() {
        game.con.remove(game.titleScreen.titleNamePanel);
        game.con.remove(game.titleScreen.startButtonPanel);
        setUpMainTextPanel();
        setUpChoiceButtonPanel();
        setUpPlayerPanel();
        playerSetup();
        game.window.setVisible(true);
    }

    private void setUpMainTextPanel() {
        JPanel mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        game.con.add(mainTextPanel);

        mainTextArea = new JTextArea("Main text");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }

    private void setUpChoiceButtonPanel() {
        JPanel choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        game.con.add(choiceButtonPanel);

        choice1 = new JButton("Choice 1");
        choice1.setOpaque(true);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.blue);
        choice1.setFont(normalFont);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setOpaque(true);
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.blue);
        choice2.setFont(normalFont);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setOpaque(true);
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.blue);
        choice3.setFont(normalFont);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setOpaque(true);
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.blue);
        choice4.setFont(normalFont);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);
    }

    private void setUpPlayerPanel() {
        JPanel playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        game.con.add(playerPanel);

        JLabel hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.green);
        playerPanel.add(hpLabelNumber);

        JLabel weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);

        weaponLabelName = new JLabel();
        weaponLabelName.setFont(normalFont);
        weaponLabelName.setForeground(Color.yellow);
        playerPanel.add(weaponLabelName);
    }

    private void playerSetup() {
        playerHP = 100;
        dragonHP = 120;
        weapon = "Knife";
        weaponLabelName.setText(weapon);
        hpLabelNumber.setText(String.valueOf(playerHP));
        townGate();
    }

    private void townGate() {
        position = "townGate";
        mainTextArea.setText("You are at the gate of the town.\nA guard is standing in front of you.\nWhat do you do?");
        choice1.setText("Talk to the guard");
        choice2.setText("Attack the guard");
        choice3.setText("Go to crossroad");
        choice4.setText("");
    }

    private void talkGuard() {
        position = "talkGuard";
        mainTextArea.setText("Guard: Hello stranger.\nI'm sorry but we cannot let a stranger inside.\nIf you defeat the Dragon and retrieve Silver Ring, you can enter our town!");
        choice1.setText("<");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    private void attackGuard() {
        position = "attackGuard";
        mainTextArea.setText("Guard: Hey don't be stupid!\nThe guard fought back and hit you hard.\n(You receive 25 damage)");
        playerHP = playerHP - 25;
        hpLabelNumber.setText(String.valueOf(playerHP));
        if (playerHP < 1) {
            lose();
        }
        choice1.setText("<");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void crossRoad() {
        position = "crossRoad";
        mainTextArea.setText("You are at a crossroad. " +
                "\nIf you go south, you will go back to the town.");
        choice1.setText("Go north to river");
        choice2.setText("Go east to forest");
        choice3.setText("Go west if you dare");
        choice4.setText("Go south to town");
    }

    public void north() {
        position = "north";
        mainTextArea.setText("""
                There is a river.\s
                You drink the water and rest at the riverside.\s

                (Your HP is recovered.)""");
        playerHP = playerHP + 10;
        if (playerHP > 100) {
            playerHP = 100;
        }
        hpLabelNumber.setText(String.valueOf(playerHP));
        choice1.setText("Go to crossroad");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void east() {
        position = "east";
        mainTextArea.setText("""
                You walked in a forest and found a\s
                chest with Long Sword!

                (You obtained a Long Sword)""");
        weapon = "Long Sword";
        weaponLabelName.setText(weapon);
        choice1.setText("Go to crossroad");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void west() {
        position = "west";
        dragonHP = 120;
        mainTextArea.setText("You encounter a fierce and scary Dragon!");
        choice1.setText("Fight");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");

    }

    public void fight() {
        position = "fight";
        mainTextArea.setText("Dragon HP: " + dragonHP + "\n\nWhat do you do?");
        choice1.setText("Attack");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");

    }

    public void playerAttack() {
        position = "playerAttack";

        int playerDamage = 0;

        if (weapon.equals("Knife")) {
            playerDamage = new java.util.Random().nextInt(15);
        } else if (weapon.equals("Long Sword")) {
            playerDamage = new java.util.Random().nextInt(30);
        }

        mainTextArea.setText("You attacked the Dragon and gave " + playerDamage + " damage!");

        dragonHP = dragonHP - playerDamage;
        choice1.setText("<");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void dragonAttack() {
        position = "dragonAttack";

        int dragonDamage;

        dragonDamage = new java.util.Random().nextInt(20);

        mainTextArea.setText("The Dragon attacked you and gave " + dragonDamage + " damage!");

        playerHP = playerHP - dragonDamage;
        hpLabelNumber.setText(String.valueOf(playerHP));
        choice1.setText("<");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void win() {
        position = "win";

        mainTextArea.setText("""
                You defeated the Dragon!
                The Dragon dropped a ring!

                (You obtained a Silver Ring)""");

        silverRing = 10;


    }

    public void lose() {
        position = "lose";

        mainTextArea.setText("You died!\n\n<GAME OVER>");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);

    }

    public void ending() {
        position = "ending";

        mainTextArea.setText("""
                Guard runs to you!\s

                Guard: Oh you killed that fierce Dragon!
                Thank you so much. You are true hero!
                You are welcome to our town!

                <THE END>

                """);


    }

    private class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            switch (position) {
                case "townGate" -> {
                    switch (yourChoice) {
                        case "c1" -> {
                            if (silverRing == 10) {
                                ending();
                            } else {
                                talkGuard();
                            }
                        }
                        case "c2" -> attackGuard();
                        case "c3" -> crossRoad();
                    }
                }
                case "talkGuard", "attackGuard" -> {
                    if (yourChoice.equals("c1")) {
                        townGate();
                    }
                }
                case "crossRoad" -> {
                    switch (yourChoice) {
                        case "c1" -> north();
                        case "c2" -> east();
                        case "c3" -> west();
                        case "c4" -> townGate();
                    }
                }
                case "north", "east", "win" -> {
                    if (yourChoice.equals("c1")) {
                        crossRoad();
                    }
                }
                case "west" -> {
                    switch (yourChoice) {
                        case "c1" -> fight();
                        case "c2" -> crossRoad();
                    }
                }
                case "fight" -> {
                    switch (yourChoice) {
                        case "c1" -> playerAttack();
                        case "c2" -> crossRoad();
                    }
                }
                case "playerAttack" -> {
                    switch (yourChoice) {
                        case "c1", "c4" -> {
                            if (dragonHP < 1) {
                                win();
                                ending();
                            } else {
                                dragonAttack();
                            }
                        }
                    }
                }
                case "dragonAttack" -> {
                    if (yourChoice.equals("c1")) {
                        if (playerHP < 1) {
                            lose();
                        } else {
                            fight();
                        }
                    }
                }
            }
        }
    }
}
