import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanal gamePanal = new GamePanal();
        window.add(gamePanal);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanal.startGameThread();

    }
}
