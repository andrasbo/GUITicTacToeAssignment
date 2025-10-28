package View;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Andras Boromissza [hyczbo]
 */
public class NewGameDialog extends JDialog implements ChangeListener {
    private final JPanel rowPanel = new JPanel();
    private final JSlider rowSlider = new JSlider(JSlider.HORIZONTAL,4,64,4);
    private final JLabel rowLabel = new JLabel();   
    
    private final JPanel colPanel = new JPanel();
    private final JSlider colSlider = new JSlider(JSlider.HORIZONTAL,4,64,4);
    private final JLabel colLabel = new JLabel();
    private final JButton startGameButton = new JButton();   
    
    /**
     *
     */
    public NewGameDialog() {  
        try {
            setIconImage(ImageIO.read(new File("src/main/resources/tictactoe 16.png")));
        } 
        catch (IOException e) {System.err.println("icon not found");}         
        
        initSelf();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initSelf() {
        setTitle("New Game");
        setDefaultCloseOperation(HIDE_ON_CLOSE);       
        setAlwaysOnTop(true);
        setLayout(new BoxLayout(getContentPane(), 1));
    }
    private void initComponents() {
        rowSlider.setPaintLabels(true);
        rowSlider.setPaintTicks(true);
        rowSlider.setPaintTrack(true);
        rowSlider.setMajorTickSpacing(8);
        rowSlider.setMinorTickSpacing(1);
        rowSlider.setSnapToTicks(true);
        rowSlider.addChangeListener(this);
        
        rowLabel.setText(String.valueOf(rowSlider.getValue()));
        rowLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        rowPanel.setLayout(new BoxLayout(rowPanel, 1));
        rowPanel.setBorder(BorderFactory.createTitledBorder("Number of rows"));
        rowPanel.add(rowLabel);        
        rowPanel.add(rowSlider);

        
        colSlider.setPaintLabels(true);
        colSlider.setPaintTicks(true);
        colSlider.setPaintTrack(true);
        colSlider.setMajorTickSpacing(8);
        colSlider.setMinorTickSpacing(1);
        colSlider.setSnapToTicks(true);
        colSlider.addChangeListener(this);
        
        colLabel.setText(String.valueOf(colSlider.getValue()));
        colLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        colPanel.setLayout(new BoxLayout(colPanel, 1));
        colPanel.setBorder(BorderFactory.createTitledBorder("Number of columns"));
        colPanel.add(colLabel);        
        colPanel.add(colSlider);
        
        startGameButton.setText("Start");
        startGameButton.setAlignmentX(CENTER_ALIGNMENT);
        
        add(rowPanel);
        add(colPanel);
        add(startGameButton);
        
        pack();        
    }
    
    /**
     *
     * @param listener
     */
    public void addStartGameActionListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }

    /**
     *
     * @return
     */
    public int getRowInput() {
        return rowSlider.getValue();
    }

    /**
     *
     * @return
     */
    public int getColInput() {
        return colSlider.getValue();
    }

    /**
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        rowLabel.setText(String.valueOf(rowSlider.getValue()));
        colLabel.setText(String.valueOf(colSlider.getValue()));
    }
}
