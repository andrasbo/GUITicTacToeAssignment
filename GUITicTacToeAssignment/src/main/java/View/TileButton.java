/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class TileButton extends JButton {
    private final int row;
    private final int col;

    /**
     *
     * @param row
     * @param col
     */
    public TileButton(int row, int col) {
        this.row = row;
        this.col = col;        
        setFocusPainted(false);
        this.setBorder(new BevelBorder(0));
        this.setCursor(new Cursor(12));
    }

    /**
     *
     * @return
     */
    public int getRow() {return row;}

    /**
     *
     * @return
     */
    public int getCol() {return col;}
}
