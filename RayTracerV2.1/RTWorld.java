import java.util.List;
import java.util.ArrayList;
import greenfoot.Color;

public class RTWorld  
{
    public List<RTObject> objects = new ArrayList<RTObject>();
    
    public void addBall(Position pos, int size, Color color) {
        
        RTObject obj = new RTObject();
        obj.setType(RTObject.ObjTypes.BALL);
        obj.setPosition(pos);
        obj.setColor(color);
        obj.setSize(size);
        
        objects.add(obj);
    
    }
    
    public void addCube(Position pos, int size, Color color) {
    
        RTObject obj = new RTObject();
        obj.setType(RTObject.ObjTypes.CUBE);
        obj.setPosition(pos);
        obj.setColor(color);
        obj.setSize(size);
        
        objects.add(obj);
    
    }
    
    public void addChessGround(int height, int size, Color color, Color secColor) {
    
        RTObject obj = new RTObject();
        obj.setType(RTObject.ObjTypes.CHESSGROUND);
        
        obj.setPosition(new Position(0, height, 0));
        obj.setColor(color);
        obj.setSecColor(secColor);
        obj.setSize(size);
        
        objects.add(obj);        
    
    }
}
