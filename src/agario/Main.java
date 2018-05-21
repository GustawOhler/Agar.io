package agario;

import java.awt.*;

public class Main {
    public static final int ROZMIAR_X_MAPY = 4000;
    public static final int ROZMIAR_Y_MAPY = 4000;
    public static final int ROZMIAR_X_PANELU = 1000;
    public static final int ROZMIAR_Y_PANELU = 1000;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Okno();
            }
        });
    }
}