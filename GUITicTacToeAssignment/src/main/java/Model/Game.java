package Model;

import java.util.ArrayList;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class Game {
    private final Tile[][] board;
    private final int rows;
    private final int cols;
    private Player currentPlayer;
    private boolean gameEnded;
    
    public Game(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new Tile[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = new Tile();
            }
        }
        currentPlayer = Player.X;
        gameEnded = false;
    }
    public void resetGame() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = new Tile();
            }
        }
        currentPlayer = Player.X;    
        gameEnded = false;
    } 
    
    public Tile[][] getBoard() {return board;}
    public Player getCurrentPlayer() {return currentPlayer;}
    public void nextPlayer() {
        currentPlayer = Player.nextPlayer(currentPlayer);
    }
    
    public void playerTakesTile(int row, int col)
            throws BoardIsFullException, PlayerWonException,
            InvalidTileException, GameEndedException, InvalidTileException {
        if (gameEnded) {throw new GameEndedException();}
        
        if (isValidTile(row, col)) {
            board[row][col].setOwner(currentPlayer); 
        }
        else {throw new InvalidTileException();}
       
        if (isWin(row, col)) {
            gameEnded = true;
            throw new PlayerWonException();
        }
        if (isFullBoard()) {throw new BoardIsFullException();}
    }
    
    public boolean isValidTile(int row, int col) {
        int i = rows-1;
        while (i > row) {
            Player prevTileOwner = board[i][col].getOwner();
            if (prevTileOwner == null) {return false;}
            i--;
        }       
        return board[row][col].getOwner() == null;
    }
    
    private boolean isFullBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].getOwner() == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isWin(int clickRow, int clickCol) {
        ArrayList<Tile> horizontal = new ArrayList<>();
        ArrayList<Tile> vertical = new ArrayList<>();
        ArrayList<Tile> diagUp = new ArrayList<>();
        ArrayList<Tile> diagDown = new ArrayList<>();
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == clickRow) {
                    horizontal.add(board[row][col]);
                }
                if (col == clickCol) {
                    vertical.add(board[row][col]);
                }
                if (row + col == clickRow + clickCol) {
                    diagUp.add(board[row][col]);
                }
                if (col - row == clickCol - clickRow) {
                    diagDown.add(board[row][col]);
                }
            }
        }
        
        return (isStreak(horizontal, 4) ||
                isStreak(vertical, 4) ||
                isStreak(diagUp, 4) ||
                isStreak(diagDown, 4));
    }
    
    private boolean isStreak(ArrayList<Tile> line, int winConditionNumber) {
        int streak = 0;
        int i = 0;
        Player playerPrev = line.get(i).getOwner();
        while ((i < line.size()) && (streak < winConditionNumber)) {
            Player playerCurr = line.get(i).getOwner();
            
            if (playerCurr == null) {
                streak = 0;
                playerPrev = playerCurr;
            }
            else {
                if (playerCurr == playerPrev) {
                    streak++;
                }
                else {
                    streak = 1;
                    playerPrev = playerCurr;
                }
            }
            i++; 
        }
        return streak >= winConditionNumber;
    }
}
