import greenfoot.GreenfootImage;
import greenfoot.Color;
import java.util.List;
import java.util.ArrayList;

public class RayTracer  
{

    private int width;
    private int height;
    private Camera cam;
    private RTWorld world; 
    
    private final int traceDistance = 400;
    
    private final Position shadowRayVector = new Position(0.5f, 1, 0.2f);
    private final int shadowTraceDistance = 300;
    
    public long checkCount = 0;
    
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
        
        
        GreenfootImage image = new GreenfootImage(width, height);
        
        for (int a = 0; a < height; a++) {
        
            for (int b = 0; b < width; b++) {
                
                image.setColorAt(b, a, TracePixel(pixelX - width/2, -(pixelY - width/2), pixelZ));
                pixelX++;
                
            }
        
            pixelX = 0;
            pixelY++;
        }
    
        return image;
    }
    
    private Position ray;
    
    private Color TracePixel(float pixelX, float pixelY, float pixelZ) {
        Color color = Color.CYAN;
    
        Position ray = new Position(cam.x, cam.y, cam.z);
        Position vec = new Position(0, 0, 0);
        
        vec = vec.getVectorTo(new Position(pixelX, pixelY, pixelZ));
        
        List<RTObject> CollisionList = getOptimizedCollisionList(world.objects, ray, vec, traceDistance);
        
        if (!CollisionList.isEmpty()) {
            for (int i = 0; i < traceDistance; i++) {

                RTObject collision = getCollision(CollisionList, ray);
        
                if (collision != null) {
                    return getColor(collision, ray);
                }
            
                ray.moveByVector(vec);
            
                
            }
        }
        
        return color;
    }
    
    private RTObject getCollision(List<RTObject> list, Position pos) {
    
        
        for (RTObject collision : list) {
        
            
            if (collision.objectType == RTObject.ObjTypes.BALL) {
            
                checkCount++;
                
                float dist = pos.getDistanceTo(collision.pos, true);
            
                if (dist <= collision.sizeSQ){
                    return collision;
                }
                
            }
        
           
            
            if (collision.objectType == RTObject.ObjTypes.CUBE) {
            
                checkCount++;
                
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
                checkCount++;
                
                if (pos.y <= collision.pos.y) {
                    return collision;
                }
            }
            
            
        }
    
        return null;
    }
    
    private List getOptimizedCollisionList(List<RTObject> fullList, Position loc, Position vec, int steps) {
    
            List<RTObject> optimisedList = new ArrayList<RTObject>();
        
            
        
            for (RTObject object : fullList) {
                
                float startX = loc.x;
                float startY = loc.y;
                float startZ = loc.z;
                
                float endX = loc.x + vec.x * steps;
                float endY = loc.y + vec.y * steps;
                float endZ = loc.z + vec.z * steps;
                
                float minX = Math.min(startX, endX);
                float minY = Math.min(startY, endY);
                float minZ = Math.min(startZ, endZ);
                
                float maxX = Math.max(startX, endX);
                float maxY = Math.max(startY, endY);
                float maxZ = Math.max(startZ, endZ);                
                
                
                //Optimalizace podlahy
                if (object.objectType == RTObject.ObjTypes.CHESSGROUND) {
                    if (minY > object.pos.y || maxY < object.pos.y) {
                        continue;
                    }
                }

                //Optimalizace koulí
                if (object.objectType == RTObject.ObjTypes.BALL) {
                    float r = object.size;

                    if (minX > object.pos.x + r || maxX < object.pos.x - r) {
                        continue;
                    }
 
                    if (minY > object.pos.y + r || maxY < object.pos.y - r) {
                        continue;
                    }
 
                    if (minZ > object.pos.z + r || maxZ < object.pos.z - r) {
                        continue;
                    }
                }
                
                
                //Optimalizace krychle
                if (object.objectType == RTObject.ObjTypes.CUBE) {
                    float size = object.size;

                    if (maxX < object.pos.x || minX > object.pos.x + size) {
                        continue;
                    }

                    if (maxY < object.pos.y || minY > object.pos.y + size) {
                        continue;
                    }

                    if (maxZ < object.pos.z || minZ > object.pos.z + size) {
                        continue;
                    }
                }
                
        
                
                optimisedList.add(object);
            
            }
        
        
            return optimisedList;
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
    
    
    private Position rayS;
    private boolean checkShadow(Position startPos) {
    
        rayS = new Position(startPos.x, startPos.y, startPos.z);
    
        List<RTObject> list = getOptimizedCollisionList(world.objects, rayS, shadowRayVector, shadowTraceDistance);
        
        if (!list.isEmpty()) {
            for (int i = 0; i < shadowTraceDistance; i++) {
                rayS.moveByVector(shadowRayVector);
          
                if (getCollision(list, rayS) != null) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    
}
