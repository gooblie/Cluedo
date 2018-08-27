import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michael on 27/08/18.
 */
public class GUI {

    public enum Move {
        NORTH, SOUTH, EAST, WEST
    }

    public GUI(){
        JFrame frame = new JFrame("Cluedo");
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

    private void doNewGame(){
        Game game = new Game();
        game.gameLoop();
    }

    private void onMove(Move move){

    }


    private void redraw(Graphics g) {

    }


    public static void main(String args[]){
        new GUI();
    }

}
