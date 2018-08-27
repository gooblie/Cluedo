import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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

    public List<Player> selectCharacters(){
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
        numbers.add(new JLabel("I'm a walrus"));
        JOptionPane.showMessageDialog(null, numbers);
        int numPlayers;
        if (b1.isSelected()){
            numPlayers = 3;
        }else if(b2.isSelected()){
            numPlayers = 4;
        }
        return null;
    }

    private void doNewGame(){
        game = new Game(this);
        selectCharacters();
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
