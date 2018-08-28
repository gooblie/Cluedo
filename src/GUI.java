import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 27/08/18.
 */
public class GUI {

    public enum Move {
        NORTH, SOUTH, EAST, WEST
    }

    public JFrame frame;
    private Game game;
    JComponent canvas;

    public GUI(){
        game = new Game(this);

        frame = new JFrame("Cluedo");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //prompt user if wanting to exit
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?");
                if (a == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
        frame.setSize(500,500);

        //set game canvas
        canvas = new JComponent() {
            protected void paintComponent(Graphics g) {
                redraw(g);
            }
        };

        //set up menu bar
        JPanel menu = new JPanel();

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> doNewGame());

        menu.add(newGame);

        //set up player controls
        JPanel playerControls = new JPanel();

        frame.getContentPane().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                onMove(e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //add components to frame
        frame.getContentPane().add(BorderLayout.NORTH, menu);
        frame.getContentPane().add(BorderLayout.CENTER, canvas);
        frame.getContentPane().add(BorderLayout.SOUTH, playerControls);
        frame.setVisible(true);

    }

    public Map<String, String> selectCharacters(){
        //ask how many players are in the game
        JOptionPane optionPane = new JOptionPane();
        JPanel numbers = new JPanel();
        JRadioButton b1 = new JRadioButton("3");
        JRadioButton b2 = new JRadioButton("4");
        JRadioButton b3 = new JRadioButton("5");
        JRadioButton b4 = new JRadioButton("6");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(b1);
        buttonGroup.add(b2);
        buttonGroup.add(b3);
        buttonGroup.add(b4);
        numbers.add(b1);
        numbers.add(b2);
        numbers.add(b3);
        numbers.add(b4);
        JOptionPane.showMessageDialog(frame, numbers, "How many players", JOptionPane.QUESTION_MESSAGE);
        int numPlayers = 0;
        if (b1.isSelected()){
            numPlayers = 3;
        }else if(b2.isSelected()){
            numPlayers = 4;
        }else if(b3.isSelected()){
            numPlayers = 5;
        }else if(b4.isSelected()){
        numPlayers = 6;
    }
    //initialise players
    Map<String, String> players = new HashMap<>();
        System.out.println(numPlayers);
        List<CharacterCard> characters = new ArrayList<>(game.getCharacters());
        for (int i = 0; i < numPlayers; i++) {
            //ask for a player name
            String playerName = JOptionPane.showInputDialog("Please input a name for your player");
            JPanel characterPanel = new JPanel();
            ButtonGroup buttonGroup1 = new ButtonGroup();
            //ask which character the player would like to
            for (CharacterCard c: characters) {
                JRadioButton button = new JRadioButton(c.getName());
                button.setActionCommand(c.getName());
                characterPanel.add(button);
                buttonGroup1.add(button);
            }
            JOptionPane.showMessageDialog(frame, characterPanel, "Select a character token", JOptionPane.QUESTION_MESSAGE );
            String character = buttonGroup1.getSelection().getActionCommand();
            for (CharacterCard c: game.getCharacters()) {
                if(c.getName().equals(character)){characters.remove(c);}
            }
            players.put(playerName, character);
        }
        return players;
    }

    private void doNewGame(){
        game = new Game(this);
        game.initPlayers();
    }

    private void onMove(char move){
        if(game.getCurrentPlayer() == null || game.getMoves() == 0)return;
        switch (java.lang.Character.toUpperCase(move)) {
            case 'W':
                game.getBoard().move(game.getCurrentPlayer(), Board.Direction.NORTH);
                game.moves--;
                break;
            case 'D':
                game.getBoard().move(game.getCurrentPlayer(), Board.Direction.EAST);
                game.moves--;
                break;
            case 'S':
                game.getBoard().move(game.getCurrentPlayer(), Board.Direction.SOUTH);
                game.moves--;
                break;
            case 'A':
                game.getBoard().move(game.getCurrentPlayer(), Board.Direction.WEST);
                game.moves--;
                break;
        }
    }

    public int getWidth(){
        return canvas.getWidth();
    }

    public int getHeight(){
        return canvas.getHeight();
    }

    private void redraw(Graphics g) {
        game.getBoard().draw(this, g);
    }


    public static void main(String args[]){
        new GUI();
    }

}
