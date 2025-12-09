import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    //Proměnné
    private GreenfootImage bg;
    
    private int drawX = 0;
    private int drawY = 0;
    
    private int KameraX = 0;
    private int KameraY = 80;
    private int KameraZ = 0;
    
    private int pixelX = 0;
    private int pixelY = 0;
    private int pixelZ = 0;

    private double paprsekX = 0;
    private double paprsekY = 0;
    private double paprsekZ = 0;   
    
    private double vektorX = 0;
    private double vektorY = 0;
    private double vektorZ = 0;    
    
    private int krok = 0;
    private int vzdalenostKObrazovce = 300;
    private boolean sledovatPaprsek = false;
    
    //Konstruktor
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        bg = getBackground();
    }
    
    //Akt
    public void act() {
                //Sledování paprsku:
                if (sledovatPaprsek) {
                    
                    paprsekX += vektorX;
                    paprsekY += vektorY;
                    paprsekZ += vektorZ;
                    
                    bg.setColorAt(drawX, drawY, Color.CYAN);
                    
                    //Podlaha
                    if (paprsekY < 0) {

                        boolean cerna = false;
                        
                        if (Math.abs(paprsekX) % 40 < 20) {
                            cerna = true;
                        }
                        if (Math.abs(paprsekZ) % 40 < 20) {
                            cerna = !cerna;
                        }                        
                        
                        if (cerna) {
                            bg.setColorAt(drawX, drawY, Color.BLACK);
                        } else {
                            bg.setColorAt(drawX, drawY, Color.WHITE);
                        }
                        sledovatPaprsek = false;
                    }
                    
                    //Koule 1
                    if (vzdalenost(paprsekX, paprsekY, paprsekZ, 0, 80, 150) < 40) {
                        bg.setColorAt(drawX, drawY, Color.RED);
                        sledovatPaprsek = false;
                    }
                    
                    //Koule 2
                    if (vzdalenost(paprsekX, paprsekY, paprsekZ, -100, 0, 200) < 30) {
                        bg.setColorAt(drawX, drawY, Color.BLUE);
                        sledovatPaprsek = false;
                    }
                
                    //Válec
                    if (vzdalenost(paprsekX, paprsekY, paprsekZ, 100, paprsekY, 150) < 20) {
                        
                        if (paprsekY < 50) {
                            bg.setColorAt(drawX, drawY, Color.GREEN);
                            sledovatPaprsek = false;
                        }
                        
                    }                    
                    
                    if (krok > 500) {sledovatPaprsek = false;}
                    krok += 1;
                    if (sledovatPaprsek) return;
                }
        
        
        
        
        //Vypočítání průsečíku paprsku
        pixelX = (drawX - getWidth()/2) + KameraX;
        pixelY = -(drawY - getHeight()/2) + KameraY;
        pixelZ = vzdalenostKObrazovce + KameraZ;
        
        //Spočítání vektoru paprsku
        zmenVektor(KameraX, KameraY, KameraZ, pixelX, pixelY, pixelZ);
        
        //Započatí sledování paprsku
        paprsekX = KameraX;
        paprsekY = KameraY;
        paprsekZ = KameraZ;
        
        krok = 0;
        sledovatPaprsek = true;

        drawX +=1;
        if (drawX >= getWidth()) {
            drawX = 0;
            drawY += 1;
        }
        if (drawY >= getHeight()) {
            Greenfoot.stop();
        }
    }
    
    //Spočítání vzdálenosti
    public double vzdalenost(double x1, double y1, double z1, double x2, double y2, double z2) {
        double relX = x2 - x1;
        double relY = y2 - y1;
        double relZ = z2 - z1;
        
        return Math.sqrt(relX * relX + relY * relY + relZ * relZ);
    }
    
    //Změnit vektor
    public void zmenVektor(double x1, double y1, double z1, double x2, double y2, double z2) {
        double relX = x2 - x1;
        double relY = y2 - y1;
        double relZ = z2 - z1;
    
        double vzdalenost = Math.sqrt(relX * relX + relY * relY + relZ * relZ);
        
        vektorX = relX / vzdalenost; 
        vektorY = relY / vzdalenost;
        vektorZ = relZ / vzdalenost;
    }
    
}
