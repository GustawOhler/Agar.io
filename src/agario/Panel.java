package agario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;


/**
 * Created by goodstuff on 23.03.2017.
 */

public class Panel extends JPanel implements MouseMotionListener{
    Mapa mapa;
    private int obecnyx, obecnyy, rozmiarx, rozmiary;
    private int licznik, licznik_przesuniecia;
    private double x_przesun, y_przesun;
    private boolean czy_mozna_przesunac;
    public final static int INTERVAL = 1;
    Gracz gracz;

    public Panel(int x, int y){
        mapa = new Mapa(Main.ROZMIAR_X_MAPY,Main.ROZMIAR_Y_MAPY);
        mapa.generujNowe(20);
        gracz = new Gracz(Main.ROZMIAR_X_MAPY/2,Main.ROZMIAR_Y_MAPY/2,30.0F, this, mapa);
        rozmiarx = x;
        rozmiary = y;
        setPreferredSize(new Dimension(rozmiarx, rozmiary));
        setBackground(Color.white);
        obecnyx = gracz.getWspol_x()-Main.ROZMIAR_X_PANELU/2;
        obecnyy=gracz.getWspol_y()-Main.ROZMIAR_Y_PANELU/2;
        licznik = 0;
        licznik_przesuniecia = 0;
        x_przesun = 0;
        y_przesun = 0;
        czy_mozna_przesunac= false;
        addMouseMotionListener(this);
        Timer timer = new Timer(INTERVAL, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            licznik++;
            licznik_przesuniecia++;
            if (licznik>=500){
                mapa.generujNowe(5);
                licznik = 0;
                //System.out.println(mapa.getMap().size());
            }
            if (licznik_przesuniecia>=10){
                gracz.przesun(x_przesun, y_przesun);
                gracz.sprawdz_czy_pokarm();
            }
            obecnyx = gracz.getWspol_x()-Main.ROZMIAR_X_PANELU/2;
            obecnyy=gracz.getWspol_y()-Main.ROZMIAR_Y_PANELU/2;
            repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Pokarm element : mapa.getMap().values()){
            if (element.getAlive() && element.getWspol_x() > obecnyx && element.getWspol_x() < obecnyx + rozmiarx && element.getWspol_y() > obecnyy && element.getWspol_y() < obecnyy + rozmiary) {
                Ellipse2D pokarm = new Ellipse2D.Float((element.getWspol_x()-obecnyx)-element.getsrednica()/2, (element.getWspol_y()-obecnyy)-element.getsrednica()/2, element.getsrednica(), element.getsrednica());
                
                g2d.setPaint(Color.getHSBColor(element.getRGB()[0],element.getRGB()[1],element.getRGB()[2]));
                g2d.fill(pokarm);
            }
        }
        Ellipse2D koloGracza = new Ellipse2D.Float((Main.ROZMIAR_X_PANELU/2-gracz.getsrednica()/2), (Main.ROZMIAR_Y_PANELU/2-gracz.getsrednica()/2), gracz.getsrednica(), gracz.getsrednica());
        g2d.setPaint(Color.getHSBColor(0,0,0));
        g2d.fill(koloGracza);
    }

    public void set_obecne_wspolrzedne(int x, int y){
        obecnyx = x;
        obecnyy = y;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        x_przesun = me.getX()-(Main.ROZMIAR_X_PANELU/2);
        y_przesun = me.getY()-(Main.ROZMIAR_Y_PANELU/2);
    }
    
    public void zerujlicznik(){
        licznik_przesuniecia = 0;
    }
    
    public boolean czy_mozna_przesuwac(){
        return czy_mozna_przesunac;
    }
    
    public void setczymozna(){
        czy_mozna_przesunac = false;
    }
}
