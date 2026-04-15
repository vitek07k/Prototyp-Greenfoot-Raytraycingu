import greenfoot.GreenfootImage;
import greenfoot.Color;


public class RayTracer  
{

    private int width;
    private int height;
    private Camera cam;
    private RTWorld world; 
    
    private final int traceDistance = 400;
    
    private final Position shadowRayVector = new Position(0.5f, 1, 0.2f);
    private final int shadowTraceDistance = 300;
    
    public RayTracer(int width, int height, Camera cam, RTWorld world) {
    
        this.width = width;
        this.height = height;
        this.world = world;
        this.cam = cam;
        
    }
    
    
    public GreenfootImage render() {
    
        int pixelX = 0;
        int pixelY = 0;
        int pixelZ = 450;
        
        //int debugCounter = 1;
        
        GreenfootImage image = new GreenfootImage(width, height);
        
        for (int a = 0; a < height; a++) {
        
            for (int b = 0; b < width; b++) {
                
                image.setColorAt(b, a, TracePixel(pixelX - width/2, -(pixelY - width/2), pixelZ));
                pixelX++;
                
                //System.out.println((int)(100*((float)(debugCounter) / (float)(width*height))) + "%");
                //debugCounter++;
            }
        
            pixelX = 0;
            pixelY++;
        }
    
        return image;
    }
    
    private Color TracePixel(float pixelX, float pixelY, float pixelZ) {
        Color color = Color.CYAN;
    
        Position ray = new Position(cam.x, cam.y, cam.z);
        Position vec = new Position(0, 0, 0);
        
        vec = vec.getVectorTo(new Position(pixelX, pixelY, pixelZ));
        
        for (int i = 0; i < traceDistance; i++) {
            
            RTObject collision = getCollision(ray);
        
            if (collision != null) {
                return getColor(collision, ray);
            }
            
            ray.moveByVector(vec);
            
        }
        
        return color;
    }
    
    private RTObject getCollision(Position pos) {
    
        for (RTObject collision : world.objects) {
        
            if (collision.objectType == RTObject.ObjTypes.BALL) {
            
                float dist = pos.getDistanceTo(collision.pos);
            
                if (dist <= collision.size){
                    return collision;
                }
                
            }
        
            if (collision.objectType == RTObject.ObjTypes.CUBE) {
            
                float x1 = pos.x;
                float y1 = pos.y;
                float z1 = pos.z;
                
                float x2 = collision.pos.x;
                float y2 = collision.pos.y;
                float z2 = collision.pos.z;

                float size = collision.size;
                
                if (x1 < x2 + size && y1 < y2 + size && z1 < z2 + size) {
                    
                    if (x1 > x2 && y1 > y2 && z1 > z2) {
                        return collision;
                    }
                    
                }
            
            }
            
            if (collision.objectType == RTObject.ObjTypes.CHESSGROUND) {
                if (pos.y <= collision.pos.y) {
                    return collision;
                }
            }
            
        }
    
        return null;
    }
    
    private Color getColor(RTObject obj, Position pos) {
    
        Color color = obj.color;
        
        if (obj.objectType == RTObject.ObjTypes.CHESSGROUND) {
            
            boolean secColor = false;
            if (Math.floor(pos.x / obj.size) % 2 == 0) {
                secColor = true;
            }
            
            if (Math.floor(pos.z / obj.size) % 2 == 0) {
                secColor = !secColor;
            }
            
            if (secColor) {
                color = obj.secColor;
            } else {
                color = obj.color;
            }
            
        }
    
        if (checkShadow(pos)) {
        
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            
            r = (int)(r*0.8);
            g = (int)(g*0.8);
            b = (int)(b*0.8);
            
            color = new Color(r,g,b);
        }
        
        return color;
    }
    
    private boolean checkShadow(Position startPos) {
    
        Position ray = new Position(startPos.x, startPos.y, startPos.z);
    
        for (int i = 0; i < shadowTraceDistance; i++) {
            ray.moveByVector(shadowRayVector);
            
            if (getCollision(ray) != null) {
                return true;
            }
        }
        
        return false;
    }
    
    
    
}
