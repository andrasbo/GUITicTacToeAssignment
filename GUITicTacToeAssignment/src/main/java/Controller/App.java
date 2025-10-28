package Controller;

import Model.BoardIsFullException;
import Model.Game;
import Model.GameEndedException;
import Model.InvalidTileException;
import Model.Player;
import Model.PlayerWonException;
import View.NewGameDialog;
import View.TileButton;
import View.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class App {
    private static Game game;
    private static final Window window = new Window();
    private static final NewGameDialog newGameDialog = new NewGameDialog();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        window.setVisible(true);

        window.addNewGameActionListener(new HandleNewGame());
        window.addResetGameActionListener(new HandleResetGame());
        window.addExitWindowActionListener(new HandleExitWindow());
        window.addBoardActionListener(new HandleBoard());
        
        newGameDialog.addStartGameActionListener(new HandleStartGame());
        
        newGameDialog.setVisible(true);

    }
    
    static class HandleNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newGameDialog.setVisible(true);
        }
    }
    static class HandleResetGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.resetGame();
            window.resetBoard();
        } 
    }
    static class HandleExitWindow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    static class HandleStartGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rows = newGameDialog.getRowInput();
            int cols = newGameDialog.getColInput();
            window.newBoard(rows, cols);
            window.addBoardActionListener(new HandleBoard());
            newGameDialog.setVisible(false);
            game = new Game(rows, cols);
            window.statusTurn(game.getCurrentPlayer().toString());
            window.drawBoard(game);
        }
    } 
    static class HandleBoard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TileButton tile = (TileButton)e.getSource();
            int row = tile.getRow();
            int col = tile.getCol();
            Player player = game.getCurrentPlayer();
            
            try {
                game.playerTakesTile(row, col);
                game.nextPlayer();
                window.statusTurn(game.getCurrentPlayer().toString());
            }
            catch (InvalidTileException exception) {
                System.err.println("tile cant be taken");
            }
            catch (BoardIsFullException exception) {
                window.statusDraw();
                newGameDialog.setVisible(true);
            }
            catch (PlayerWonException exception) {
                window.statusWin(player.toString());
                newGameDialog.setVisible(true);                
            }
            catch (GameEndedException exception) {}
            finally {
                window.drawBoard(game);
            }
        }
        
    }

}
