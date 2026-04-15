import greenfoot.*;

public class Camera  
{

    
    public float x, y, z;
    public int screenDist = 300;
    
    private int speed = 25;
    private MyWorld world;
    
    public Camera(float x, float y, float z, MyWorld world) {
    
        this.x = x;
        this.y = y;
        this.z = z;
    
        this.world = world;
    }
    
    public void tick() {
    
        if (Greenfoot.isKeyDown("W")) {
            z += speed;
            world.renderFrame();
        }
        if (Greenfoot.isKeyDown("S")) {
            z -= speed;
            world.renderFrame();
        }

        if (Greenfoot.isKeyDown("A")) {
            x -= speed;
            world.renderFrame();
        }
        if (Greenfoot.isKeyDown("D")) {
            x += speed;
            world.renderFrame();
        }
        
        if (Greenfoot.isKeyDown("shift")) {
            y -= speed;
            
            if (y < 10) y = 10;
            
            world.renderFrame();
        }
        if (Greenfoot.isKeyDown("space")) {
            y += speed;
            world.renderFrame();
        }
    
    }
}
