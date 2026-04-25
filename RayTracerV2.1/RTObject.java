import greenfoot.Color;

public class RTObject  
{
    
    public enum ObjTypes {BALL, CUBE, CHESSGROUND}

    public ObjTypes objectType;
    public Position pos;
    
    public float size = 10;
    public float sizeSQ = size * size;
    
    public Color color = Color.GREEN;
    public Color secColor;
    
    public void setType(ObjTypes type) {
        this.objectType = type;
    }
    
    public void setPosition(Position pos) {
        this.pos = pos;
    }
    
    public void setSize(int size) {
        this.size = size;
        this.sizeSQ = size * size;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setSecColor(Color color) {
        this.secColor = color;
    } 
}
