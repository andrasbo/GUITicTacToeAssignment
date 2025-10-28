package View;

import Model.Game;
import Model.Player;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class Window extends JFrame {

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu gameMenu = new JMenu();
    private final JMenuItem newGameButton = new JMenuItem();    
    private final JMenuItem resetGameButton = new JMenuItem();
    private final JMenuItem exitWindowButton = new JMenuItem();
    
    private final JPanel statusPanel = new JPanel();    
    private final JLabel statusLabel = new JLabel();
    private final JPanel boardPanel = new JPanel();
    
    private int rows;
    private int cols;
   
    /**
     *
     */
    public Window() {      
               
        try {
            setIconImage(ImageIO.read(new File("src/main/resources/tictactoe 16.png")));
        } 
        catch (IOException e) {
            System.err.println("icon not found");
        }               
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException ex) {}
        
        
        initSelf();
        initComponents();
        setLocationRelativeTo(null);
    }
    private void initSelf() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), 1));
    }                  
    private void initComponents() {

        gameMenu.setText("Game");
        gameMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        newGameButton.setText("New");
        newGameButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newGameButton.setIcon(new ImageIcon(getClass().getResource("/tictactoe 16.png")));
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        resetGameButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        resetGameButton.setIcon(new ImageIcon(getClass().getResource("/reset 16.png"))); // NOI18N
        resetGameButton.setText("Reset");
        resetGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        exitWindowButton.setText("Exit");
        exitWindowButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        exitWindowButton.setIcon(new ImageIcon(getClass().getResource("/exit 16.png")));

        gameMenu.add(newGameButton);
        gameMenu.add(resetGameButton);
        gameMenu.add(exitWindowButton);        
        
        menuBar.add(gameMenu);       
        setJMenuBar(menuBar);
        
        statusPanel.setBorder(BorderFactory.createTitledBorder("Status"));   
        statusPanel.setPreferredSize(new Dimension(400,60));
        statusPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,60));
        statusLabel.setText("Start a new Game!");
        statusPanel.add(statusLabel);
        add(statusPanel);
        
        boardPanel.setPreferredSize(new Dimension(400, 400));
        boardPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));      
        
        add(boardPanel);
         
        pack();
    }                       

    /**
     *
     * @param rows
     * @param cols
     */
    public void newBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        
        boardPanel.removeAll();
        boardPanel.setVisible(false);
        GridLayout layout = new GridLayout(rows,cols);
        layout.setHgap(5);
        layout.setVgap(5);
        boardPanel.setLayout(layout);
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TileButton tile = new TileButton(row, col);
                tile.setFont(new Font("sans", Font.BOLD, 48));
                tile.setBorderPainted(false);
                tile.setForeground(Color.white);
                boardPanel.add(tile);                
            }
        }
        boardPanel.setVisible(true);
    } 

    /**
     *
     */
    public void resetBoard() {
        for (Component tile : boardPanel.getComponents()) {
            ((TileButton)tile).setText("");
            ((TileButton)tile).setBackground(Color.WHITE);
        }        
    }

    /**
     *
     * @param game
     */
    public void drawBoard(Game game) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TileButton tile = (TileButton)boardPanel.getComponent(row * cols + col);
                if (game.isValidTile(row, col)) {
                    tile.setText("");
                    tile.setBackground(Color.green);
                }
                else if (game.getBoard()[row][col].getOwner() == Player.X) {
                    tile.setText("X");
                    tile.setBackground(Color.blue);
                }
                else if (game.getBoard()[row][col].getOwner() == Player.O) {
                    tile.setText("O");
                    tile.setBackground(Color.red);
                }
                else {
                    tile.setText("");
                    tile.setBackground(Color.white);
                }
            }
        }       
    }

    /**
     *
     * @param player
     */
    public void statusTurn(String player) {
        statusLabel.setText("Player " + player + ", take your turn!");
    }

    /**
     *
     */
    public void statusDraw() {
        statusLabel.setText("DRAW!");
    }

    /**
     *
     * @param player
     */
    public void statusWin(String player) {
        statusLabel.setText(player + " has won!");
    }
    
    /**
     *
     * @param listener
     */
    public void addNewGameActionListener(ActionListener listener) {
        newGameButton.addActionListener(listener);}
                                              
    /**
     *
     * @param listener
     */
    public void addResetGameActionListener(ActionListener listener) {
        resetGameButton.addActionListener(listener);}
                                              
    /**
     *
     * @param listener
     */
    public void addExitWindowActionListener(ActionListener listener) {
        exitWindowButton.addActionListener(listener);}
    
    /**
     *
     * @param listener
     */
    public void addBoardActionListener(ActionListener listener) {
        for (Component tile : boardPanel.getComponents()) {
            ((TileButton)tile).addActionListener(listener);
        }
    }
}

