package agario;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by goodstuff on 23.03.2017.
 */
public class Mapa {

    private HashMap<Integer, Pokarm> zywy_pokarm;
    private int rozmiar_x;
    private int rozmiar_y;
    private int ilosc = 20;

    public Mapa(int wymiarx, int wymiary){
        rozmiar_x = wymiarx;
        rozmiar_y = wymiary;
        zywy_pokarm = new HashMap<>();
    }
    
    public void generujNowe(int ilosc){
        int randomx, randomy;
        Random generator = new Random();
        for (int i = 0; i<ilosc;){
            randomx = generator.nextInt(rozmiar_x-20)+10;
            randomy = generator.nextInt(rozmiar_y-20)+10;
            Pokarm nowy = new Pokarm(randomx,randomy);
            while (zywy_pokarm.containsKey(nowy.generatehashCode())){
                if (randomx+5 < Main.ROZMIAR_X_MAPY)
                    randomx += 5;
                else if (randomx-5> 0)
                    randomx -= 5;
                else
                    randomx = generator.nextInt(rozmiar_x-20)+10;
                if (randomy+5 < Main.ROZMIAR_Y_MAPY)
                    randomy += 5;
                else if (randomy-5> 0)
                    randomy -=5;
                else
                    randomy = generator.nextInt(rozmiar_y-20)+10;
                nowy = new Pokarm(randomx,randomy);
            }
            nowy.setAlive();
            zywy_pokarm.put(nowy.generatehashCode(),nowy);
            i++;
        }
    }

    public HashMap<Integer, Pokarm> getMap(){
        return zywy_pokarm;
    }
    
    public void usunPokarm(int hashCode){
        zywy_pokarm.remove(hashCode);
    }
}
