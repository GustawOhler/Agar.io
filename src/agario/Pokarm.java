package agario;

import java.util.Random;

/**
 * Created by Karoll0 on 2017-03-23.
 */
public class Pokarm {
    private int wspol_x;
    private int wspol_y;
    private float srednica;
    private boolean isAlive;
    private float[] RGB;
    public Pokarm(int x , int y)
    {
        this.wspol_x = x;
        this.wspol_y = y;
        this.srednica = 20.0F;
        isAlive = false;
        Random generator = new Random();
        RGB = new float[3];
        RGB[0] = generator.nextFloat();
        RGB[1] = generator.nextFloat();
        RGB[2] = generator.nextFloat();
    }
    public float[] getRGB(){
        return RGB;
    }
    public float getsrednica() {
        return srednica;
    }

    public void setsrednica(float srednica) {
        this.srednica = srednica;
    }

    public int getWspol_y() {
        return wspol_y;
    }

    public void setWspol_y(int wspol_y) {
        this.wspol_y = wspol_y;
    }

    public int getWspol_x() {
        return wspol_x;
    }

    public void setWspol_x(int wspol_x) {
        this.wspol_x = wspol_x;
    }

    public void setAlive(){isAlive = true;}

    public boolean getAlive(){return isAlive;}
    
    public int generatehashCode() {
        int result = wspol_y * Main.ROZMIAR_X_MAPY + wspol_x;
        return result;
    }
}
