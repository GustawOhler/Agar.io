package agario;

import javax.swing.*;

/**
 * Created by goodstuff on 23.03.2017.
 */
public class Okno extends JFrame{
    public Okno() {
        super("agar.io");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel(Main.ROZMIAR_X_PANELU, Main.ROZMIAR_Y_PANELU);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
