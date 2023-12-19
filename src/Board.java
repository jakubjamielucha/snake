import javax.swing.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{

    private Image apple, mint1, mint2;
    private int dots, appleX, appleY;
    private final int DOT_SIZE = 10, ALL_DOTS = 900, RANDOM_POSITION = 29;
    private final int x[] = new int[ALL_DOTS], y[] = new int [ALL_DOTS];
    private Timer timer;
    private boolean inGame = true, leftDirection = false, rightDirection = false, upDirection = false, downDirection = false;

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
            locateApple();
            timer = new Timer(140, this);
            timer.start();
        }
    }

    public void locateApple(){
        int r = (int)(Math.random()*RANDOM_POSITION);
        appleX = (r*DOT_SIZE);
        r = (int)(Math.random()*RANDOM_POSITION);
        appleY = (r*DOT_SIZE);
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            locateApple();
        }
    }

    public void draw (Graphics g){
        if(inGame){
            g.drawImage(apple, appleX, appleY, this);

            for(int z = 0; z<dots; z++){
            if(z==0){
                g.drawImage(mint1, x[z], y[z], this);
            } else {
                g.drawImage(mint2, x[z], y[z], this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    } else {
        gameOver(g);
    }
    }

    public void gameOver(Graphics g){
        String msg = "GAME OVER";
        Font font = new Font("SAN_SERIF", Font.BOLD, 16);
        FontMetrics metrices = getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg, (300 - metrices.stringWidth(msg))/2, 300/2);
    }

    public void checkCollision(){

        for(int z = dots; z > 0; z--){
            if( z > 4 && x[0] == x[z] && y[0] == y[z]){
                inGame = false;
            }
        }

        if(y[0] >= 300 || y[0] < 0 || x[0] >= 300 || x[0] < 0){
            inGame = false;
        }

        if(!inGame){
            timer.stop();
        }

    }

    private class TAdapter extends KeyAdapter{

        public void keyPressed(KeyEvent e){

            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && (!rightDirection)){
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if(key == KeyEvent.VK_RIGHT && (!leftDirection)){
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if(key == KeyEvent.VK_UP && (!downDirection)){
                upDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

            if(key == KeyEvent.VK_DOWN && (!upDirection)){
                downDirection = true;
                leftDirection = false;
                rightDirection = false;
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(inGame){
            checkApple();
        }
    }
}