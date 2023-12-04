import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Image apple, mint1, mint2;
    private int dots;
    private final int DOT_SIZE = 10, ALL_DOTS = 900, RANDOM_POSITION = 29;
    private final int x[] = new int[ALL_DOTS], y[] = new int [ALL_DOTS];

    Board(){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300, 300));
    
        loadImages();
        initGame();
    }

    public void loadImages(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/apple.png"));
        apple = i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/mint1.png"));
        mint1 = i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/mint2.png"));
        mint2 = i3.getImage();
    }

    public void initGame(){
        dots = 3;

        for(int z = 0; z<dots; z++){
            x[z] = 50 - z * DOT_SIZE;
            y[z] = 50;
        }
    }

}