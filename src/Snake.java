import javax.swing.*;

public class Snake extends JFrame{

    Snake(){
        add(new Board());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("SSSSSSSSSSNAKE");
    }

    public static void main(String[] args){
        new Snake().setVisible(true);
    }
}