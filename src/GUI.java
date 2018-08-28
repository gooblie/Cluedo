import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    JPanel playerView;

    public GUI(){
        game = new Game(this);

        frame = new JFrame("Cluedo");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setMaximizedBounds(new Rectangle(1000, 1000));
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
        frame.setSize(800,800);

        //set game canvas
        canvas = new JComponent() {
            protected void paintComponent(Graphics g) {
                redrawBoard(g);
            }
        };

        //set up menu bar
        JPanel menu = new JPanel();

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> doNewGame());

        menu.add(newGame);

        //set up player controls
        playerView = new JPanel();
        playerView.setPreferredSize(new Dimension(700, 150));
        playerView.setVisible(true);

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
        frame.getContentPane().add(BorderLayout.SOUTH, playerView);
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
        List<Player> players = new ArrayList<>();
        System.out.println(numPlayers);
        List<CharacterCard> characters = new ArrayList<>(game.getCharacters());
        for (int i = 1; i <= numPlayers; i++) {
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
            players.add(new Player(playerName, character, i, game.getBoard().getStartPosition(character)));
        }
        return players;
    }

    private void doNewGame(){
        game = new Game(this);
        game.initPlayers();
        game.getBoard().initBoardPlayerStart();
        redrawBoard(canvas.getGraphics());
        setPlayerView();
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
        redrawBoard(canvas.getGraphics());
    }

    public int getWidth(){
        return canvas.getWidth();
    }

    public void setPlayerView(){

        JTextArea text = new JTextArea();
        text.setText("Hi " + game.getCurrentPlayer().getName() + "You have " + game.moves + " moves");

        JComponent handView = new JComponent() {
            protected void paintComponent(Graphics g) {
                redrawHand(g);
            }
        };

        //draw cards in hand


        if (playerView != null){
            playerView.add(text);
            playerView.add(handView);
        }


    }

    public int getHeight(){
        return canvas.getHeight();
    }

    private void redrawBoard(Graphics g) {
        game.getBoard().draw(this, g);
    }

    private void redrawHand(Graphics g){
        int i = 0;
        for (Card card: game.getCurrentPlayer().getCards()) {
            //draw the card
            if(card instanceof WeaponCard){
                g.setColor(Color.red);
            }else if(card instanceof CharacterCard){
                g.setColor(Color.yellow);
            }else{
                g.setColor(Color.orange);
            }
            g.drawRect(i*70, 0, 60, 100);
            i++;
        }
    }

    public static void main(String args[]){
        new GUI();
    }

}
