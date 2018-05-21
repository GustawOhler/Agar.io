package agario;

import static java.lang.Math.pow;

/**
 * Created by Karoll0 on 2017-03-23.
 */
public class Gracz {
    private int wspol_x;
    private int wspol_y;
    private float srednica;
    private int id_gracza;
    private boolean isAlive;
    private String name;
    private double przesunieciex;
    private Panel panel;
    private double przesunieciey;
    private Mapa mapa;
    
    public Gracz(int x , int y, float prom, Panel pan, Mapa map)
    {
        this.wspol_x = x;
        this.wspol_y = y;
        this.srednica = prom;
        this.isAlive = true;
        przesunieciex = 0;
        przesunieciey = 0;
        this.panel = pan;
        this.mapa = map;
    }

    public float getsrednica() {
        return srednica;
    }

    public void setsrednica(int srednica) {
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

    public int getId_gracza() {
        return id_gracza;
    }

    public void setId_gracza(int id_gracza) {
        this.id_gracza = id_gracza;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void przesun(double x, double y){
            przesunieciex += (double)x/50;
            przesunieciey += (double)y/50;
            if (przesunieciex>=1.0 || przesunieciex<=-1.0){
                if (wspol_x + (int) przesunieciex + (int)(srednica/2.0F) <= Main.ROZMIAR_X_MAPY && wspol_x + (int) przesunieciex - (int)(srednica/2.0F) >= 0)
                    wspol_x += (int) przesunieciex;
                przesunieciex -= (double)(int)przesunieciex;
            }
            if (przesunieciey>=1.0 || przesunieciey<=-1.0){
                if (wspol_y + (int) przesunieciey + (int)(srednica/2.0F) <= Main.ROZMIAR_Y_MAPY && wspol_y + (int) przesunieciey - (int)(srednica/2.0F) >= 0)
                    wspol_y += (int) przesunieciey;
                przesunieciey -= (double)(int)przesunieciey;
            }
            panel.setczymozna();
            panel.zerujlicznik();
    }
    
    public void sprawdz_czy_pokarm(){
        int srodek_okregu_x = wspol_x;
        int srodek_okregu_y = wspol_y;
        for (int i = 0; i<=(int)srednica; i++){
            for (int j = 0; j<=(int)srednica; j++){
                int x = wspol_x - (int)(srednica/2.0F) + i;
                int y = wspol_y - (int)(srednica/2.0F) + j;
                if (pow((double)(x-srodek_okregu_x),2.0)+pow((double)(y-srodek_okregu_y),2.0)<=pow((double)srednica/2.0,2.0)){
                    if (mapa.getMap().containsKey(new Pokarm(x,y).generatehashCode())){
                        mapa.usunPokarm(new Pokarm(x, y).generatehashCode());
                        srednica+=2.0;
                        if (wspol_x + srednica/2.0F > Main.ROZMIAR_X_MAPY)
                            wspol_x -= 1;
                        else if (wspol_x - srednica/2.0F < 0)
                            wspol_x +=1;
                        if (wspol_y + srednica > Main.ROZMIAR_Y_MAPY)
                            wspol_y -= 1;
                        else if (wspol_y - srednica/2.0F < 0)
                            wspol_y +=1;
                    }
                }
            }
        }
    }
}