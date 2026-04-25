public class Position  
{

    public float x, y, z;
    
    public Position(float x, float y, float z) {
    
        this.x = x;
        this.y = y;
        this.z = z;
    
    }
    
    public Position getVectorTo(Position pos) {
    
        float dist = getDistanceTo(pos, false);
        
        float vecX = (pos.x - this.x) / dist;
        float vecY = (pos.y - this.y) / dist;
        float vecZ = (pos.z - this.z) / dist;
        
        return new Position(vecX, vecY, vecZ);
    }
    
    public float getDistanceTo(Position pos, boolean squared) {

        float relX = pos.x - this.x;
        float relY = pos.y - this.y;
        float relZ = pos.z - this.z; 

        float dist;
        
        if (squared) {
            dist = relX*relX + relY*relY + relZ*relZ;
        } else {
            dist = (float)Math.sqrt(relX*relX + relY*relY + relZ*relZ);
        }
        
        return dist;
    }
    
    public void moveByVector(Position vec3) {
    
        this.x += vec3.x;
        this.y += vec3.y;
        this.z += vec3.z;
    
    }
    
}
