import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 650;
    
    private Camera cam = new Camera(35,30,-40, this);
    private Canvas canvas = new Canvas();;
    private RTWorld rtw = new RTWorld();
    private RayTracer rt = new RayTracer(WIDTH, HEIGHT, cam, rtw);;
    
    public MyWorld()
    {    
        super(WIDTH, HEIGHT, 1); 
        Greenfoot.setSpeed(10000);

        addRTObjects();
        addObject(canvas, WIDTH/2, HEIGHT/2);
        
        renderFrame();
    }
    
    public void act() {
    
        cam.tick();
    
    }
    
    private void addRTObjects() {
    
        rtw.addBall(new Position(0, 100, 200), 80, Color.RED);
        
        rtw.addBall(new Position(120, 75, 120), 25, Color.WHITE);
        
        rtw.addCube(new Position(-150, 70, 300), 20, Color.BLUE);
    
        rtw.addCube(new Position(100, 20, 200), 20, Color.GREEN);
        
        rtw.addChessGround(0, 15, Color.ORANGE, Color.WHITE);
    }
    
    public void renderFrame() {
        
        GreenfootImage image = rt.render();
        canvas.displayImage(image);
        
    }
    
}
