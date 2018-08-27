import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public GUI(){
        frame = new JFrame("Cluedo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        //set game canvas
        JComponent canvas = new JComponent() {
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

        JButton west = new JButton("\u2190");
        west.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                onMove(Move.WEST);
            }
        });

        JButton east = new JButton("\u2192");
        east.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                onMove(Move.EAST);
            }
        });

        JButton north = new JButton("\u2191");
        north.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                onMove(Move.NORTH);
            }
        });

        JButton south = new JButton("\u2193");
        south.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                onMove(Move.SOUTH);
            }
        });

        JPanel nav = new JPanel();
        nav.setLayout(new GridLayout(2, 2));
        nav.add(north);
        nav.add(south);
        nav.add(west);
        nav.add(east);

        playerControls.add(nav);


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

    private void onMove(Move move){

    }

    public int getWidth(){
        return frame.getWidth();
    }

    public int getHeight(){
        return frame.getHeight();
    }

    private void redraw(Graphics g) {
        game.getBoard().draw(this, g);
    }


    public static void main(String args[]){
        new GUI();
    }

}
